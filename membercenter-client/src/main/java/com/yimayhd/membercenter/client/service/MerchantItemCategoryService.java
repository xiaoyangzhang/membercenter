package com.yimayhd.membercenter.client.service;

import com.yimayhd.membercenter.client.domain.merchant.MerchantItemCategoryDO;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.result.MemResultSupport;

import java.util.List;

public interface MerchantItemCategoryService {

	MemResult<List<MerchantItemCategoryDO>> findMerchantItemCategoriesBySellerId(int domainId, long sellerId);
	
	MemResultSupport saveMerchantItemCategories(int domainId, long examineId, long[] categoryIds);
}
