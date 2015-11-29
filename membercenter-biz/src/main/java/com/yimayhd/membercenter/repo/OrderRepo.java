package com.yimayhd.membercenter.repo;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.domain.MemberDO;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.dto.MemberDiscountQueryDTO;
import com.yimayhd.membercenter.entity.member.MemeberDiscount;
import com.yimayhd.membercenter.util.DateUtil;
import com.yimayhd.tradecenter.client.model.domain.order.BizOrderDO;
import com.yimayhd.tradecenter.client.model.param.order.OrderQueryDTO;
import com.yimayhd.tradecenter.client.model.param.order.OrderQueryOption;
import com.yimayhd.tradecenter.client.model.result.order.BatchQueryResult;
import com.yimayhd.tradecenter.client.model.result.order.SingleQueryResult;
import com.yimayhd.tradecenter.client.service.trade.TcQueryService;

public class OrderRepo {
	private static final Logger logger = LoggerFactory.getLogger("OrderRepo");
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
	
	public MemResult<List<MemeberDiscount>> queryMemberDiscounts(MemberDiscountQueryDTO memberDiscountQueryDTO){
		MemResult<List<MemeberDiscount>> result = new MemResult<List<MemeberDiscount>>();
		if( memberDiscountQueryDTO == null ){
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return result ;
		}
		OrderQueryDTO orderQueryDTO = new OrderQueryDTO() ;
		orderQueryDTO.setBuyerId(memberDiscountQueryDTO.getBuyerId());
		orderQueryDTO.setPageNo(memberDiscountQueryDTO.getPageNo());
		orderQueryDTO.setPageSize(memberDiscountQueryDTO.getPageSize());
		//FIXME 缺少通过会员信息购买的商品这个条件
		BatchQueryResult batchQueryResult = tcQueryService.queryOrderForBuyer(orderQueryDTO);
		if( batchQueryResult == null || !batchQueryResult.isSuccess() ){
			logger.error("queryOrderForBuyer  OrderQueryDTO={},  Result={}", JSON.toJSONString(orderQueryDTO), JSON.toJSONString(batchQueryResult) );
			result.setReturnCode(MemberReturnCode.PAGE_QUERY_ORDER_FAILED);
			return result ;
		}
		List<BizOrderDO> bizOrderDOs = batchQueryResult.getBizOrderDOList();
		if( bizOrderDOs == null ){
			return result;
		}
		List<MemeberDiscount> discounts = new ArrayList<MemeberDiscount>() ;
		for(BizOrderDO bizOrderDO : bizOrderDOs ){
			MemeberDiscount discount = new MemeberDiscount() ;
			discount.buyTime = DateUtil.dateToLong(bizOrderDO.getGmtCreated()) ;
			discount.itemId = bizOrderDO.getItemId() ;
			discount.itemPic = bizOrderDO.getItemPic() ;
			discount.itemTitle = bizOrderDO.getItemTitle() ;
			discounts.add(discount);
		}
		result.setValue(discounts);
		return result ;
	}
}
