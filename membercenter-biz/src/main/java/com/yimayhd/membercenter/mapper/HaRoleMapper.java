package com.yimayhd.membercenter.mapper;

import java.util.List;

import com.yimayhd.membercenter.client.domain.HaRoleDO;

/**
 * 角色表（菜单）
 * @author czf
 */
public interface HaRoleMapper{

	Long roleDetailCount();
	
	boolean updateRoleStatus(HaRoleDO haRoleDO);
	
	public List<HaRoleDO> getRolesByType(int roleType);
}
