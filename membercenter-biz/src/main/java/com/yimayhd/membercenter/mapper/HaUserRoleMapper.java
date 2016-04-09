package com.yimayhd.membercenter.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yimayhd.membercenter.client.domain.HaUserRoleDO;
import com.yimayhd.membercenter.client.query.UserMenuQuery;

/**
 * 用户角色表
 * @author czf
 */
public interface HaUserRoleMapper{

    List<Long> getHaRoleList(UserMenuQuery userMenuQuery);
    
    public HaUserRoleDO getUserRole(@Param("userId")long userId, @Param("roleId")long roleId) ;
    
    public int add(HaUserRoleDO record);
    
    public int updateStatus(HaUserRoleDO record);

}
