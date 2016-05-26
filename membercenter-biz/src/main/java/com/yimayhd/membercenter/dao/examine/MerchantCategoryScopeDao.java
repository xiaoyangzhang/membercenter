package com.yimayhd.membercenter.dao.examine;

import java.util.List;

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
	public List<MerchantCategoryScopeDO> getMerchantCategoryScopeByMerchantCategoryId(long merchantCategoryId,int domainId) {
//		if (merchantCategoryId <=0 || domainId <= 0) {
//			return null;
//		}
		List<MerchantCategoryScopeDO> scopes = merchantCategoryScopeDOMapper.getMerchantCategoryScopeByMerchantCategoryId(merchantCategoryId, domainId);
//		if (scopes == null) {
//			return null;
//		}
		return scopes;
	}
}
