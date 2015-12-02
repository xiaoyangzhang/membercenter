package com.yimayhd.membercenter.manager.helper;

import java.util.Calendar;
import java.util.Date;

import com.yimayhd.membercenter.client.constant.MemberConstant;
import com.yimayhd.membercenter.client.domain.MemberDO;
import com.yimayhd.membercenter.client.dto.MemberBuyDTO;
import com.yimayhd.membercenter.client.enums.MemberType;
import com.yimayhd.membercenter.converter.MemberConverter;
import com.yimayhd.membercenter.util.CodeUtil;
import com.yimayhd.membercenter.util.DateUtil;

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
		memberDO.setStartTime(DateUtil.getDateStart(new Date()));
		memberDO.setEndTime(DateUtil.getDateEnd(calendar.getTime()));
		memberDO.setType(MemberType.VIP.getType());
		memberDO.setUserId(userId);
		String code = CodeUtil.getNumberCode(MemberConstant.MEMBER_CODE_LENGTH);
		memberDO.setCode(code);
		return memberDO ;
	}
}
