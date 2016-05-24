package com.yimayhd.membercenter.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.membercenter.client.domain.merchant.BusinessScopeDO;
import com.yimayhd.membercenter.mapper.BusinessScopeDOMapper;

/**
 * Created by hanlei on 2016/5/24.
 */
public class BusinessScopeDao {
	
	@Autowired
	private BusinessScopeDOMapper businessScopeDOMapper;
	
	public List<BusinessScopeDO> getBusinessScopesByScope(int domainId, long[] scopeIds) {
		return businessScopeDOMapper.getBusinessScopesByScope(domainId, scopeIds);
	}

}
