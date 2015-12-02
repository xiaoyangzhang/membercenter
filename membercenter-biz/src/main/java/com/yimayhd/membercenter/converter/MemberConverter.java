package com.yimayhd.membercenter.converter;

import com.yimayhd.membercenter.client.domain.MemberDO;
import com.yimayhd.membercenter.client.domain.WxUserMerchantRelationDO;
import com.yimayhd.membercenter.client.enums.MemberStatus;
import com.yimayhd.membercenter.client.enums.MemberType;
import com.yimayhd.membercenter.client.vo.MerchantPageQueryVO;
import com.yimayhd.membercenter.entity.member.Member;
import com.yimayhd.membercenter.util.DateUtil;
import com.yimayhd.user.client.domain.UserDOPageQuery;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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


	public static UserDOPageQuery do2UserDOPageQuery(MerchantPageQueryVO merchantPageQueryVO,List<WxUserMerchantRelationDO> wxUserMerchantRelationDOList){
		Set<Long> userIdSet = new HashSet<Long>();
		for (WxUserMerchantRelationDO relationDO : wxUserMerchantRelationDOList) {
			userIdSet.add(relationDO.getUserId());
		}
		List<Long> userIdList = new ArrayList<Long>(userIdSet);
		UserDOPageQuery userDOPageQuery = new UserDOPageQuery();
		userDOPageQuery.setUserIdList(userIdList);
		userDOPageQuery.setPageNo(merchantPageQueryVO.getPageNo());
		userDOPageQuery.setPageSize(merchantPageQueryVO.getPageSize());
		userDOPageQuery.setMobile(merchantPageQueryVO.getMobile());
		userDOPageQuery.setNickname(merchantPageQueryVO.getNickName());
		userDOPageQuery.setCity(merchantPageQueryVO.getCity());
		return userDOPageQuery;
	}
}
