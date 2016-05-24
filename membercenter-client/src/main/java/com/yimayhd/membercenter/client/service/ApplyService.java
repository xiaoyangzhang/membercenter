package com.yimayhd.membercenter.client.service;

import java.util.List;

import com.yimayhd.membercenter.client.domain.merchant.BusinessScopeDO;

public interface ApplyService {

	public List<BusinessScopeDO> getBusinessScopeBySellerId(long sellerId,int domainId);
}
