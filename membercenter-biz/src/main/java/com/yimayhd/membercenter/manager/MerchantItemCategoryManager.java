package com.yimayhd.membercenter.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.rocketmq.client.producer.*;
import com.yimayhd.membercenter.client.domain.examine.ExamineDO;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.converter.ExamineConverter;
import com.yimayhd.membercenter.enums.ExamineStatus;
import com.yimayhd.membercenter.idgen.IDPool;
import com.yimayhd.membercenter.manager.talent.TalentExamineManager;
import com.yimayhd.membercenter.mapper.ExamineDOMapper;
import com.yimayhd.membercenter.mapper.ExamineDetailDOMapper;
import com.yimayhd.membercenter.repo.MerchantRepo;
import com.yimayhd.user.client.domain.MerchantDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.common.message.Message;
import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.domain.merchant.MerchantItemCategoryDO;
import com.yimayhd.membercenter.client.enums.topic.MemberTopic;
import com.yimayhd.membercenter.client.result.MemResultSupport;
import com.yimayhd.membercenter.dao.MerchantItemCategoryDao;
import com.yimayhd.membercenter.mq.MsgSender;

public class MerchantItemCategoryManager {
    private static final Logger logger = LoggerFactory.getLogger(MerchantItemCategoryManager.class);

    @Autowired
    private MerchantItemCategoryDao merchantItemCategoryDao;
    @Autowired
    private MsgSender msgSender;
    @Autowired
    private TransactionTemplate transactionTemplate;
    @Autowired
    private MerchantRepo merchantRepo;
    @Autowired
    private TalentExamineManager talentExamineManager;
    @Autowired
    ExamineDOMapper examineDOMapper;
    @Autowired
    IDPool examineDetailIdPool;
    @Autowired
    ExamineDetailDOMapper examineDetailDOMapper;

    public List<MerchantItemCategoryDO> findMerchantItemCategoryByMerchant(int domainId, int sellerId) {
        return merchantItemCategoryDao.selectMerchantItemCategoriesByMerchant(domainId, sellerId);
    }

