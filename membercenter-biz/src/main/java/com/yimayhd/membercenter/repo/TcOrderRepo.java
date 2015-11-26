package com.yimayhd.membercenter.repo;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.tradecenter.client.model.domain.order.BizOrderDO;
import com.yimayhd.tradecenter.client.model.param.order.OrderQueryOption;
import com.yimayhd.tradecenter.client.model.result.order.SingleQueryResult;
import com.yimayhd.tradecenter.client.service.trade.TcQueryService;

public class TcOrderRepo {
	@Autowired
	private TcQueryService tcQueryService ;
	
	public MemResult<BizOrderDO> getBizOrderById(long bizOrderId){
		MemResult<BizOrderDO> result = new MemResult<BizOrderDO>() ;
		OrderQueryOption orderQueryOption = new OrderQueryOption() ;
		SingleQueryResult queryResult = tcQueryService.querySingle(bizOrderId, orderQueryOption);
		if( queryResult == null || !queryResult.isSuccess() || queryResult.getBizOrderDO() == null ){
			result.setReturnCode(MemberReturnCode.BIZ_ORDER_NOT_FOUND);
			return result ;
		}
		BizOrderDO bizOrderDO = queryResult.getBizOrderDO();
		result.setValue(bizOrderDO);
		return result ;
	}
}
