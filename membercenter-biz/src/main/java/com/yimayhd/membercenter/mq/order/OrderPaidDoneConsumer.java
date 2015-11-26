package com.yimayhd.membercenter.mq.order;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.yimayhd.membercenter.mq.BaseConsumer;
import com.yimayhd.tradecenter.client.model.domain.order.BizOrderDO;
import com.yimayhd.tradecenter.client.model.result.order.metaq.OrderInfoTO;
import com.yimayhd.tradecenter.client.model.topic.OrderTopic;

public class OrderPaidDoneConsumer extends BaseConsumer {
	private static final Logger logger = LoggerFactory.getLogger("OrderPaidDoneConsumer") ;
	private static final OrderTopic topic = OrderTopic.ORDER_PAID_DONE ;	
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
		logger.info("topic={}  msg={}", topic, JSON.toJSONString(message));
		if( !(message instanceof OrderInfoTO) ){
			logger.error("Message not OrderInfoTO! topic={}  msg={}", topic, JSON.toJSONString(message));
			return true;
		}
		OrderInfoTO orderInfoTO = (OrderInfoTO) message ;
		BizOrderDO bizOrderDO = orderInfoTO.getBizOrderDO();
		
		
		
		return false;
	}

}
