package com.yimayhd.membercenter.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.membercenter.client.domain.MemberPrivilegeDO;
import com.yimayhd.membercenter.client.query.MemPrivilegePageQuery;
import com.yimayhd.membercenter.mapper.MemberPrivilegeDOMapper;

public class MemberPrivilegeDao {
	
	@Autowired
	private MemberPrivilegeDOMapper  memberPrivilegeDOMapper;

	public MemberPrivilegeDO insert2DB(MemberPrivilegeDO memberPrivilegeDO) {
		
		memberPrivilegeDO.setGmtCreated(new Date());
		memberPrivilegeDO.setGmtModified(new Date());
		
		int insertStatus = memberPrivilegeDOMapper.insert(memberPrivilegeDO);
		
		if(1 == insertStatus){
			
			return memberPrivilegeDO;
		}
		
		return null;
	}

	public MemberPrivilegeDO update2DB(MemberPrivilegeDO memberPrivilegeDO) {
		
		memberPrivilegeDO.setGmtModified(new Date());
		
		int updateStatus = memberPrivilegeDOMapper.updateByPrimaryKey(memberPrivilegeDO);
		
		if(1 == updateStatus){
			
			return memberPrivilegeDO;
		}
		
		return null;
	}

	public MemberPrivilegeDO getPrivilege4DB(long id) {
		
		return memberPrivilegeDOMapper.selectByPrimaryKey(id);
	}

	public List<MemberPrivilegeDO> pageQuery(MemPrivilegePageQuery memPrilvilegePageQuery) {
		
		return memberPrivilegeDOMapper.pageQuery(memPrilvilegePageQuery);
	}

	public int queryCount(MemPrivilegePageQuery memPrilvilegePageQuery) {
		
		return memberPrivilegeDOMapper.queryCount(memPrilvilegePageQuery);
	}

	public int updateStatus2DB(MemberPrivilegeDO memberPrivilegeDO) {
		
		memberPrivilegeDO.setGmtModified(new Date());
		
		return memberPrivilegeDOMapper.updateStatus(memberPrivilegeDO);
	}

}
