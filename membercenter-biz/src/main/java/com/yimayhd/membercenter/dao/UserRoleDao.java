package com.yimayhd.membercenter.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.membercenter.client.domain.HaUserRoleDO;
import com.yimayhd.membercenter.mapper.HaUserRoleMapper;

public class UserRoleDao {
	@Autowired
	private HaUserRoleMapper haUserRoleMapper ;
	
	
	public HaUserRoleDO getUserRole(long userId, long roleId) {
		return haUserRoleMapper.getUserRole(userId, roleId);
	}
    
    public HaUserRoleDO insert(HaUserRoleDO record){
    	if( record == null ){
    		return null;
    	}
    	record.setGmtCreated(new Date());
    	record.setGmtModified(new Date());
    	int count = haUserRoleMapper.add(record) ;
    	if( count == 1){
    		return record ;
    	}
    	return null;
    }
    
    public HaUserRoleDO updateStatus(HaUserRoleDO record){
    	if( record == null ){
    		return null;
    	}
    	record.setGmtModified(new Date());
    	int count = haUserRoleMapper.updateStatus(record);
    	if( count == 1){
    		return record ;
    	}
    	return null;
    }
}
