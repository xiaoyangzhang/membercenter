package com.yimayhd.membercenter.service.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private MerchantRepo merchantRepo;
    @Autowired
    private MerchantItemCategoryManager merchantItemCategoryManager;
    @Autowired
    private ExamineDealService examineDealService;
    
	@Override
	public MemResult<List<MerchantItemCategoryDO>> findMerchantItemCategoriesByMerchant(int domainId, long examineId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemResultSupport saveMerchantItemCategoriesByMerchant(int domainId, long examineId, long[] categoryIds) {

		MemResult<ExamineInfoDTO> examineInfoMemResult = examineDealService.queryMerchantExamineInfoById(examineId);
		ExamineDO examineDO = ExamineConverter.examinDTOToDO(examineInfoMemResult.getValue());
		MemResultSupport memResultSupport = new MemResultSupport();
		if(examineDO == null) {
			memResultSupport.setReturnCode(MemberReturnCode.EXAMIN_DATA_ERROR);
			return memResultSupport;
		}

		memResultSupport = merchantItemCategoryManager.saveMerchanItemCategories(examineDO, categoryIds);
		return memResultSupport;
	}

}
