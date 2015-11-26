package com.yimayhd.membercenter.mq.order;

import java.io.Serializable;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.mq.BaseConsumer;
import com.yimayhd.membercenter.repo.TcOrderRepo;
import com.yimayhd.tradecenter.client.model.domain.order.BizOrderDO;
import com.yimayhd.tradecenter.client.model.enums.PayStatus;
import com.yimayhd.tradecenter.client.model.result.order.metaq.OrderInfoTO;
import com.yimayhd.tradecenter.client.model.topic.OrderTopic;

public class OrderPaidDoneConsumer extends BaseConsumer {
	private static final Logger logger = LoggerFactory.getLogger("OrderPaidDoneConsumer") ;
	private static final OrderTopic topic = OrderTopic.ORDER_PAID_DONE ;
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
		if( !(message instanceof OrderInfoTO) ){
			logger.error(log+"   Message not OrderInfoTO!");
			return true;
		}
		OrderInfoTO orderInfoTO = (OrderInfoTO) message ;
		BizOrderDO bizOrderDO = orderInfoTO.getBizOrderDO();
		if( bizOrderDO == null ){
			logger.error(log+"  message error! BizOrderDO is empty!");
			return true;
		}
		MemResult<BizOrderDO> orderResult = orderRepo.getBizOrderById(bizOrderDO.getBizOrderId());
		if( orderResult == null || !orderResult.isSuccess() || orderResult.getValue() == null ){
			logger.error(log+"  get BizOrderDO failed!  result={}", JSON.toJSONString(orderResult));
			return false;
		}
		BizOrderDO orderDO = orderResult.getValue() ;
		if( PayStatus.PAID.getStatus() != orderDO.getPayStatus() ){
			logger.error(log+"  BizOrderDO status Error!  status={}", orderDO.getPayStatus() );
			return false;
		}
		
		
		
		
		return false;
	}

}
