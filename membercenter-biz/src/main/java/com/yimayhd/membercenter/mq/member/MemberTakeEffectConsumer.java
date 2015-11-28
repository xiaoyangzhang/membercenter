package com.yimayhd.membercenter.mq.member;

import java.io.Serializable;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.domain.MemberDO;
import com.yimayhd.membercenter.client.enums.MemberStatus;
import com.yimayhd.membercenter.client.enums.topic.MemberTopic;
import com.yimayhd.membercenter.client.result.MemResultSupport;
import com.yimayhd.membercenter.dao.MemberDao;
import com.yimayhd.membercenter.mq.BaseConsumer;
import com.yimayhd.membercenter.repo.UserMemberRepo;

public class MemberTakeEffectConsumer extends BaseConsumer {
	private static final Logger logger = LoggerFactory.getLogger("MemberTakeEffectConsumer") ;
	private static final MemberTopic topic = MemberTopic.MEMBER_TAKE_EFFECT ;
	@Autowired
	private UserMemberRepo userMemberRepo ;
	@Autowired
	private MemberDao memberDao ;
	
	
	
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
		MemberDO dbMember = memberDao.selectById(memberDO.getId()) ;
		if( dbMember == null ){
			logger.error(log +"   member not exist in db! ");
			return true; 
		}
		long userId = dbMember.getUserId() ;
		if( MemberStatus.ACTIVE.getStatus() == dbMember.getStatus() ) {
			MemResultSupport result = userMemberRepo.flagVip4User(userId);
			if( result == null || !result.isSuccess() ){
				logger.error(log+" flagVip4User failed!  userId={}, result={}", userId, JSON.toJSONString(result));
				int code = result.getErrorCode() ;
				if( MemberReturnCode.USER_NOT_FOUND.getCode() == code ){
					return true;
				}
				return false;
			}
			return true ;
		}else{
			logger.info(log+" member not active, so give up option db member={}", JSON.toJSONString(dbMember));
		}
		return true;
	}

}
