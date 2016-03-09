package com.yimayhd.membercenter.mapper;

import com.yimayhd.membercenter.query.HaRoleMenuQuery;

import java.util.List;

/**
 * 角色菜单表
 * @author czf
 */
public interface HaRoleMenuMapper{

	List<Long> getHaRoleIdList(HaRoleMenuQuery haRoleMenuQuery);
	
}
