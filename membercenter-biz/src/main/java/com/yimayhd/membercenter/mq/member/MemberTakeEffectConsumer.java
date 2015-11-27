package com.yimayhd.membercenter.mq.member;

import java.io.Serializable;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.yimayhd.membercenter.client.domain.MemberDO;
import com.yimayhd.membercenter.client.enums.topic.MemberTopic;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.mq.BaseConsumer;
import com.yimayhd.membercenter.repo.TcOrderRepo;
import com.yimayhd.tradecenter.client.model.domain.order.BizOrderDO;
import com.yimayhd.tradecenter.client.model.enums.PayStatus;

public class MemberTakeEffectConsumer extends BaseConsumer {
	private static final Logger logger = LoggerFactory.getLogger("OrderPaidDoneConsumer") ;
	private static final MemberTopic topic = MemberTopic.MEMBER_TAKE_EFFECT ;
	@Autowired
	private TcOrderRepo orderRepo ;
	
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
		
		
		
		
		
		return false;
	}

}
