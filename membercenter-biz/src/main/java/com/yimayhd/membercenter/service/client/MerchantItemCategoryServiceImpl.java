package com.yimayhd.membercenter.service.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yimayhd.membercenter.enums.ExamineStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.domain.examine.ExamineDO;
import com.yimayhd.membercenter.client.domain.merchant.MerchantItemCategoryDO;
import com.yimayhd.membercenter.client.dto.ExamineDealDTO;
import com.yimayhd.membercenter.client.dto.ExamineInfoDTO;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.result.MemResultSupport;
import com.yimayhd.membercenter.client.service.MerchantItemCategoryService;
import com.yimayhd.membercenter.client.service.examine.ExamineDealService;
import com.yimayhd.membercenter.converter.ExamineConverter;
import com.yimayhd.membercenter.manager.MerchantItemCategoryManager;
import com.yimayhd.membercenter.mapper.ExamineDOMapper;
import com.yimayhd.membercenter.repo.MerchantRepo;
import com.yimayhd.user.client.domain.MerchantDO;

public class MerchantItemCategoryServiceImpl implements MerchantItemCategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MerchantItemCategoryServiceImpl.class);

    @Autowired
    ExamineDOMapper examineDOMapper;
    @Autowired
    private MerchantItemCategoryManager merchantItemCategoryManager;
    @Autowired
    private ExamineDealService examineDealService;
    
	@Override
	public MemResult<List<MerchantItemCategoryDO>> findMerchantItemCategoriesBySellerId(int domainId, long sellerId) {
		MemResult<List<MerchantItemCategoryDO>> merchantItemCategoryResult = new MemResult<>();
		if(domainId <= 0 || sellerId <= 0) {
			merchantItemCategoryResult.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return merchantItemCategoryResult;
		}

		List<MerchantItemCategoryDO> merchantItemCategoryDOs = merchantItemCategoryManager.findMerchantItemCategoryByMerchant(domainId, sellerId);
		merchantItemCategoryResult.setValue(merchantItemCategoryDOs);
		return merchantItemCategoryResult;
	}

	@Override
	public MemResultSupport saveMerchantItemCategories(int domainId, long examineId, long[] categoryIds) {
		MemResultSupport memResultSupport = new MemResultSupport();
		if(domainId <= 0 || examineId <= 0 || null == categoryIds || categoryIds.length == 0) {
			memResultSupport.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return memResultSupport;
		}
		MemResult<ExamineInfoDTO> examineInfoMemResult = examineDealService.queryMerchantExamineInfoById(examineId);
		ExamineDO examineDO = ExamineConverter.examinDTOToDO(examineInfoMemResult.getValue());
		if(examineDO == null) {
			memResultSupport.setReturnCode(MemberReturnCode.EXAMIN_DATA_ERROR);
			return memResultSupport;
		}
		examineDO.setStatues(ExamineStatus.EXAMIN_OK.getStatus());
		memResultSupport = merchantItemCategoryManager.saveMerchanItemCategories(examineDO, categoryIds);
		return memResultSupport;
	}

	@Override
	public MemResultSupport checkCategoryPrivilege(int domainId, long categoryId, long sellerId) {
		MemResultSupport support = new MemResultSupport();
		if(domainId <= 0 || categoryId <= 0 || sellerId <= 0) {
			support.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return support;
		}
		return merchantItemCategoryManager.checkCategoryPrivilege(domainId,categoryId,sellerId);
	}

}
