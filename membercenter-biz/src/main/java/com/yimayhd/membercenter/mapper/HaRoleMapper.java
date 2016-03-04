package com.yimayhd.membercenter.mapper;

import com.yimayhd.membercenter.client.domain.HaRoleDO;
import java.util.List;

/**
 * 角色表（菜单）
 * @author czf
 */
public interface HaRoleMapper{

	Long roleDetailCount();
	
	boolean updateRoleStatus(HaRoleDO haRoleDO);
}
