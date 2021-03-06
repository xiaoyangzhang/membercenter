package com.yimayhd.membercenter.mq.order;

import java.io.Serializable;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.yimayhd.membercenter.client.dto.MemberBuyDTO;
import com.yimayhd.membercenter.client.enums.MemberRecordOutType;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.manager.MemberManager;
import com.yimayhd.membercenter.mq.BaseConsumer;
import com.yimayhd.membercenter.repo.OrderRepo;
import com.yimayhd.tradecenter.client.model.domain.order.BizOrderDO;
import com.yimayhd.tradecenter.client.model.enums.PayStatus;
import com.yimayhd.tradecenter.client.model.result.order.metaq.OrderInfoTO;
import com.yimayhd.tradecenter.client.model.topic.OrderTopic;
import com.yimayhd.tradecenter.client.util.BizOrderUtil;

public class OrderPaidDoneConsumer extends BaseConsumer {
	private static final Logger logger = LoggerFactory.getLogger("OrderPaidDoneConsumer") ;
	private static final OrderTopic topic = OrderTopic.ORDER_PAID_DONE ;
	
	@Autowired
	private OrderRepo orderRepo ;
	@Autowired
	private MemberManager memberManager;
	
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
		boolean isRecharge = BizOrderUtil.isMemberRecharge(bizOrderDO);
		if( !isRecharge ){
			//仅处理会员充值订单，其他的订单此处不处理
			return true;
		}
		
//		MemResult<BizOrderDO> orderResult = orderRepo.getBizOrderById(bizOrderDO.getBizOrderId());
//		if( orderResult == null || !orderResult.isSuccess() || orderResult.getValue() == null ){
//			logger.error(log+"  get BizOrderDO failed!  result={}", JSON.toJSONString(orderResult));
//			return false;
//		}
//		BizOrderDO orderDO = orderResult.getValue() ;
//		if( PayStatus.PAID.getStatus() != orderDO.getPayStatus() ){
//			logger.error(log+"  BizOrderDO status Error!  status={}", orderDO.getPayStatus() );
//			return false;
//		}
		
		int period = (int)BizOrderUtil.getMemberRechargeDays(bizOrderDO);
		
		MemberBuyDTO memberBuyDTO = new MemberBuyDTO();
		memberBuyDTO.setBuyerId(bizOrderDO.getBuyerId());
		memberBuyDTO.setOuterId(String.valueOf(bizOrderDO.getBizOrderId()));
		memberBuyDTO.setOuterType(MemberRecordOutType.BUY.getType());
		memberBuyDTO.setPeriod(period);
		memberBuyDTO.setSellerId(bizOrderDO.getSellerId());
		
		MemResult<Boolean> result = memberManager.finishMemberPay(memberBuyDTO);
		if( result == null || !result.isSuccess() || result.getValue() == null || !result.getValue() ){
			logger.error(log+"  finishMemberPay failed!  MemberBuyDTO={}, Result={}", JSON.toJSONString(memberBuyDTO), JSON.toJSONString(result));
			return false;
		}
		return true ;
	}

}
