package com.yimayhd.membercenter.mq.user;

import java.io.Serializable;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.yimayhd.membercenter.manager.UserPermissionManager;
import com.yimayhd.membercenter.mq.BaseConsumer;
import com.yimayhd.membercenter.repo.UserRepo;
import com.yimayhd.user.client.domain.UserDO;
import com.yimayhd.user.client.enums.TopicEnum;

public class UserRegisterConsumer extends BaseConsumer {
	private static final Logger logger = LoggerFactory.getLogger("UserRegisterConsumer") ;
	private static final TopicEnum topic = TopicEnum.USER_REGISTER ;
	@Autowired
	private UserRepo userRepo ;
	@Autowired
	private UserPermissionManager userPermissionManager ;
	
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
		
		//FIXME 
		long defaultRoleId = 0 ;
		boolean addRoleResult = userPermissionManager.addRole4User(userId, defaultRoleId);
		if( !addRoleResult ){
			return false;
		}
		
		return true ;
	}

}
