package com.yimayhd.membercenter.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.domain.MerchantScopeDO;
import com.yimayhd.membercenter.client.domain.merchant.BusinessScopeDO;
import com.yimayhd.membercenter.client.domain.merchant.MerchantCategoryScopeDO;
import com.yimayhd.membercenter.client.query.BusinessScopeQueryDTO;
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
		if (queryDTO.getIdSet() != null && queryDTO.getIdSet().size() >0) {
			
			businessScope.setIdList(queryDTO.getIdSet());
		}
		
		return businessScopeDao.getBusinessScope(businessScope);
	}
	
	public List<MerchantCategoryScopeDO> getMerchantCategoryScope(BusinessScopeQueryDTO queryDTO) {
		MerchantCategoryScopeDO merchantCategoryScope = new MerchantCategoryScopeDO();
		merchantCategoryScope.setDomainId(queryDTO.getDomainId());
		if (queryDTO.getIdSet() != null && queryDTO.getIdSet().size() >0) {
			
			merchantCategoryScope.setIdSet(queryDTO.getIdSet());
		}
		merchantCategoryScope.setBusinessScopeId(queryDTO.getMerchantCategoryScopeId());
		merchantCategoryScope.setMerchantCategoryId(queryDTO.getMerchantCategoryId());
		return merchantCategoryScopeDao.getMerchantCategoryScope(merchantCategoryScope);
	}
	public List<MerchantScopeDO> getMerchantScope(BusinessScopeQueryDTO queryDTO) {
		MerchantScopeDO merchantScope = new MerchantScopeDO();
		merchantScope.setDomainId(queryDTO.getDomainId());
		merchantScope.setSellerId(queryDTO.getSellerId());
		return merchantScopeDao.getMerchantScope(merchantScope);
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
