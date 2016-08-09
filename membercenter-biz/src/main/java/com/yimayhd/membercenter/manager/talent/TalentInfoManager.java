/*
 * FileName: TalentInfoManager.java
 * Author:   liubb
 * Date:     2016年3月15日 下午4:56:46
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimayhd.membercenter.manager.talent;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.domain.merchant.MerchantInfoDO;
import com.yimayhd.membercenter.client.domain.talent.TalentInfoDO;
import com.yimayhd.membercenter.client.query.MerchantQueryDTO;
import com.yimayhd.membercenter.client.query.talent.TalentQueryDTO;
import com.yimayhd.membercenter.client.result.MemPageResult;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.converter.MerchantConverter;
import com.yimayhd.membercenter.converter.TalentConverter;
import com.yimayhd.membercenter.repo.MerchantRepo;
import com.yimayhd.membercenter.service.api.talent.TalentMemberApiImpl;
import com.yimayhd.snscenter.client.dto.SnsCountDTO;
import com.yimayhd.snscenter.client.result.BaseResult;
import com.yimayhd.snscenter.client.service.SnsFollowService;
import com.yimayhd.user.client.dto.MerchantUserDTO;
import com.yimayhd.user.client.enums.MerchantOption;
import com.yimayhd.user.client.enums.SequenceEnum;
import com.yimayhd.user.client.enums.ServiceTypeOption;
import com.yimayhd.user.client.query.MerchantPageQuery;

/**
 * 〈一句话功能简述〉<br>
 * 〈 达人信息manager〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class TalentInfoManager {

    private static final Logger logger = LoggerFactory.getLogger(TalentMemberApiImpl.class);

    @Autowired
    MerchantRepo merchantRepo;
    
    @Autowired
    SnsFollowService snsFollowService;

    /**
     * 
     * 功能描述: <br>
     * 〈获取达人基本信息〉
     *
     * @param talentId
     * @param domainId
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public MemResult<MerchantUserDTO> queryTalentInfo(long talentId, long domainId) {
        // 获取达人基本信息
        MemResult<MerchantUserDTO> baseResult = merchantRepo.queryMerchantBySellerId(talentId, domainId);
        logger.info("queryTalentInfo talentId:{} domainId:{} return:{}", talentId, domainId, baseResult.isSuccess());
        return baseResult;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈获取达人列表〉
     *
     * @param talentQueryDTO
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public MemPageResult<TalentInfoDO> queryTalentList(TalentQueryDTO talentQueryDTO) {
        MemPageResult<TalentInfoDO> pageResult = new MemPageResult<TalentInfoDO>();
        logger.info("queryTalentList param:{} begin -->", JSONObject.toJSONString(talentQueryDTO));
        MerchantPageQuery merchantPageQuery = new MerchantPageQuery();
        merchantPageQuery.setOption(MerchantOption.TALENT.getOption());
        merchantPageQuery.setDomainId(talentQueryDTO.getDomainId());
        merchantPageQuery.setPageNo(talentQueryDTO.getPageNo());
        merchantPageQuery.setPageSize(talentQueryDTO.getPageSize());
        merchantPageQuery.setPicUrlsNotNullAndTitleNotNull(true);
        if (null != talentQueryDTO.isSortType()) {
            merchantPageQuery.setServiceSort(
                    talentQueryDTO.isSortType() ? SequenceEnum.DESC.getType() : SequenceEnum.ASC.getType());
        }
        // 根据参数选择查询实现
        if (StringUtils.isNotBlank(talentQueryDTO.getSearchWord())) {
          //  merchantPageQuery.setTitle(talentQueryDTO.getSearchWord().toUpperCase());
        	merchantPageQuery.setKeyWords(talentQueryDTO.getSearchWord());
        } else {
            // 达人类型是否为空
            if (StringUtils.isNotBlank(talentQueryDTO.getTagId())) {
                merchantPageQuery.setServiceType(ServiceTypeOption.valueOfCode(talentQueryDTO.getTagId()).getOption());
            }
        }
        MemPageResult<MerchantUserDTO> result = merchantRepo.getMerchantUserList(merchantPageQuery);
        pageResult = TalentConverter.merchantListToTalentList(pageResult, result);
        logger.info("queryTalentList param:{} return:{}", JSONObject.toJSONString(talentQueryDTO), result.isSuccess());
        return pageResult;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈查询店铺列表〉
     *
     * @param merchantQueryDTO
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public MemPageResult<MerchantInfoDO> queryMerchantList(MerchantQueryDTO merchantQueryDTO) {
        MemPageResult<MerchantInfoDO> baseResult = new MemPageResult<MerchantInfoDO>();
        MerchantPageQuery merchantPageQuery = new MerchantPageQuery();
        merchantPageQuery.setOption(MerchantOption.valueOfName(merchantQueryDTO.getMerchantType()).getOption());
        merchantPageQuery.setDomainId(merchantQueryDTO.getDomainId());
        merchantPageQuery.setPageNo(merchantQueryDTO.getPageNo());
        merchantPageQuery.setPageSize(merchantQueryDTO.getPageSize());
        MemPageResult<MerchantUserDTO> result = merchantRepo.getMerchantUserList(merchantPageQuery);
        baseResult = MerchantConverter.merchant(baseResult, result);
        logger.info("queryMerchantList param:{} return:{}", JSONObject.toJSONString(merchantQueryDTO),
                JSONObject.toJSONString(baseResult.getReturnCode()));
        return baseResult;
    }
    
    /**
     * 查询粉丝数、关注数、UGC数量
     * @param userId
     * @return
     */
    public MemResult<SnsCountDTO> getSnsCountInfo(long userId,long theUserId){
    	MemResult<SnsCountDTO> result = new MemResult<SnsCountDTO>();
    	BaseResult<SnsCountDTO> queryResult = snsFollowService.getSnsCountDTO(userId,theUserId);
    	
    	if(!queryResult.isSuccess()){
    		logger.error("snsFollowService.getSnsCountDTO error:queryResult={},userId={}",JSONObject.toJSONString(queryResult),JSONObject.toJSONString(userId));
    		result.setReturnCode(MemberReturnCode.DUBBO_ERROR);
    	}else{
    		result.setValue(queryResult.getValue());
    	}
    	
    	
    	return result;
    }
}
