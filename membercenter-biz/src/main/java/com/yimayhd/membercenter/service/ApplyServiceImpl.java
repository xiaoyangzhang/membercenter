package com.yimayhd.membercenter.service;

import java.util.ArrayList;
import java.util.List;

import com.yimayhd.membercenter.client.domain.merchant.BusinessScopeDO;
import com.yimayhd.membercenter.client.service.ApplyService;

public class ApplyServiceImpl implements ApplyService {

	@Override
	public List<BusinessScopeDO> getBusinessScopeBySellerId(long sellerId,
			int domainId) {
		List<BusinessScopeDO> businessScopeList = new ArrayList<BusinessScopeDO>();
		businessScopeList.add(new BusinessScopeDO());
		return businessScopeList;
	}

}
