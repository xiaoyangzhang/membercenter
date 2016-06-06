package com.yimayhd.membercenter.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.domain.MerchantScopeDO;
import com.yimayhd.membercenter.client.domain.merchant.BusinessScopeDO;
import com.yimayhd.membercenter.client.domain.merchant.MerchantCategoryScopeDO;
import com.yimayhd.membercenter.client.domain.merchant.MerchantQualificationDO;
import com.yimayhd.membercenter.client.query.BusinessScopeQueryDTO;
import com.yimayhd.membercenter.client.query.QualificationQueryDTO;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.converter.MerchantConverter;
import com.yimayhd.membercenter.dao.examine.BusinessScopeDao;
import com.yimayhd.membercenter.dao.examine.MerchantCategoryScopeDao;
import com.yimayhd.membercenter.dao.examine.MerchantScopeDao;
import com.yimayhd.membercenter.manager.talent.TalentExamineManager;

/**
 * Created by hanlei on 2016/5/24.
 */
public class BusinessScopeManager {

	@Autowired
	private BusinessScopeDao businessScopeDao;
	@Autowired
	private MerchantCategoryScopeDao merchantCategoryScopeDao;
	@Autowired
	private  TalentExamineManager talentExamineManager;
    @Autowired
	private MerchantScopeDao merchantScopeDao;
	public List<BusinessScopeDO> getBusinessScopesByScope(BusinessScopeQueryDTO queryDTO) {
		BusinessScopeDO businessScope = new BusinessScopeDO();
		businessScope.setDomainId(queryDTO.getDomainId());
		businessScope.setIdList(queryDTO.getIdSet());
		return businessScopeDao.getBusinessScope(businessScope);
	}
	
	public List<MerchantCategoryScopeDO> getMerchantCategoryScope(BusinessScopeQueryDTO queryDTO) {
		return merchantCategoryScopeDao.getMerchantCategoryScope(queryDTO);
	}
	public List<MerchantScopeDO> getMerchantScope(BusinessScopeQueryDTO queryDTO) {
		return merchantScopeDao.getMerchantScope(queryDTO);
	}
	public MemResult<Boolean> updateMerchantScopeStatus(BusinessScopeQueryDTO queryDTO) {
		 MemResult<Boolean> result  = new  MemResult<Boolean>();
		 MerchantScopeDO merchantScope = MerchantConverter.convertQueryScopeVO2DO(queryDTO);
		 MerchantScopeDO updateResult = merchantScopeDao.update(merchantScope);
		if (updateResult == null) {
			result.setReturnCode(MemberReturnCode.DB_UPDATE_FAILED);
			
		}
		return result;
	}
//	public MemResult<List<BusinessScopeDO>> getBusinessScope(BusinessScopeDO businessScope,List<Long> idList) {
//		MemResult<List<BusinessScopeDO>> result = new MemResult<List<BusinessScopeDO>>();
//		List<BusinessScopeDO> businessScopeList = businessScopeDao.getBusinessScope(businessScope,idList);
//		 if(businessScopeList == null) {
//			 result.setReturnCode(MemberReturnCode.BUSINESS_SCOPE_FAILED);
//			 return result;
//		 }
//		 result.setValue(businessScopeList);
//		 return result;
//
//	}
//	
//	public MemResult<List<BusinessScopeDO>> getBusinessScopes(BusinessScopeDO businessScope,List<Long> idList) {
//		MemResult<List<BusinessScopeDO>> result = new MemResult<>();
//
//		List<BusinessScopeDO> businessScopeDOs = businessScopeDao.getBusinessScope(businessScope,idList);
//		if(businessScopeDOs.isEmpty()) {
//			result.setReturnCode(MemberReturnCode.BUSINESS_SCOPE_FAILED);
//			return result;
//		}
//		result.setValue(businessScopeDOs);
//		return result;
//	}
	public MemResult<Integer> updateStatusBatch(List<BusinessScopeQueryDTO> queryDTOs) {
		 MemResult<Integer> result  = new  MemResult<Integer>();
		 List<MerchantScopeDO> scopeList = new ArrayList<MerchantScopeDO>();
		 for (BusinessScopeQueryDTO queryDTO : queryDTOs) {
			 MerchantScopeDO merchantScope = MerchantConverter.convertQueryScopeVO2DO(queryDTO);
			 merchantScope.setGmtModified(new Date());
				scopeList.add(merchantScope);
		}
		 int updateCount = merchantScopeDao.updateStatusBatch(scopeList);
		 if (updateCount < queryDTOs.size()) {
			 result.setReturnCode(MemberReturnCode.DB_UPDATE_FAILED);
			 return result;
		}
		 result.setValue(updateCount);
		 return result;
	}
}
