package com.yimayhd.membercenter.mapper;

import com.yimayhd.membercenter.client.domain.HaRoleDO;
import com.yimayhd.membercenter.client.query.RoleListQuery;

import java.util.List;

/**
 * 角色表（菜单）
 *
 * @author czf
 */
public interface HaRoleMapper {

    Long roleDetailCount();

    boolean updateRoleStatus(HaRoleDO haRoleDO);

    public List<HaRoleDO> getRolesByType(int roleType);

    int add(HaRoleDO haRoleDO);

    HaRoleDO getById(long id);

    int modify(HaRoleDO haRoleDO);

    void delete(long id);

    List<HaRoleDO> pageQuery(RoleListQuery roleListQuery);

    Integer queryCount(RoleListQuery roleListQuery);
}
