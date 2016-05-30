package com.yimayhd.membercenter.dao.examine;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.membercenter.client.domain.merchant.BusinessScopeDO;
import com.yimayhd.membercenter.mapper.BusinessScopeDOMapper;

/**
 * 
* @ClassName: BusinessScopeDao
* @Description: 操作经营范围
* @author zhangxy
* @date 2016年5月26日 上午11:20:48
*
 */
public class BusinessScopeDao {

	@Autowired
	private BusinessScopeDOMapper businessScopeDOMapper;
	public List<BusinessScopeDO> getAllBusinessScope(int domainId) {
		//List<BusinessScopeDO> businessScopeList = new ArrayList<BusinessScopeDO>();
//		if (domainId <= 0) {
//			return null;
			//return MemResult.buildFailResult(-1, "参数错误", businessScopeList);
		//}
		List<BusinessScopeDO> businessScopeList = businessScopeDOMapper.getAllBusinessScope(domainId);
//		 if(businessScopeList == null) {
//			 return null;
//		 }
		// return MemResult.buildSuccessResult(businessScopeList);
		 return businessScopeList;
		
	}

//	public List<BusinessScopeDO> getBusinessScopesByIds(int domianId, long[] ids) {
//		return businessScopeDOMapper.getBusinessScopesByScope(domianId,ids);
//	}
//	public List<BusinessScopeDO> getBusinessScopesByScope(int domainId, long[] scopeIds) {
	
//	public List<BusinessScopeDO> getBusinessScopesByScope(int domainId, Long[] scopeIds) {
//		return businessScopeDOMapper.getBusinessScopesByScope(domainId, scopeIds);
//	}
	public List<BusinessScopeDO> getBusinessScopesByScope(int domainId, List<Long> scopeIds) {
		return businessScopeDOMapper.getBusinessScopesByScope(domainId, scopeIds);
	}
}
