package com.yimayhd.membercenter.mq.user;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.yimayhd.membercenter.client.domain.HaRoleDO;
import com.yimayhd.membercenter.manager.UserPermissionManager;
import com.yimayhd.membercenter.mq.BaseConsumer;
import com.yimayhd.membercenter.repo.UserRepo;
import com.yimayhd.user.client.domain.UserDO;
import com.yimayhd.user.client.enums.TopicEnum;

public abstract class UserRoleConsumer extends BaseConsumer {
	private static final Logger logger = LoggerFactory.getLogger("UserRegisterConsumer") ;

	@Autowired
	private UserPermissionManager userPermissionManager ;
	
	/**
	 * 
	 * @param own 是否拥有权限
	 * @param roles
	 * @return
	 */
	protected boolean updateUserRole(long userId, boolean own, List<HaRoleDO> roles){
		if( !CollectionUtils.isEmpty(roles) ){
			for( HaRoleDO role : roles ){
				long roleId = role.getId() ;
				if( own ){
					boolean addRoleResult = userPermissionManager.addRole4User(userId, roleId);
					if( !addRoleResult ){
						return false;
					}
				}else{
					boolean removeResult = userPermissionManager.disactiveUserRole(userId, roleId);
					if( !removeResult ){
						return false;
					}
				}
			}
			
		}
		return true; 
	}

}
