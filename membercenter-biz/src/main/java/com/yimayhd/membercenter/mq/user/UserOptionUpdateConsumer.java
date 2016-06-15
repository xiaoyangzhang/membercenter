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
import com.yimayhd.membercenter.repo.UserRepo;
import com.yimayhd.user.client.domain.UserDO;
import com.yimayhd.user.client.enums.UserOptions;
import com.yimayhd.user.client.topic.UserTopic;

public class UserOptionUpdateConsumer extends UserRoleConsumer {
	private static final Logger logger = LoggerFactory.getLogger("UserOptionUpdateConsumer") ;
	private static final UserTopic topic = UserTopic.USER_OPTION_UPDATED ;
	@Autowired
	private UserRepo userRepo ;
	
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
		
		long option = user.getOptions();
		
		boolean isMerchant = UserOptions.COMMERCIAL_TENANT.has(option);
		boolean isTenant = UserOptions.CERTIFICATED.has(option);
		
		//更新达人的权限
		List<HaRoleDO> talentRoles = roleDao.getRolesByType(RoleType.TALENT);
		boolean updateTalentRoleResult = updateUserRole(userId, isTenant, talentRoles);
		if( !updateTalentRoleResult ){
			return false;
		}
		
		//更新商户的权限
		List<HaRoleDO> merchantRoles = roleDao.getRolesByType(RoleType.MERCHANT);
		boolean updateMerchantRoleResult = updateUserRole(userId, isMerchant, merchantRoles);
		if( !updateMerchantRoleResult ){
			return false;
		}	
		return true ;
	}
	
	
}
