package com.yimayhd.membercenter.mq.user;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.yimayhd.membercenter.client.domain.HaRoleDO;
import com.yimayhd.membercenter.dao.RoleDao;
import com.yimayhd.membercenter.enums.RoleType;
import com.yimayhd.membercenter.manager.UserPermissionManager;
import com.yimayhd.membercenter.repo.UserRepo;
import com.yimayhd.user.client.domain.UserDO;
import com.yimayhd.user.client.enums.TopicEnum;

public class UserRegisterConsumer extends UserRoleConsumer {
	private static final Logger logger = LoggerFactory.getLogger("MQ") ;
	private static final TopicEnum topic = TopicEnum.USER_REGISTER ;
	@Autowired
	private UserRepo userRepo ;
	@Autowired
	private UserPermissionManager userPermissionManager ;
	@Autowired
	private RoleDao roleDao;
	
	@Override
	public String getTopic() {
		return topic.getTopic();
	}

	@Override
	public String getTags() {
		return topic.getTags();
	}

	@Override
	public boolean doConsumeMessage(Serializable message) {
		String log ="UUID+"+ UUID.randomUUID()+"  topic="+topic+"  msg={}"+JSON.toJSONString(message);
		logger.info(log);
		if( !(message instanceof UserDO) ){
			logger.error(log+"   Message not UserDO!");
			return true;
		}
		UserDO userDO = (UserDO) message ;

		long userId = userDO.getId() ;
		UserDO user = userRepo.getUserDOById(userId);
		if( user == null ){
			logger.error(log+"   UserDO not exit, ignore!");
			return true;
		}
		
		//更新达人的权限
		List<HaRoleDO> roles = roleDao.getRolesByType(RoleType.REGISTER_USER);
		boolean updateRoleResult = updateUserRole(userId, true, roles);
		if( !updateRoleResult ){
			return false;
		}
		
		return true ;
	}

}
