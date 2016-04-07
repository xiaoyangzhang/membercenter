/*
 * FileName: TalentInfoDealServiceImpl.java
 * Author:   liubb
 * Date:     2016年3月23日 上午10:21:44
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimayhd.membercenter.service.client.back;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.domain.BankDO;
import com.yimayhd.membercenter.client.domain.CertificatesDO;
import com.yimayhd.membercenter.client.domain.examine.ExamineDO;
import com.yimayhd.membercenter.client.dto.AccountDTO;
import com.yimayhd.membercenter.client.dto.BankInfoDTO;
import com.yimayhd.membercenter.client.dto.TalentInfoDTO;
import com.yimayhd.membercenter.client.query.InfoQueryDTO;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.service.back.TalentInfoDealService;
import com.yimayhd.membercenter.converter.ExamineConverter;
import com.yimayhd.membercenter.manager.talent.TalentBackInfoManager;
import com.yimayhd.membercenter.manager.talent.TalentExamineManager;
import com.yimayhd.membercenter.util.ParmCheckUtil;
import com.yimayhd.user.client.enums.CertificateOption;
import com.yimayhd.user.client.enums.MerchantOption;
import com.yimayhd.user.client.enums.ServiceTypeOption;

/**
 * 〈一句话功能简述〉<br>
 * 〈 达人后台基本信息实现类〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class TalentInfoDealServiceImpl implements TalentInfoDealService {

    private static final Logger logger = LoggerFactory.getLogger(TalentInfoDealServiceImpl.class);

    @Autowired
    TalentBackInfoManager talentBackInfoManager;

    @Autowired
    TalentExamineManager talentExamineManager;

    /*
     * (non-Javadoc)
     * @see
     * com.yimayhd.membercenter.client.service.talent.TalentInfoDealService#updateTalentInfo(com.yimayhd.membercenter.
     * client.domain.talent.TalentInfoDO)
     */
    @Override
    public MemResult<Boolean> updateTalentInfo(TalentInfoDTO talentInfoDTO) {
        MemResult<Boolean> baseResult = new MemResult<Boolean>();
        try {
            // 参数校验
            if (ParmCheckUtil.checkUpdateTalentInfoNull(talentInfoDTO)) {
                baseResult.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
                logger.info("updateTalentInfo parm:{} isn't ok", JSONObject.toJSONString(talentInfoDTO));
                return baseResult;
            }
            return talentBackInfoManager.updateTalentBackInfo(talentInfoDTO.getTalentInfoDO(),
                    talentInfoDTO.getPictureTextDTO(), talentInfoDTO.getDomainId());
        } catch (Exception e) {
            logger.error("updateTalentInfo par:{} error:{}", JSONObject.toJSONString(talentInfoDTO), e);
            baseResult.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
        }
        return baseResult;
    }

    /*
     * (non-Javadoc)
     * @see com.yimayhd.membercenter.client.service.talent.TalentInfoDealService#queryTalentServiceType()
     */
    @Override
    public MemResult<List<CertificatesDO>> queryTalentServiceType() {
        MemResult<List<CertificatesDO>> result = new MemResult<List<CertificatesDO>>();
        List<CertificatesDO> certificatesDOs = new ArrayList<CertificatesDO>();
        for (ServiceTypeOption option : ServiceTypeOption.values()) {
            CertificatesDO certificatesDO = new CertificatesDO();
            certificatesDO.setId(Integer.valueOf(option.getCode()));
            certificatesDO.setName(option.getDesc());
            certificatesDO.setType(Integer.valueOf(MerchantOption.TALENT.getCode()));
            certificatesDOs.add(certificatesDO);
        }
        result.setValue(certificatesDOs);
        return result;
    }

    /*
     * (non-Javadoc)
     * @see com.yimayhd.membercenter.client.service.talent.TalentInfoDealService#queryTalentCertificates()
     */
    @Override
    public MemResult<List<CertificatesDO>> queryTalentCertificates() {
        MemResult<List<CertificatesDO>> result = new MemResult<List<CertificatesDO>>();
        List<CertificatesDO> certificatesDOs = new ArrayList<CertificatesDO>();
        for (CertificateOption option : CertificateOption.values()) {
            CertificatesDO certificatesDO = new CertificatesDO();
            certificatesDO.setId(Integer.valueOf(option.getCode()));
            certificatesDO.setName(option.getDesc());
            certificatesDO.setType(Integer.valueOf(MerchantOption.TALENT.getCode()));
            certificatesDOs.add(certificatesDO);
        }
        result.setValue(certificatesDOs);
        return result;
    }

    /*
     * (non-Javadoc)
     * @see com.yimayhd.membercenter.client.service.talent.TalentInfoDealService#queryTalentInfoByUserId(long, int)
     */
    @Override
    public MemResult<TalentInfoDTO> queryTalentInfoByUserId(long userId, int domainId) {
        MemResult<TalentInfoDTO> baseResult = new MemResult<TalentInfoDTO>();
        try {
            // 参数校验
            if (ParmCheckUtil.checkUserIdAndDomainId(userId, domainId)) {
                baseResult.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
                logger.info("queryTalentInfoByUserId userId:{}, domainId:{} isn't ok", userId, domainId);
                return baseResult;
            }
            return talentBackInfoManager.queryTalentBackInfo(userId, domainId);
        } catch (Exception e) {
            logger.error("queryTalentInfoByUserId par:{} error:{}", userId, e);
            baseResult.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
        }
        return baseResult;
    }

    /*
     * (non-Javadoc)
     * @see com.yimayhd.membercenter.client.service.talent.TalentInfoDealService#queryMerchantAccountInfoById()
     */
    @Override
    public MemResult<AccountDTO> queryMerchantAccountInfoById(InfoQueryDTO infoQueryDTO) {
        MemResult<AccountDTO> result = new MemResult<AccountDTO>();
        try {
            if (ParmCheckUtil.checkQueryDTO(infoQueryDTO)) {
                result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
                logger.info("queryMerchantCountInfoById par:{} is error", JSONObject.toJSONString(infoQueryDTO));
                return result;
            }
            // 数据转换
            ExamineDO examineDO = ExamineConverter.examineQueryToDO(infoQueryDTO);
            MemResult<ExamineDO> queryResult = talentExamineManager.queryMerchantExamineInfoById(examineDO);
            if (queryResult.isSuccess()) {
                // 数据转换
                AccountDTO accountDTO = ExamineConverter.examineToAccount(queryResult.getValue());
                result.setValue(accountDTO);
                logger.info("queryMerchantCountInfoById par:{} return success", JSONObject.toJSONString(infoQueryDTO));
            } else {
                result.setReturnCode(queryResult.getReturnCode());
                logger.info("queryMerchantCountInfoById par:{} return error:{}", JSONObject.toJSONString(infoQueryDTO),
                        queryResult.getErrorMsg());
            }
        } catch (Exception e) {
            logger.error("queryMerchantAccountInfoById par:{} error:{}", JSONObject.toJSONString(infoQueryDTO), e);
            result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * @see com.yimayhd.membercenter.client.service.talent.TalentInfoDealService#updateMerchantAccountInfo()
     */
    @Override
    public MemResult<Boolean> updateMerchantAccountInfo(AccountDTO accountDTO) {
        MemResult<Boolean> result = new MemResult<Boolean>();
        try {
            // 参数校验
            if (ParmCheckUtil.checkAccountInfoDTO(accountDTO)) {
                result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
                logger.info("updateMerchantAccountInfo par:{} is error", JSONObject.toJSONString(accountDTO));
                return result;
            }
            return talentExamineManager.updateMerchantAccountInfoById(accountDTO);
        } catch (Exception e) {
            logger.error("updateMerchantAccountInfo par:{} error:{}", JSONObject.toJSONString(accountDTO), e);
            result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * @see com.yimayhd.membercenter.client.service.talent.TalentInfoDealService#queryBankList()
     */
    @Override
    public MemResult<List<BankInfoDTO>> queryBankList() {
        MemResult<List<BankInfoDTO>> result = new MemResult<List<BankInfoDTO>>();
        MemResult<List<BankDO>> bankResult = talentBackInfoManager.queryBankInfo();
        if (bankResult.isSuccess()) {
            if (!ParmCheckUtil.checkListNull(bankResult.getValue())) {
                List<BankInfoDTO> bankList = new ArrayList<BankInfoDTO>();
                for (BankDO bankDO : bankResult.getValue()) {
                    BankInfoDTO bankInfoDTO = new BankInfoDTO();
                    bankInfoDTO.setBankId(bankDO.getBankId());
                    bankInfoDTO.setBankName(bankDO.getBankName());
                    bankList.add(bankInfoDTO);
                }
                result.setValue(bankList);
            }
        } else {
            result.setReturnCode(bankResult.getReturnCode());
        }
        return result;
    }
}
