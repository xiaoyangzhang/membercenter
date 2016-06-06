package com.yimayhd.membercenter.dao.examine;

import java.util.List;

import com.yimayhd.membercenter.client.domain.merchant.MerchantCategoryDO;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.membercenter.client.domain.merchant.MerchantCategoryScopeDO;
import com.yimayhd.membercenter.client.query.BusinessScopeQueryDTO;
import com.yimayhd.membercenter.mapper.MerchantCategoryScopeDOMapper;

/**
 * 
* @ClassName: MerchantCategoryScopeDao
* @Description: 商家身份与经营访问管理关系
* @author zhangxy
* @date 2016年5月26日 上午11:15:02
*
 */
public class MerchantCategoryScopeDao {

	@Autowired
	private MerchantCategoryScopeDOMapper merchantCategoryScopeDOMapper;
	public List<MerchantCategoryScopeDO> getMerchantCategoryScope(BusinessScopeQueryDTO queryDTO) {
		//List<MerchantCategoryScopeDO> scopes = 
		return merchantCategoryScopeDOMapper.getMerchantCategoryScope(queryDTO,queryDTO.getIdSet());
	}

//	public List<MerchantCategoryScopeDO> getMerchantCategoriesByScopeIds(long[] scopeIds, int domainId) {
//		return merchantCategoryScopeDOMapper.getMerchantCatergoryScopesByScopeIds(scopeIds, domainId);
//	}
}
