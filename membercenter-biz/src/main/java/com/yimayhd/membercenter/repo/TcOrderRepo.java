package com.yimayhd.membercenter.repo;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.membercenter.client.result.BaseResult;
import com.yimayhd.tradecenter.client.model.domain.order.BizOrderDO;
import com.yimayhd.tradecenter.client.model.param.order.OrderQueryOption;
import com.yimayhd.tradecenter.client.model.result.order.SingleQueryResult;
import com.yimayhd.tradecenter.client.service.trade.TcQueryService;

public class TcOrderRepo {
	@Autowired
	private TcQueryService tcQueryService ;
	
	public BaseResult<BizOrderDO> getBizOrderById(long bizOrderId){
		BaseResult<BizOrderDO> result = new BaseResult<BizOrderDO>() ;
		OrderQueryOption orderQueryOption = new OrderQueryOption() ;
		SingleQueryResult queryResult = tcQueryService.querySingle(bizOrderId, orderQueryOption);
		if( queryResult == null || !queryResult.isSuccess() || queryResult.getBizOrderDO() == null ){
//			result.set
			return result ;
		}
		return result ;
	}
}
