package com.yimayhd.membercenter.converter;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.yimayhd.membercenter.client.domain.MemberPrivilegeDO;
import com.yimayhd.membercenter.client.query.MemPrivilegePageQuery;
import com.yimayhd.membercenter.entity.PageInfo;
import com.yimayhd.membercenter.entity.PrivilegeInfo;
import com.yimayhd.membercenter.entity.PrivilegeInfoPageList;

public class PrivilegeConverter {

	public static PrivilegeInfoPageList privilegeInfoListConverter(List<MemberPrivilegeDO> privilegeDOs,int pageNo,boolean hasNext){
		
		if(CollectionUtils.isEmpty(privilegeDOs)){
			
			return null;
		}
		
		
		PrivilegeInfoPageList pageInfoList = new PrivilegeInfoPageList();
		
		pageInfoList.pageNo = pageNo;
		pageInfoList.hasNext = hasNext;
		 
		List<PrivilegeInfo> privilegeInfoList = dos2PrivilegeInfos(privilegeDOs);
		pageInfoList.privilegeInfoPageList = privilegeInfoList ;
		
		
		return  pageInfoList;
	}
	
	
	

	public static List<PrivilegeInfo> dos2PrivilegeInfos(List<MemberPrivilegeDO> privilegeDOs) {
		
		if( CollectionUtils.isEmpty(privilegeDOs) ) {
			return null;
		}
		List<PrivilegeInfo> privilegeInfoList = new ArrayList<PrivilegeInfo>();
		
		for (MemberPrivilegeDO privilegeDO : privilegeDOs) {
			
			privilegeInfoList.add(privilegeInfo(privilegeDO));
		}
		
		return privilegeInfoList;
	}
	
	private static PrivilegeInfo privilegeInfo(MemberPrivilegeDO privilegeDO) {
		
		if(null == privilegeDO){
			return null;
		}
		PrivilegeInfo privilegeInfo = new PrivilegeInfo();
		
		privilegeInfo.title = privilegeDO.getTitle();
		privilegeInfo.imageUrl = privilegeDO.getImageUrl();
		privilegeInfo.imageShowUrl = privilegeDO.getImageShowUrl();
		privilegeInfo.description = privilegeDO.getDescription();
		
		
		return privilegeInfo;
	}
	
	public static MemPrivilegePageQuery memPrivilegePageQuery(PageInfo pageInfo){
		
		MemPrivilegePageQuery query = new MemPrivilegePageQuery();
		query.setHasNextMod(true);
		query.setPageNo(pageInfo.pageNo);
		query.setPageSize(pageInfo.pageSize);
		
		return query;
	}
}
