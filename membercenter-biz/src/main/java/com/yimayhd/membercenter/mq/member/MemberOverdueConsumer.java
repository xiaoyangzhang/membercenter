package com.yimayhd.membercenter.mq.member;

import java.io.Serializable;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.domain.MemberDO;
import com.yimayhd.membercenter.client.enums.topic.MemberTopic;
import com.yimayhd.membercenter.client.result.MemResultSupport;
import com.yimayhd.membercenter.mq.BaseConsumer;
import com.yimayhd.membercenter.repo.UserMemberRepo;

public class MemberOverdueConsumer extends BaseConsumer {
	private static final Logger logger = LoggerFactory.getLogger("MemberOverdueConsumer") ;
	private static final MemberTopic topic = MemberTopic.MEMBER_OVERDUE ;
	@Autowired
	private UserMemberRepo userMemberRepo ;
	
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
		if( !(message instanceof MemberDO) ){
			logger.error(log+"   Message not MemberDO!");
			return true;
		}
		MemberDO memberDO = (MemberDO) message ;

		long userId = memberDO.getUserId() ;
		MemResultSupport result = userMemberRepo.removeVipFlag(userId);
		if( result == null || !result.isSuccess() ){
			logger.error(log+" removeVipFlag failed!  userId={}, result={}", userId, JSON.toJSONString(result));
			int code = result.getErrorCode() ;
			if( MemberReturnCode.USER_NOT_FOUND.getCode() == code ){
				return true;
			}
			return false;
		}
		return true ;
	}

}
