package com.yimayhd.membercenter.client.service;

import java.util.List;

import com.yimayhd.membercenter.client.domain.merchant.MerchantCategoryDO;
import com.yimayhd.membercenter.client.domain.merchant.MerchantItemCategoryDO;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.result.MemResultSupport;

public interface MerchantItemCategoryService {

	MemResult<List<MerchantItemCategoryDO>> findMerchantItemCategoriesBySellerId(int domainId, long sellerId);
	
	MemResultSupport saveMerchantItemCategories(int domainId, long examineId, List<Long> categoryIds);

	MemResultSupport checkCategoryPrivilege(int domainId, long categoryId, long sellerId);
	
	MemResult<List<MerchantItemCategoryDO>> getMerchantItemCategory(int domainId, long categoryId, long sellerId);
	MemResult<MerchantItemCategoryDO> selectObjByCategoryIdAndSellerId(int domainId, long categoryId, long sellerId);
}
