package com.yimayhd.membercenter.mapper;

import com.yimayhd.membercenter.client.query.UserMenuQuery;

import java.util.List;

/**
 * 用户角色表
 * @author czf
 */
public interface HaUserRoleMapper{

    List<Long> getHaRoleList(UserMenuQuery userMenuQuery);

}