    public MemResultSupport saveMerchanItemCategories(final ExamineDO examineDO, final long[] categoryIds) {
        final MemResultSupport memResultSupport = new MemResultSupport();
        // 新增商家
        final MerchantDO merchantDO = ExamineConverter.examineToMerchant(examineDO);
        final AtomicBoolean isSuccess = new AtomicBoolean(false);

        final MemResult<ExamineDO> examineResult = talentExamineManager.queryMerchantExamineInfoById(examineDO);
        transactionTemplate.execute(new TransactionCallback<Void>() {
            @Override
            public Void doInTransaction(TransactionStatus transactionStatus) {
                // 无审核记录
                if (!examineResult.isSuccess() || (examineResult.isSuccess() && null == examineResult.getValue())) {
                    logger.info("saveMerchanItemCategories param:{} is null, update fail", JSONObject.toJSONString(examineDO));
                    memResultSupport.setReturnCode(MemberReturnCode.EXAMIN_DATA_ERROR);
                    return null;
                }
                // 判断是否处于审核进行中
                if (examineResult.getValue().getStatues() != ExamineStatus.EXAMIN_ING.getStatus()) {
                    // 非审核进行中状态无法进行审核
                    memResultSupport.setReturnCode(MemberReturnCode.DB_EXAMINE_NOT_ING);
                    logger.info("saveMerchanItemCategories param:{} error, isn't ing", JSONObject.toJSONString(examineDO),
                            MemberReturnCode.DB_EXAMINE_NOT_ING.getDesc());
                    return null;
                }

                examineDO.setId(examineResult.getValue().getId());
                int count = examineDOMapper.updateByPrimaryKey(examineDO);
                if (count != 1) {
                    transactionStatus.setRollbackOnly();
                    memResultSupport.setReturnCode(MemberReturnCode.DB_UPDATE_FAILED);
                    return null;
                }
                logger.info("saveMerchanItemCategories param:{} update success", JSONObject.toJSONString(examineDO));
                examineResult.getValue().setId(examineDetailIdPool.getNewId());
                examineResult.getValue().setReviewerId(examineDO.getReviewerId());
                examineResult.getValue().setExamineMes(examineDO.getExamineMes());
                examineResult.getValue().setStatues(examineDO.getStatues());
                examineResult.getValue().setGmtModified(new Date());
                // 保存审核明细表
                count = examineDetailDOMapper.insert(examineResult.getValue());
                if (count != 1) {
                    transactionStatus.setRollbackOnly();
                    memResultSupport.setReturnCode(MemberReturnCode.DB_WRITE_FAILED);
                    return null;
                }
                logger.info("saveMerchanItemCategories param:{} insertDetail success",JSONObject.toJSONString(examineResult.getValue()));

                MemResult<MerchantDO> memResult = merchantRepo.saveMerchant(merchantDO);
                if (!memResult.isSuccess()) {
                    transactionStatus.setRollbackOnly();
                    memResultSupport.setReturnCode(MemberReturnCode.MERCHANT_NOT_FOUND_ERROR);
                }
                logger.info("saveMerchanItemCategories param:{} insertMerchant success",JSONObject.toJSONString(memResult.getValue()));

                ArrayList<MerchantItemCategoryDO> merchantItemCategoryDOs = new ArrayList<>();
                for (long categoryId : categoryIds) {
                    MerchantItemCategoryDO merchantItemCategoryDO = new MerchantItemCategoryDO();
                    merchantItemCategoryDO.setDomainId(examineDO.getDomainId());
                    merchantItemCategoryDO.setGmtCreated(new Date());
                    merchantItemCategoryDO.setGmtModified(new Date());
                    merchantItemCategoryDO.setItemCategoryId(categoryId);
                    merchantItemCategoryDO.setSellerId(merchantDO.getSellerId());
                    merchantItemCategoryDO.setStatus(1);
                    merchantItemCategoryDOs.add(merchantItemCategoryDO);
                }
                isSuccess.set(merchantItemCategoryDao.saveMerchanItemCategories(merchantItemCategoryDOs));
                if (!isSuccess.get()) {
                    transactionStatus.setRollbackOnly();
                    memResultSupport.setReturnCode(MemberReturnCode.SCOPE_ITEM_CATEGORY_NOT_FOUND_ERROR);
                }
                logger.info("saveMerchanItemCategories param:{} insertMerchantItemCategoryDOs success",JSONObject.toJSONString(merchantItemCategoryDOs));
                return null;
            }
        });

        isSuccess.set(false);
        // 为商家授权、发送短信
        TransactionSendResult merchantItemCategorySendResult = msgSender.sendMessage(examineResult.getValue(), MemberTopic.MERCHANT_ROLE_BIND.getTopic(),
                MemberTopic.MERCHANT_ROLE_BIND.getTags(), new LocalTransactionExecuter() {
                    @Override
                    public LocalTransactionState executeLocalTransactionBranch(Message msg, Object arg) {
                        // 发送审核状态到mq消息
                        SendResult sendResult = msgSender.sendMessage(examineResult.getValue(),
                                MemberTopic.EXAMINE_RESULT.getTopic(), MemberTopic.EXAMINE_RESULT.getTags());
                        logger.info("sendMerchantApply par:{} sendMes return:{}", JSONObject.toJSONString(examineDO),
                                JSONObject.toJSONString(sendResult));
                        if (sendResult.getSendStatus() == SendStatus.SEND_OK) {
                            isSuccess.set(true);
                            return LocalTransactionState.COMMIT_MESSAGE;
                        }
                        return LocalTransactionState.UNKNOW;
                    }
                });

        if (merchantItemCategorySendResult.getSendStatus() != SendStatus.SEND_OK) {
            logger.error("send msg failed! topic={}, msg={},  result={}", MemberTopic.MERCHANT_ROLE_BIND.getTopic(), JSON.toJSONString(examineDO), JSON.toJSONString(merchantItemCategorySendResult));
            memResultSupport.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
        }

        if (!isSuccess.get()) {
            logger.error("send msg failed! topic={}, msg={},  result={}", MemberTopic.EXAMINE_RESULT.getTopic(), JSON.toJSONString(examineDO), "短信发送失败");
            memResultSupport.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
        }
        return memResultSupport;
    }
}
