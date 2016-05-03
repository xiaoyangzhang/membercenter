/*
 * FileName: TalentExaminManager.java
 * Author:   liubb
 * Date:     2016年3月25日 下午5:22:20
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimayhd.membercenter.manager.talent;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.domain.examine.ExamineDO;
import com.yimayhd.membercenter.client.dto.AccountDTO;
import com.yimayhd.membercenter.client.enums.topic.MemberTopic;
import com.yimayhd.membercenter.client.query.examine.ExaminePageQueryDTO;
import com.yimayhd.membercenter.client.result.MemPageResult;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.converter.ExamineConverter;
import com.yimayhd.membercenter.enums.ExamineStatus;
import com.yimayhd.membercenter.idgen.IDPool;
import com.yimayhd.membercenter.mapper.ExamineDOMapper;
import com.yimayhd.membercenter.mapper.ExamineDetailDOMapper;
import com.yimayhd.membercenter.mq.MsgSender;
import com.yimayhd.membercenter.repo.MerchantRepo;
import com.yimayhd.membercenter.util.MapUnionUtil;
import com.yimayhd.membercenter.util.ParmCheckUtil;

/**
 * 〈一句话功能简述〉<br>
 * 〈审核信息manager〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class TalentExamineManager {

    private static final Logger logger = LoggerFactory.getLogger(TalentInfoManager.class);

    @Autowired
    ExamineDOMapper examineDOMapper;

    @Autowired
    ExamineDetailDOMapper examineDetailDOMapper;

    @Autowired
    MerchantRepo merchantRepo;

    @Autowired
    IDPool examineIdPool;

    @Autowired
    IDPool examineDetailIdPool;

    @Autowired
    MsgSender msgSender;

    /**
     * 
     * 功能描述: <br>
     * 〈保存审核信息〉
     *
     * @param examinDO
     * @param pageNo
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public MemResult<Boolean> submitMerchantExamineInfo(ExamineDO examineDO) {
        MemResult<Boolean> result = new MemResult<Boolean>();
        try {
            result = checkSellerNameIsExist(examineDO.getSellerName(), examineDO.getDomainId());
            // 判断sellerName是否已经存在
            if (!result.isSuccess()) {
                result.setReturnCode(MemberReturnCode.DB_SELLERNAME_FAILED);
                logger.info("submitMerchantExaminInfo par:{} sellerName exists", JSONObject.toJSONString(examineDO));
                return result;
            }
            // do 判断是否已经存在
            ExamineDO examine = examineDOMapper.selectBySellerId(examineDO);
            if (null != examine) {
                // 判断是否已经审核通过
                if (examine.getStatues() == ExamineStatus.EXAMIN_OK.getStatus()) {
                    result.setReturnCode(MemberReturnCode.DB_EXAMINE_FAILED);
                    logger.info("submitMerchantExaminInfo par:{} has already checked",
                            JSONObject.toJSONString(examineDO));
                    return result;
                }
                examineDOMapper.updateByPrimaryKey(MapUnionUtil.unionAll(examineDO, examine));
            } else {
                examineDO.setId(examineIdPool.getNewId());
                examineDOMapper.insert(examineDO);
            }
            result.setValue(Boolean.TRUE);
        } catch (Exception e) {
            logger.error("submitMerchantExaminInfo par:{} insert error:{}", JSONObject.toJSONString(examineDO), e);
            result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
        }
        return result;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈更新状态为审核中〉
     *
     * @param examineDO
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public MemResult<Boolean> changeExamineStatus(ExamineDO examineDO) {
        MemResult<Boolean> result = new MemResult<Boolean>();
        try {
            // do 判断是否已经存在
            ExamineDO examine = examineDOMapper.selectBySellerId(examineDO);
            if (null != examine) {
                if (examine.getStatues() == ExamineStatus.EXAMIN_OK.getStatus()) {
                    // result.setReturnCode(MemberReturnCode.DB_EXAMINE_FAILED);
                    logger.info("changeExamineStatus par:{} has already checked", JSONObject.toJSONString(examineDO));
                    result.setValue(Boolean.TRUE);
                    return result;
                }
                examineDO.setId(examine.getId());
                examineDO.setStatues(ExamineStatus.EXAMIN_ING.getStatus());
                examineDO.setGmtModified(new Date());
                examineDOMapper.updateByPrimaryKey(examineDO);
                logger.info("changeExamineStatus par:{} success", JSONObject.toJSONString(examineDO));
            } else {
                result.setReturnCode(MemberReturnCode.EXAMIN_DATA_ERROR);
                logger.info("changeExamineStatus par:{} has not data, update fail", JSONObject.toJSONString(examineDO));
            }
            result.setValue(Boolean.TRUE);
        } catch (Exception e) {
            logger.error("changeExamineStatus par:{} error:{}", JSONObject.toJSONString(examineDO), e);
            result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
        }
        return result;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈根据sellerName检查是否已经存在〉
     *
     * @param sellerName
     * @param domainId
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public MemResult<Boolean> checkSellerNameIsExist(String sellerName, int domainId) {
        MemResult<Boolean> merchantListResult = new MemResult<Boolean>();
        if (StringUtils.isNotBlank(sellerName)) {
            merchantListResult = merchantRepo.getMerchantList(sellerName, domainId);
        }
        return merchantListResult;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈根据userId查询审核信息〉
     *
     * @param examineQueryDO
     * @param isOk true查询审核通过信息
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public MemResult<ExamineDO> queryMerchantExamineInfoById(ExamineDO examineQueryDO) {
        MemResult<ExamineDO> result = new MemResult<ExamineDO>();
        try {
            ExamineDO examineDO = null;
            // do 需要修改
            if (ParmCheckUtil.MIN_CODE < examineQueryDO.getId()) {
                examineDO = examineDOMapper.selectById(examineQueryDO);
            } else {
                examineDO = examineDOMapper.selectBySellerId(examineQueryDO);
            }
            result.setValue(examineDO);
            logger.info("queryMerchantExaminInfo parm:{} return success", JSONObject.toJSONString(examineQueryDO));
        } catch (Exception e) {
            result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
            logger.error("queryMerchantExaminInfo  parm:{} error:{}", JSONObject.toJSONString(examineQueryDO), e);
        }
        return result;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈分页查询基本信息〉
     *
     * @param examinQueryDTO
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public MemPageResult<ExamineDO> queryMerchantExamineByPage(ExaminePageQueryDTO examinQueryDTO) {
        MemPageResult<ExamineDO> baseResult = new MemPageResult<ExamineDO>();
        try {
            // 查询总数
            int count = examineDOMapper.queryMerchantExaminCount(examinQueryDTO);
            if (ParmCheckUtil.MIN_CODE >= count) {
                // baseResult.setReturnCode(MemberReturnCode.EXAMIN_DATA_ERROR);
                logger.info("queryMerchantExaminByPage param:{}  queryMerchantExaminCount is zero",
                        JSONObject.toJSONString(examinQueryDTO));
                return baseResult;
            }
            // 分页查询
            List<ExamineDO> examinList = examineDOMapper.queryMerchantExaminByPage(examinQueryDTO);
            baseResult.setList(examinList);
            baseResult.setTotalCount(count);
            baseResult.setPageNo(examinQueryDTO.getPageNo());
            baseResult.setHasNext(count > examinQueryDTO.getPageNo() * examinQueryDTO.getPageSize());
            logger.info("queryMerchantExaminByPage param:{} return success", JSONObject.toJSONString(examinQueryDTO));
        } catch (Exception e) {
            logger.error("queryMerchantExaminByPage param:{} error, mes is:{}", JSONObject.toJSONString(examinQueryDTO),
                    e);
            baseResult.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
        }
        return baseResult;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈更新审核状态〉
     *
     * @param examinDO
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public MemResult<Boolean> dealExamineInfo(ExamineDO examineDO) {
        MemResult<Boolean> baseResult = new MemResult<Boolean>();
        // 查询是否已经存在审核记录
        MemResult<ExamineDO> examineResult = queryMerchantExamineInfoById(examineDO);
        try {
            // 无审核记录
            if (!examineResult.isSuccess() || (examineResult.isSuccess() && null == examineResult.getValue())) {
                logger.info("dealExamineInfo param:{} is null, update fail", JSONObject.toJSONString(examineDO));
                baseResult.setReturnCode(MemberReturnCode.EXAMIN_DATA_ERROR);
                return baseResult;
            }
            // 判断是否处于审核进行中
            if (examineResult.getValue().getStatues() != ExamineStatus.EXAMIN_ING.getStatus()) {
                // 判断是否已经审核通过
                // if (examineResult.getValue().getStatues() == ExamineStatus.EXAMIN_OK.getStatus()) {
                // if (examineDO.getStatues() == ExamineStatus.EXAMIN_OK.getStatus()) {
                // baseResult.setValue(Boolean.TRUE);
                // logger.info("dealExamineInfo param:{} has already examine ok",
                // JSONObject.toJSONString(examineDO));
                // } else {
                // 审核通过后无法再次修改状态
                // baseResult.setReturnCode(MemberReturnCode.DB_EXAMINE_REFUSE);
                // logger.info("dealExamineInfo param:{} has already examine ok, couldn't change status",
                // JSONObject.toJSONString(examineDO));
                // }
                // return baseResult;
                // }
                // 非审核进行中状态无法进行审核
                baseResult.setReturnCode(MemberReturnCode.DB_EXAMINE_NOT_ING);
                logger.info("dealExamineInfo param:{} error, isn't ing", JSONObject.toJSONString(examineDO),
                        MemberReturnCode.DB_EXAMINE_NOT_ING.getDesc());
                return baseResult;
            }
            examineDO.setId(examineResult.getValue().getId());
            examineDOMapper.updateByPrimaryKey(examineDO);
            logger.info("dealExamineInfo param:{} update success", JSONObject.toJSONString(examineDO));
            examineResult.getValue().setId(examineDetailIdPool.getNewId());
            examineResult.getValue().setReviewerId(examineDO.getReviewerId());
            examineResult.getValue().setExamineMes(examineDO.getExamineMes());
            examineResult.getValue().setStatues(examineDO.getStatues());
            examineResult.getValue().setGmtModified(new Date());
            // 保存审核明细表
            examineDetailDOMapper.insert(examineResult.getValue());
            logger.info("dealExamineInfo param:{} insertDetail success",
                    JSONObject.toJSONString(examineResult.getValue()));
            baseResult.setValue(Boolean.TRUE);
        } catch (Exception e) {
            logger.error("dealExamineInfo param:{} error, mes is:{}", JSONObject.toJSONString(examineDO), e);
            baseResult.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
        } finally {
            if (baseResult.isSuccess()) {
                // 发送审核状态到mq消息
                SendResult sendResult = msgSender.sendMessage(examineResult.getValue(),
                        MemberTopic.EXAMINE_RESULT.getTopic(), MemberTopic.EXAMINE_RESULT.getTags());
                logger.info("dealExamineInfo par:{} sendMes return:{}", JSONObject.toJSONString(examineDO),
                        JSONObject.toJSONString(sendResult));
            }
        }
        return baseResult;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈更新账号信息〉
     *
     * @param examineDO
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public MemResult<Boolean> updateMerchantAccountInfoById(AccountDTO accountDTO) {
        MemResult<Boolean> baseResult = new MemResult<Boolean>();
        try {
            ExamineDO examineDO = new ExamineDO();
            examineDO.setSellerId(accountDTO.getSellerId());
            examineDO.setDomainId(accountDTO.getDomainId());
            examineDO.setType(accountDTO.getType());
            ExamineDO examine = examineDOMapper.selectBySellerId(examineDO);
            // 无审核记录
            if (null == examine || examine.getStatues() != ExamineStatus.EXAMIN_OK.getStatus()) {
                logger.info("updateMerchantAccountInfoById param:{} is null, update fail",
                        JSONObject.toJSONString(examineDO));
                baseResult.setReturnCode(MemberReturnCode.EXAMIN_DATA_ERROR);
                // return baseResult;
            } else {
                // 数据转换
                ExamineDO accoutExamine = ExamineConverter.accountToExamine(examine, accountDTO);
                examineDOMapper.updateByPrimaryKey(accoutExamine);
                logger.info("updateMerchantAccountInfoById param:{} update success",
                        JSONObject.toJSONString(accountDTO));
                // return baseResult;
            }
        } catch (Exception e) {
            logger.error("updateMerchantAccountInfoById param:{} error, mes is:{}", JSONObject.toJSONString(accountDTO),
                    e);
            baseResult.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
        }
        return baseResult;
    }
}
