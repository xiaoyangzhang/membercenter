package com.yimayhd.membercenter.client.service;

import java.util.List;

import com.yimayhd.membercenter.client.domain.merchant.MerchantItemCategoryDO;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.result.MemResultSupport;

public interface MerchantItemCategoryService {

	MemResult<List<MerchantItemCategoryDO>> findMerchantItemCategoriesByMerchant(int domainId, long examineId);
	
	MemResultSupport saveMerchantItemCategoriesByMerchant(int domainId, long examineId, long[] categoryIds);
}
