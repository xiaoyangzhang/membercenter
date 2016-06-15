package com.yimayhd.membercenter.service.client;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.domain.merchant.CategoryQualificationDO;
import com.yimayhd.membercenter.client.domain.merchant.MerchantQualificationDO;
import com.yimayhd.membercenter.client.domain.merchant.QualificationDO;
import com.yimayhd.membercenter.client.dto.ExamineInfoDTO;
import com.yimayhd.membercenter.client.query.QualificationQueryDTO;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.service.QualificationService;
import com.yimayhd.membercenter.manager.QualificationManager;

public class QualificationServiceImpl implements QualificationService {
	
	private static final Logger log = LoggerFactory.getLogger(QualificationServiceImpl.class);

	@Autowired
	private QualificationManager qualificationManager;

	@Override
	public MemResult<List<CategoryQualificationDO>> getCategoryQualification(QualificationQueryDTO queryDTO) {
		MemResult<List<CategoryQualificationDO>> result = new MemResult<List<CategoryQualificationDO>>();
		if (queryDTO == null || queryDTO.getDomainId() <= 0 ) {
			log.error(" param error : categoryQualificationDO={}",JSON.toJSONString(queryDTO));
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return result;
		}
		try {
			result = qualificationManager.getCategoryQualification(queryDTO);
		} catch (Exception e) {
			log.error(" param error : QualificationQueryDTO={} error:{}",JSON.toJSONString(queryDTO),e);
			result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
		}
		return result;
	}


	@Override
	public MemResult<Boolean> updateMerchantQualification(
			ExamineInfoDTO dto) {
		
		MemResult<Boolean> result = new MemResult<Boolean>();
		if (dto == null ) {
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return result;
		}
		try {
			result = qualificationManager.updateMerchantQualification(dto);
			return result;
		} catch (Exception e) {
			log.error("params : ExamineInfoDTO={}  error :{}",JSON.toJSONString(dto),e);
			result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
			return result;
		}
	}

	@Override
	public MemResult<List<QualificationDO>> getQualification(QualificationQueryDTO queryDTO) {
		MemResult<List<QualificationDO>> result = new MemResult<List<QualificationDO>>();
		if (queryDTO == null || (queryDTO.getIdSet() != null && queryDTO.getIdSet().size() == 0) || queryDTO.getDomainId() <= 0 ) {
			log.error("params: QualificationQueryDTO={} ",JSON.toJSONString(queryDTO));
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return result;
		}
		try {
			result = qualificationManager.getQualification(queryDTO);
			return result;
		} catch (Exception e) {
			log.error("params :QualificationQueryDTO={} error:{}",JSON.toJSONString(queryDTO),e);
			result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
			return result;
		}
	}

	@Override
	public MemResult<List<MerchantQualificationDO>> getMerchantQualification(QualificationQueryDTO queryDTO) {
		MemResult<List<MerchantQualificationDO>> result = new MemResult<List<MerchantQualificationDO>>();
		if (queryDTO == null) {
			log.error("param: QualificationQueryDTO={}",queryDTO);
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return result;
		}
		try {
			result = qualificationManager.getMerchantQualification(queryDTO);
			//return result;
		} catch (Exception e) {
			log.error("params :QualificationQueryDTO={} error:{}",JSON.toJSONString(queryDTO),e);
			result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
		}
		return result;
	}

	@Override
	public MemResult<Boolean> updateMerchantQualificationStatus(
			QualificationQueryDTO queryDTO) {
		MemResult<Boolean> result = new MemResult<Boolean>();
		if (queryDTO == null || queryDTO.getDomainId() <= 0 ) {
			log.error(" param error : categoryQualificationDO={}",JSON.toJSONString(queryDTO));
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return result;
		}
		try {
			result = qualificationManager.updateMerchantQualificationStatus(queryDTO);
		} catch (Exception e) {
			log.error(" param error : QualificationQueryDTO={} error:{}",JSON.toJSONString(queryDTO),e);
			result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
		}
		return result;
	}

	@Override
	public MemResult<Integer> updateStatusBatch(
			List<QualificationQueryDTO> qualificationQueryDTOs) {
		MemResult<Integer> result = new MemResult<Integer>();
		if (qualificationQueryDTOs == null /*|| qualificationQueryDTOs.getDomainId() <= 0 */) {
			log.error(" param error : QualificationQueryDTO={}",JSON.toJSONString(qualificationQueryDTOs));
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return result;
		}
		try {
			result = qualificationManager.updateStatusBatch(qualificationQueryDTOs);
		} catch (Exception e) {
			log.error(" param error : QualificationQueryDTO={} error:{}",JSON.toJSONString(qualificationQueryDTOs),e);
			result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
		}
		return result;
	}

	@Override
	public MemResult<Boolean> getQualificationRequired(QualificationQueryDTO queryDTO) {
		return qualificationManager.getQualificationRequired(queryDTO);
	}

}
