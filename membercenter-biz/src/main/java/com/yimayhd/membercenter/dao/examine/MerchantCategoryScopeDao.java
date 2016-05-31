package com.yimayhd.membercenter.dao.examine;

import java.util.List;

import com.yimayhd.membercenter.client.domain.merchant.MerchantCategoryDO;
import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.membercenter.client.domain.merchant.MerchantCategoryScopeDO;
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
	public List<MerchantCategoryScopeDO> getMerchantCategoryScope(MerchantCategoryScopeDO merchantCategoryScope) {
//		if (merchantCategoryId <=0 || domainId <= 0) {
//			return null;
//		}
		List<MerchantCategoryScopeDO> scopes = merchantCategoryScopeDOMapper.getMerchantCategoryScope(merchantCategoryScope);
//		if (scopes == null) {
//			return null;
//		}
		return scopes;
	}

//	public List<MerchantCategoryScopeDO> getMerchantCategoriesByScopeIds(long[] scopeIds, int domainId) {
//		return merchantCategoryScopeDOMapper.getMerchantCatergoryScopesByScopeIds(scopeIds, domainId);
//	}
}
