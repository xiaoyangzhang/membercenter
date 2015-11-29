package com.yimayhd.membercenter.service.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.membercenter.api.PrivilegeApi;
import com.yimayhd.membercenter.client.domain.MemberPrivilegeDO;
import com.yimayhd.membercenter.client.query.MemPrivilegePageQuery;
import com.yimayhd.membercenter.client.result.MemPageResult;
import com.yimayhd.membercenter.converter.PrivilegeConverter;
import com.yimayhd.membercenter.entity.PageInfo;
import com.yimayhd.membercenter.entity.PrivilegeInfoPageList;
import com.yimayhd.membercenter.enums.MemPrivilegeStatus;
import com.yimayhd.membercenter.manager.MemberPrivilegeManager;

public class PrivilegeApiImpl implements PrivilegeApi {
	
	private final static Logger log = LoggerFactory.getLogger(PrivilegeApiImpl.class);
	
	@Autowired
	private MemberPrivilegeManager memPrivilegeManager;
	

	@Override
	public PrivilegeInfoPageList getPrivilegeInfoPageList(int appId, int domainId, long deviceId, long userId,
			int versionCode, PageInfo pageInfo) {
		
		try {
			
			MemPrivilegePageQuery memPrivilegePageQuery = PrivilegeConverter.memPrivilegePageQuery(pageInfo);
			
			memPrivilegePageQuery.setStatus(MemPrivilegeStatus.AVAILABLE.getStatus());
			
			MemPageResult<MemberPrivilegeDO> pageQuery = memPrivilegeManager.pageQuery(memPrivilegePageQuery);
			
			PrivilegeInfoPageList privilegeInfoPageList = PrivilegeConverter.privilegeInfoListConverter(pageQuery.getList(), pageQuery.getPageNo(), pageQuery.isHasNext());
			
			return privilegeInfoPageList;
			
		} catch (Exception e) {
			log.error("getPrivilegeInfoPageList error",e);
		}
		return null;
	}

}
