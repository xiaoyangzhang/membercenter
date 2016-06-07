package com.yimayhd.membercenter.service.client.examine;

import com.alibaba.fastjson.JSON;
import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.domain.MerchantScopeDO;
import com.yimayhd.membercenter.client.domain.merchant.*;
import com.yimayhd.membercenter.client.dto.ExamineInfoDTO;
import com.yimayhd.membercenter.client.query.MerchantCategoryQueryDTO;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.service.examine.MerchantApplyService;
import com.yimayhd.membercenter.manager.ApplyManager;
import com.yimayhd.membercenter.manager.MerchantApplyManager;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MerchantApplyServiceImpl implements MerchantApplyService {
	
	private static final Logger log = LoggerFactory.getLogger(MerchantApplyServiceImpl.class);

	@Autowired
	private MerchantApplyManager merchantApplyManager;


	@Override
	public MemResult<Boolean> submitExamineInfo(ExamineInfoDTO dto) {
		MemResult<Boolean> result = new MemResult<Boolean>();
		if (dto == null ) {
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return result;
		}
		try {
			result = merchantApplyManager.submitExamineInfo(dto);
			return result;
		} catch (Exception e) {
			log.error("params : ExamineInfoDTO={}   error :{}",JSON.toJSONString(dto),e);
			result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
			return result;
		}
	}


	@Override
	public MemResult<List<MerchantCategoryDO>> getMerchantCategory(
			MerchantCategoryQueryDTO merchantCategoryQueryDTO) {
		MemResult<List<MerchantCategoryDO>> result = new MemResult<List<MerchantCategoryDO>>();
		if (merchantCategoryQueryDTO == null || merchantCategoryQueryDTO.getDomainId() <= 0) {
			log.error("params error:MerchantCategoryQueryDTO={}",JSON.toJSONString(merchantCategoryQueryDTO));
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return result;
			
		}
		try {
			result = merchantApplyManager.getMerchantCategory(merchantCategoryQueryDTO);
		} catch (Exception e) {
			log.error("params : MerchantCategoryQueryDTO={}   error :{}",JSON.toJSONString(merchantCategoryQueryDTO),e);
			result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
		}
		return result;
	}

	

}
