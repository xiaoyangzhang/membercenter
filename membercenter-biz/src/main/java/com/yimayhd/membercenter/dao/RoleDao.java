package com.yimayhd.membercenter.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.membercenter.client.domain.HaRoleDO;
import com.yimayhd.membercenter.enums.RoleType;
import com.yimayhd.membercenter.mapper.HaRoleMapper;

public class RoleDao {
	@Autowired
	private HaRoleMapper haRoleMapper ;
	
	
	public List<HaRoleDO> getRolesByType(RoleType roleType) {
		if( roleType == null ){
			return null;
		}
		return haRoleMapper.getRolesByType(roleType.getType());
	}
}
