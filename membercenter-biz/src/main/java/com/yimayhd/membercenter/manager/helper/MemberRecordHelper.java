package com.yimayhd.membercenter.manager.helper;

import com.yimayhd.membercenter.client.domain.MemberRecordDO;
import com.yimayhd.membercenter.client.dto.MemberBuyDTO;

public class MemberRecordHelper {

	public static MemberRecordDO createMemberRecord(MemberBuyDTO memberBuyDTO){
		if( memberBuyDTO == null ){
			return null ;
		}
		int period = memberBuyDTO.getPeriod();
		long userId = memberBuyDTO.getBuyerId() ;
		int outerType = memberBuyDTO.getOuterType() ;
		String outerId = memberBuyDTO.getOuterId() ;
		long sellerId = memberBuyDTO.getSellerId() ;
		
		
		MemberRecordDO record = new MemberRecordDO();
		record.setBuyerId(userId);
		record.setOuterId(outerId);
		record.setOuterType(outerType);
		record.setPeriod(period);
		record.setSellerId(sellerId);
		return record ;
	}
}
