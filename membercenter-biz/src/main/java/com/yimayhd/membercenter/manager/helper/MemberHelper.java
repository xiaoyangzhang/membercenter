package com.yimayhd.membercenter.manager.helper;

import java.util.Calendar;
import java.util.Date;

import com.yimayhd.membercenter.client.domain.MemberDO;
import com.yimayhd.membercenter.client.dto.MemberBuyDTO;
import com.yimayhd.membercenter.client.enums.MemberType;

public class MemberHelper {

	public static MemberDO createMember(MemberBuyDTO memberBuyDTO){
		if( memberBuyDTO == null ){
			return null ;
		}
		int period = memberBuyDTO.getPeriod();
		long userId = memberBuyDTO.getBuyerId() ;
		
		Calendar calendar = Calendar.getInstance() ;
		calendar.add(Calendar.DATE, period);
		MemberDO memberDO = new MemberDO() ;
		memberDO.setStartTime(new Date());
		memberDO.setEndTime(calendar.getTime());
		memberDO.setType(MemberType.VIP.getType());
		memberDO.setUserId(userId);
		return memberDO ;
	}
}
