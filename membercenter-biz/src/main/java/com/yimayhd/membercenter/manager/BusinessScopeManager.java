package com.yimayhd.membercenter.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.membercenter.client.domain.merchant.BusinessScopeDO;
import com.yimayhd.membercenter.dao.BusinessScopeDao;

/**
 * Created by hanlei on 2016/5/24.
 */
public class BusinessScopeManager {

	@Autowired
	private BusinessScopeDao businessScopeDao;

	public List<BusinessScopeDO> getBusinessScopesByScope(long[] scopeIds) {
		return businessScopeDao.getBusinessScopesByScope(scopeIds);
	}
}
