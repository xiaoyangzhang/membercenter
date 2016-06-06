package com.yimayhd.membercenter.dao.examine;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.membercenter.client.domain.merchant.BusinessScopeDO;
import com.yimayhd.membercenter.client.query.BusinessScopeQueryDTO;
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
	public List<BusinessScopeDO> getBusinessScope(BusinessScopeDO businessScope) {
		
		List<BusinessScopeDO> businessScopeList = businessScopeDOMapper.getBusinessScopes(businessScope,businessScope.getIdList());
		 return businessScopeList;
		
	}


}
