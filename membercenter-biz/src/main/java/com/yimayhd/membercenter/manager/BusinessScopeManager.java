package com.yimayhd.membercenter.manager;

import com.yimayhd.membercenter.client.domain.merchant.BusinessScopeDO;
import com.yimayhd.membercenter.dao.examine.BusinessScopeDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by hanlei on 2016/5/24.
 */
public class BusinessScopeManager {

	@Autowired
	private BusinessScopeDao businessScopeDao;

	public List<BusinessScopeDO> getBusinessScopesByScope(int domainId, long[] scopeIds) {
		return businessScopeDao.getBusinessScopesByScope(domainId, scopeIds);
	}
}
