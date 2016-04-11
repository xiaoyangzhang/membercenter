/*
 * FileName: MerchantRepo.java
 * Author:   liubb
 * Date:     2016年3月16日 上午11:23:15
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimayhd.membercenter.repo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.result.MemPageResult;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.util.ParmCheckUtil;
import com.yimayhd.user.client.domain.MerchantDO;
import com.yimayhd.user.client.dto.MerchantDTO;
import com.yimayhd.user.client.dto.MerchantUserDTO;
import com.yimayhd.user.client.query.MerchantPageQuery;
import com.yimayhd.user.client.query.MerchantQuery;
import com.yimayhd.user.client.result.BasePageResult;
import com.yimayhd.user.client.result.BaseResult;
import com.yimayhd.user.client.service.MerchantService;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class MerchantRepo {

    private static final Logger logger = LoggerFactory.getLogger(MerchantRepo.class);

    @Autowired
    MerchantService merchantService;

    /**
     * 
     * 功能描述: <br>
     * 〈根据sellerId domainId获取店铺基本信息〉
     *
     * @param sellerId
     * @param domainId
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public MemResult<MerchantUserDTO> queryMerchantBySellerId(long sellerId, long domainId) {
        MemResult<MerchantUserDTO> baseResult = new MemResult<MerchantUserDTO>();
        try {
            BaseResult<MerchantUserDTO> result = merchantService.getMerchantAndUserBySellerId(sellerId, domainId);
            if (result.isSuccess() && null != result.getValue() && null != result.getValue().getMerchantDO()
                    && null != result.getValue().getUserDO()) {
                baseResult.setValue(result.getValue());
                logger.debug("getMerchantBySellerId sellerId:{} domainId:{} query seller info success", sellerId,
                        domainId);
                return baseResult;
            }
            baseResult.setReturnCode(MemberReturnCode.MEMBER_NOT_FOUND);
            logger.info("sellerId:{} domainId:{} query seller info return {}", sellerId, domainId,
                    JSONObject.toJSONString(result));
            return baseResult;
        } catch (Exception e) {
            baseResult.setReturnCode(MemberReturnCode.DUBBO_ERROR);
            logger.error("getMerchantBySellerId sellerId:{} domainId:{} query seller info dubbo error, mes is {}",
                    sellerId, domainId, e);
        }
        return baseResult;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈根据服务类型查询店铺列表〉
     *
     * @param merchantPageQuery
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public MemPageResult<MerchantUserDTO> getMerchantUserList(MerchantPageQuery merchantPageQuery) {
        MemPageResult<MerchantUserDTO> baseResult = new MemPageResult<MerchantUserDTO>();
        try {
            BasePageResult<MerchantUserDTO> result = merchantService.getMerchantUserList(merchantPageQuery);
            if (result.isSuccess() && null != result.getList()) {
                baseResult.setList(result.getList());
                baseResult.setPageNo(result.getPageNo());
                baseResult.setTotalCount(result.getTotalCount());
                baseResult.setPageSize(result.getPageSize());
                return baseResult;
            }
            baseResult.setReturnCode(MemberReturnCode.MEMBER_NOT_FOUND);
            logger.info("getMerchantUserList:{} info return {}", JSONObject.toJSONString(merchantPageQuery),
                    JSONObject.toJSONString(result));
        } catch (Exception e) {
            baseResult.setReturnCode(MemberReturnCode.DUBBO_ERROR);
            logger.error("getMerchantUserList:{} info dubbo error, mes is {}",
                    JSONObject.toJSONString(merchantPageQuery), e);
        }
        return baseResult;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈保存店铺信息〉
     *
     * @param merchantDO
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public MemResult<MerchantDO> saveMerchant(MerchantDO merchantDO) {
        MemResult<MerchantDO> baseResult = new MemResult<MerchantDO>();
        try {
            BaseResult<MerchantDO> result = merchantService.saveMerchant(merchantDO);
            if (result.isSuccess() && null != result.getValue()) {
                baseResult.setValue(result.getValue());
                return baseResult;
            }
            baseResult.setErrorCode(result.getErrorCode());
            baseResult.setErrorMsg(result.getResultMsg());
            baseResult.setSuccess(false);
            logger.info("saveMerchant par:{} return error:{}", JSONObject.toJSONString(merchantDO),
                    JSONObject.toJSONString(result));
        } catch (Exception e) {
            baseResult.setReturnCode(MemberReturnCode.DUBBO_ERROR);
            logger.error("saveMerchant par:{} return error:{}", JSONObject.toJSONString(merchantDO), e);
        }
        return baseResult;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈更新店铺信息〉
     *
     * @param merchantDTO
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public MemResult<Boolean> updateMerchantInfo(MerchantDTO merchantDTO) {
        MemResult<Boolean> baseResult = new MemResult<Boolean>();
        try {
            BaseResult<Boolean> result = merchantService.updateMerchantInfo(merchantDTO);
            if (result.isSuccess() && null != result.getValue()) {
                baseResult.setValue(result.getValue());
                return baseResult;
            }
            baseResult.setErrorCode(result.getErrorCode());
            baseResult.setErrorMsg(result.getResultMsg());
            baseResult.setSuccess(false);
            logger.debug("updateMerchantInfo par:{} return error:{}", JSONObject.toJSONString(merchantDTO),
                    JSONObject.toJSONString(result));
        } catch (Exception e) {
            baseResult.setReturnCode(MemberReturnCode.DUBBO_ERROR);
            logger.error("updateMerchantInfo par:{} return error:{}", JSONObject.toJSONString(merchantDTO), e);
        }
        return baseResult;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈检查sellerName是否已经存在〉
     *
     * @param sellerName
     * @param domainId
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public MemResult<Boolean> getMerchantList(String sellerName, int domainId) {
        MemResult<Boolean> baseResult = new MemResult<Boolean>();
        try {
            MerchantQuery merchantQuery = new MerchantQuery();
            merchantQuery.setDomainId(domainId);
            merchantQuery.setName(sellerName);
            BaseResult<List<MerchantDO>> merchantResult = merchantService.getMerchantList(merchantQuery);
            // 返回列表为空 不存在sellerName
            if (merchantResult.isSuccess() && ParmCheckUtil.checkListNull(merchantResult.getValue())) {
                return baseResult;
            }
            baseResult.setReturnCode(MemberReturnCode.DB_SELLERNAME_FAILED);
            logger.debug("getMerchantList par:{} return error:{}", JSONObject.toJSONString(merchantQuery),
                    JSONObject.toJSONString(merchantResult));
        } catch (Exception e) {
            baseResult.setReturnCode(MemberReturnCode.DUBBO_ERROR);
            logger.error("getMerchantList par:{} return error:{}", sellerName, e);
        }
        return baseResult;
    }
    
    /**
     * 
     * 功能描述: <br>
     * 〈查询店铺信息〉
     *
     * @param id
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public MemResult<MerchantDO> getMerchantById(long id) {
        MemResult<MerchantDO> baseResult = new MemResult<MerchantDO>();
        try {
            BaseResult<MerchantDO> result = merchantService.getMerchantById(id);
            // 返回列表为空 不存在sellerName
            if (result.isSuccess()) {
                baseResult.setValue(result.getValue());
                return baseResult;
            }
            baseResult.setReturnCode(MemberReturnCode.USER_NOT_FOUND);
            logger.debug("getMerchantById par:{} return error:{}", id, JSONObject.toJSONString(result));
        } catch (Exception e) {
            baseResult.setReturnCode(MemberReturnCode.DUBBO_ERROR);
            logger.error("getMerchantById par:{} return error:{}", id, e);
        }
        return baseResult;
    }
}
