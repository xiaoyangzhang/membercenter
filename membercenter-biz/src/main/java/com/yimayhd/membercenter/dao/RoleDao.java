package com.yimayhd.membercenter.dao;

import java.util.Date;
import java.util.List;

import com.yimayhd.membercenter.client.domain.MemberRecordDO;
import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.membercenter.client.domain.HaRoleDO;
import com.yimayhd.membercenter.enums.RoleType;
import com.yimayhd.membercenter.mapper.HaRoleMapper;

public class RoleDao {
    @Autowired
    private HaRoleMapper haRoleMapper;


    public List<HaRoleDO> getRolesByType(RoleType roleType) {
        if (roleType == null) {
            return null;
        }
        return haRoleMapper.getRolesByType(roleType.getType());
    }

    public HaRoleDO insert(HaRoleDO haRoleDO) {
        if (haRoleDO == null) {
            return null;
        }
        Date now = new Date();
        haRoleDO.setGmtCreated(now);
        haRoleDO.setGmtModified(now);
        int result = haRoleMapper.add(haRoleDO);
        if (result == 1) {
            return haRoleDO;
        }
        return null;
    }

    public HaRoleDO update(HaRoleDO record) {
        if (record == null) {
            return null;
        }
        record.setGmtModified(new Date());
        int count = haRoleMapper.modify(record);
        if (count == 1) {
            return record;
        }
        return null;
    }

    public HaRoleDO getById(long id) {
        return haRoleMapper.getById(id);
    }

    public void delete(long id) {
        haRoleMapper.delete(id);
    }
}
