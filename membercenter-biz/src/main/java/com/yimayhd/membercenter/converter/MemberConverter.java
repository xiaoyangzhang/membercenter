package com.yimayhd.membercenter.converter;

import com.yimayhd.membercenter.client.domain.MemberDO;
import com.yimayhd.membercenter.client.enums.MemberStatus;
import com.yimayhd.membercenter.client.enums.MemberType;
import com.yimayhd.membercenter.entity.member.Member;
import com.yimayhd.membercenter.util.DateUtil;

public class MemberConverter {
	public static Member do2Member(MemberDO memberDO){
		if( memberDO == null ){
			return null;
		}
		Member member = new Member() ;
		member.id = memberDO.getId() ;
		member.code = memberDO.getCode() ;
		member.endTime = DateUtil.dateToLong(memberDO.getEndTime());
		member.startTime = DateUtil.dateToLong(memberDO.getStartTime()) ;
		MemberStatus memberStatus = MemberStatus.get(memberDO.getStatus()) ;
		member.status = memberStatus == null ? null : memberStatus.name() ;
		MemberType memberType = MemberType.get(memberDO.getType()) ;
		member.type = memberType == null ? null : memberType.name() ;
		member.userId = memberDO.getUserId() ;
		return member ;
	}
}
