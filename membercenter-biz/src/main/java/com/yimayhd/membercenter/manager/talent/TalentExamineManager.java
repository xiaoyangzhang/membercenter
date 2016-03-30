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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.yimayhd.fhtd.utils.PicFeatureUtil;
import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.domain.examine.ExamineDO;
import com.yimayhd.membercenter.client.dto.AccountDTO;
import com.yimayhd.membercenter.client.query.examine.ExaminePageQueryDTO;
import com.yimayhd.membercenter.client.result.MemPageResult;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.converter.ExamineConverter;
import com.yimayhd.membercenter.enums.ExamineStatus;
import com.yimayhd.membercenter.enums.ExamineType;
import com.yimayhd.membercenter.idgen.IDPool;
import com.yimayhd.membercenter.mapper.ExamineDOMapper;
import com.yimayhd.membercenter.repo.MerchantRepo;
import com.yimayhd.membercenter.repo.UserOptionRepo;
import com.yimayhd.membercenter.util.ParmCheckUtil;
import com.yimayhd.user.client.domain.MerchantDO;
import com.yimayhd.user.client.enums.UserOptions;

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
    MerchantRepo merchantRepo;

    // @Autowired
    // IDPool examineDoneIdPool;

    @Autowired
    IDPool examineDetailIdPool;
    
    
    @Autowired
    UserOptionRepo userOptionRepo;

    /**
     * 
     * 功能描述: <br>
     * 〈保存审核信息〉
     *
     * @param examinDO
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public MemResult<Boolean> submitMerchantExamineInfo(ExamineDO examineDO) {
        MemResult<Boolean> result = new MemResult<Boolean>();
        try {
            // do 需要修改
            ExamineDO examine = examineDOMapper.selectBySellerId(examineDO);
            if (null != examine) {
                // 判断是否已经审核通过
                if (examine.getStatues() == ExamineStatus.EXAMIN_OK.getId()) {
                    result.setReturnCode(MemberReturnCode.DB_EXAMINE_FAILED);
                    return result;
                }
                examineDOMapper.updateByPrimaryKey(unionAll(examineDO, examine));
            } else {
                examineDO.setId(examineDetailIdPool.getNewId());
                examineDOMapper.insert(examineDO);
            }
        } catch (Exception e) {
            logger.error("submitMerchantExaminInfo par:{} insert error:{}", JSONObject.toJSONString(examineDO), e);
            result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
        }
        return result;
    }
    
    /**
     *
     * 功能描述: <br>
     * 〈对象取并集〉
     *  分页提交新增
     *  还好代码灵活
     * @param examineMater
     * @param examineSlave
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private static ExamineDO unionAll(ExamineDO examineMater, ExamineDO examineSlave){
        examineMater.setId(examineSlave.getId());
        // 图片
        Map<String, String> pictureMasterMap = PicFeatureUtil.fromString(examineMater.getPicturesUrl());
        // 图片
        Map<String, String> pictureSlaveMap = PicFeatureUtil.fromString(examineSlave.getPicturesUrl());
        //新覆盖旧
        pictureSlaveMap.putAll(pictureMasterMap);
        examineMater.setPicturesUrl(PicFeatureUtil.toString(pictureSlaveMap));
        // 信息明细
        Map<String, String> featureMasterMap = PicFeatureUtil.fromString(examineMater.getFeature());
        // 信息明细
        Map<String, String> featureSlaveMap = PicFeatureUtil.fromString(examineSlave.getFeature());
        featureSlaveMap.putAll(featureMasterMap);
        examineMater.setFeature(PicFeatureUtil.toString(featureSlaveMap));
        // 达人技能
        Map<String, String> certificateMasterMap = PicFeatureUtil.fromString(examineMater.getCertificate());
        // 达人技能
        Map<String, String> certificateSlaveMap = PicFeatureUtil.fromString(examineSlave.getCertificate());
        certificateSlaveMap.putAll(certificateMasterMap);
        examineMater.setCertificate(PicFeatureUtil.toString(certificateSlaveMap));
        
        examineMater.setGmtModified(new Date());
        return examineMater;
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
            // do 需要修改
            ExamineDO examineDO = examineDOMapper.selectBySellerId(examineQueryDO);
            // if (isOk) {
            // examineDO = examineDoneDOMapper.selectBySellerId(examineQueryDO);
            // } else {
            // examineDO = examineDOMapper.selectBySellerId(examineQueryDO);
            // }
            if (null == examineDO) {
                result.setReturnCode(MemberReturnCode.MERCHANT_NOT_FOUND_ERROR);
                logger.error("queryMerchantExaminInfo parm:{} return null", JSONObject.toJSONString(examineQueryDO));
            } else {
                result.setValue(examineDO);
            }
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
            if (0 >= count) {
                baseResult.setReturnCode(MemberReturnCode.EXAMIN_DATA_ERROR);
                logger.info("queryMerchantExaminByPage param:{}  queryMerchantExaminCount is zero",
                        JSONObject.toJSONString(examinQueryDTO));
                return baseResult;
            }
            // 分页查询
            List<ExamineDO> examinList = examineDOMapper.queryMerchantExaminByPage(examinQueryDTO);
            if (ParmCheckUtil.checkListNull(examinList)) {
                baseResult.setReturnCode(MemberReturnCode.EXAMIN_DATA_ERROR);
                logger.info("queryMerchantExaminByPage param:{}  queryMerchantExaminByPage is null",
                        JSONObject.toJSONString(examinQueryDTO));
                return baseResult;
            }
            baseResult.setList(examinList);
            baseResult.setTotalCount(count);
            baseResult.setPageNo(examinQueryDTO.getPageNo());
            logger.debug("queryMerchantExaminByPage param:{} return success", JSONObject.toJSONString(examinQueryDTO));
            return baseResult;
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
    public MemResult<Boolean> updateMerchantExamineById(ExamineDO examineDO) {
        MemResult<Boolean> baseResult = new MemResult<Boolean>();
        try {
            ExamineDO examine = examineDOMapper.selectBySellerId(examineDO);
            // 无审核记录
            if (null == examine) {
                logger.info("updateMerchantExaminById param:{} is null, update fail",
                        JSONObject.toJSONString(examineDO));
                baseResult.setReturnCode(MemberReturnCode.DB_UPDATE_FAILED);
                return baseResult;
            }
            // 判断是否已经审核通过
            if (examine.getStatues() == ExamineStatus.EXAMIN_OK.getId()) {
                baseResult.setReturnCode(MemberReturnCode.DB_EXAMINE_FAILED);
                return baseResult;
            }
            examineDO.setId(examine.getId());
            examineDOMapper.updateByPrimaryKey(examineDO);
            // 审核通过保存基本信息
            if (examineDO.getStatues() == ExamineStatus.EXAMIN_OK.getId()) {
                // examine.setId(examineDoneIdPool.getNewId());
                // examine.setGmtCreated(new Date());
                // examine.setGmtModified(new Date());
                // //保存至审核通过表
                // examineDoneDOMapper.insert(examine);
                MerchantDO merchantDO = ExamineConverter.examineToMerchant(examineDO);
                // 初始化店铺信息
                MemResult<MerchantDO> memResult = merchantRepo.saveMerchant(merchantDO);
                logger.info("updateMerchantExaminById param:{} saveMerchant return:{}",
                            JSONObject.toJSONString(merchantDO), JSONObject.toJSONString(memResult.getReturnCode()));
                //更新user option
                addUserOption(examineDO.getSellerId(), examineDO.getType());
            }
            logger.info("updateMerchantExaminById param:{} update success", JSONObject.toJSONString(examineDO));
            // baseResult.setReturnCode(MemberReturnCode.DB_UPDATE_FAILED);
            return baseResult;
        } catch (Exception e) {
            logger.error("updateMerchantExaminById param:{} error, mes is:{}", JSONObject.toJSONString(examineDO), e);
            baseResult.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
        }
        return baseResult;
    }

    
    /**
     * 
     * 功能描述: <br>
     * 〈更新达人或者商铺状态〉
     *
     * @param userId
     * @param type
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public MemResult<Boolean> addUserOption(long userId, int type){
        List<UserOptions> userOptionsList = new ArrayList<UserOptions>();
        //达人
        if(ExamineType.TALENT.getId() == type){
            userOptionsList.add(UserOptions.TRAVEL_KA);
            //达人默认大V
            userOptionsList.add(UserOptions.CERTIFICATED);
        }else{
            userOptionsList.add(UserOptions.COMMERCIAL_TENANT);
        }
        MemResult<Boolean> optionsResult = userOptionRepo.addUserOption(userId, userOptionsList);
        logger.info("addUserOption userId:{}, list.size:{} add return:{}", userId, userOptionsList.size(), JSONObject.toJSONString(optionsResult));
        return optionsResult;
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
            if (null == examine || examine.getStatues() != ExamineStatus.EXAMIN_OK.getId()) {
                logger.info("updateMerchantAccountInfoById param:{} is null, update fail",
                        JSONObject.toJSONString(examineDO));
                baseResult.setReturnCode(MemberReturnCode.EXAMIN_DATA_ERROR);
                return baseResult;
            } else {
                // 数据转换
                ExamineDO accoutExamine = ExamineConverter.accountToExamine(examine, accountDTO);
                examineDOMapper.updateByPrimaryKey(accoutExamine);
                logger.info("updateMerchantAccountInfoById param:{} update success",
                        JSONObject.toJSONString(accountDTO));
                return baseResult;
            }
        } catch (Exception e) {
            logger.error("updateMerchantAccountInfoById param:{} error, mes is:{}", JSONObject.toJSONString(accountDTO),
                    e);
            baseResult.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
        }
        return baseResult;
    }

}
