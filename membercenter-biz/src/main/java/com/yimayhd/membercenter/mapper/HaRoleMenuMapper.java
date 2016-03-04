package com.yimayhd.membercenter.mapper;

import com.yimayhd.membercenter.client.domain.HaRoleMenuDO;

/**
 * 角色菜单表
 * @author czf
 */
public interface HaRoleMenuMapper{

	boolean addRoleMenu(HaRoleMenuDO haRoleMenuDO);
	
	HaRoleMenuDO getHaRoleMenuById(long id);
	
}
