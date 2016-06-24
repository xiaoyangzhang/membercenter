package com.yimayhd.membercenter.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;













import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.alibaba.fastjson.JSON;
import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.dto.ExamineInfoDTO;
import com.yimayhd.membercenter.client.dto.MerchantQualificationDTO;
import com.yimayhd.membercenter.client.query.QualificationQueryDTO;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.domain.examine.ExamineDO;
import com.yimayhd.membercenter.client.domain.merchant.CategoryQualificationDO;
import com.yimayhd.membercenter.client.domain.merchant.MerchantQualificationDO;
import com.yimayhd.membercenter.client.domain.merchant.QualificationDO;
import com.yimayhd.membercenter.converter.MerchantConverter;
import com.yimayhd.membercenter.dao.examine.CategoryQualificationDao;
import com.yimayhd.membercenter.dao.examine.MerchantApplyDao;
import com.yimayhd.membercenter.dao.examine.MerchantQualificationDao;
import com.yimayhd.membercenter.dao.examine.QualificationDao;
import com.yimayhd.membercenter.manager.talent.TalentExamineManager;

/**
 * 〈一句话功能简述〉<br>
 * 〈入驻申请manager〉
 *
 * @author zhangxy
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class QualificationManager {
	private static final Logger log  = LoggerFactory.getLogger(QualificationManager.class);
	@Autowired
	private  MerchantApplyDao merchantApplyDao;
	@Autowired
	private QualificationDao qualificationDao;
	@Autowired
	private CategoryQualificationDao categoryQualificationDao;
	@Autowired
	private MerchantQualificationDao merchantQualificationDao;
	@Autowired
	private TransactionTemplate transactionTemplate;
	@Autowired
	private TalentExamineManager talentExamineManager;
	public MemResult<List<QualificationDO>> getQualification(QualificationQueryDTO queryDTO) {
		MemResult<List<QualificationDO>> result = new MemResult<List<QualificationDO>>();
		QualificationDO qualification = new QualificationDO();
		qualification.setDomainId(queryDTO.getDomainId());
		if (queryDTO.getIdSet() != null && queryDTO.getIdSet().size() >0) {
			
			qualification.setIdList(queryDTO.getIdSet());
		}
		List<QualificationDO> qualificationList = qualificationDao.getQualification(qualification);
		if (qualificationList == null) {
			result.setReturnCode(MemberReturnCode.QUALIFICATION_FAILED);
			return result;
		}
		result.setValue(qualificationList);
		return result;
	}

	public MemResult<List<CategoryQualificationDO>> getCategoryQualification(QualificationQueryDTO queryDTO) {
		MemResult<List<CategoryQualificationDO>> result = new MemResult<List<CategoryQualificationDO>>();
		CategoryQualificationDO categoryQualification = new CategoryQualificationDO();
		categoryQualification.setDomainId(queryDTO.getDomainId());
		categoryQualification.setIsDirectSale(queryDTO.getDirectSale());
		if (queryDTO.getIdSet() != null && queryDTO.getIdSet().size() >0) {
			
			categoryQualification.setScopeIdsList(queryDTO.getIdSet());
		}
		categoryQualification.setMerchantCategoryId(queryDTO.getMerchantCategoryId());
		List<CategoryQualificationDO> qualifications = categoryQualificationDao.getCategoryQualification(categoryQualification);
		if (qualifications == null) {
			result.setReturnCode(MemberReturnCode.CATEGORY_QUALIFICATION_FAILED);
			return result;
		}
		result.setValue(qualifications);
		return result;

	}

	public MemResult<List<MerchantQualificationDO>> getMerchantQualification(QualificationQueryDTO qualificationQueryDTO) {
		MemResult<List<MerchantQualificationDO>> result = new MemResult<List<MerchantQualificationDO>>();
		MerchantQualificationDO merchantQualification= new MerchantQualificationDO();
		merchantQualification.setDomainId(qualificationQueryDTO.getDomainId());
		merchantQualification.setSellerId(qualificationQueryDTO.getSellerId());
		List<MerchantQualificationDO> merchantQualifications = merchantQualificationDao.getMerchantQualification(merchantQualification);
		if (merchantQualifications == null) {
			result.setReturnCode(MemberReturnCode.MERCHANT_QUALIFICATION_FAILED);
			return result;
		}
		result.setValue(merchantQualifications);
		return result;
	}

	
	public MemResult<Boolean> updateMerchantQualification(final ExamineInfoDTO examineInfoDTO) {
		MemResult<Boolean> result = new MemResult<Boolean>();
		if (examineInfoDTO == null) {
			log.error("param error : merchantQualificationDO={}",JSON.toJSONString(examineInfoDTO));
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return result;
		}
		 
		final List<MerchantQualificationDO> merchantQualifications = examineInfoDTO.getMerchantQualifications();
		Boolean dbResult = transactionTemplate.execute(new TransactionCallback<Boolean>() {

			@Override
			public Boolean doInTransaction(TransactionStatus status) {
				boolean result = true;
				try {
					MerchantQualificationDO merchantQualification = new MerchantQualificationDO();
					 merchantQualification.setDomainId(examineInfoDTO.getDomainId());
					 merchantQualification.setSellerId(examineInfoDTO.getSellerId());
					 List<MerchantQualificationDO> merchantQualificationList = merchantQualificationDao.getMerchantQualification(merchantQualification);
						if (merchantQualificationList != null) {
							for (MerchantQualificationDO merchantQualificationDO : merchantQualificationList) {
								merchantQualificationDO.setStatus(-1);
								merchantQualificationDO.setGmtModified(new Date());
								merchantQualificationDao.update(merchantQualificationDO);
							}
						}
					MerchantQualificationDO merQuaDO = null;
					for (MerchantQualificationDO mq : merchantQualifications) {
						
						merQuaDO = merchantQualificationDao.insert(mq);
						if(merQuaDO == null) {
							status.setRollbackOnly();
							return false;
						}
					}
//					ExamineDO examineDO = new ExamineDO();
//			        examineDO.setSellerId(examineInfoDTO.getSellerId());
//			        examineDO.setDomainId(examineInfoDTO.getDomainId());
//			        examineDO.setType(examineInfoDTO.getType());
				//	MemResult<Boolean> changeStatusResult = talentExamineManager.changeExamineStatus(examineDO);
//					if (changeStatusResult == null || !changeStatusResult.isSuccess()) {
//						status.setRollbackOnly();
//						return false;
//					}
				} catch (Exception e) {
					status.setRollbackOnly(); 
					log.error("param:examineInfoDTO={}   error:{}", JSON.toJSONString(examineInfoDTO) ,e);
					result = false;
				}
				return result;
			}
		
		});
		if (dbResult == null || !dbResult) {
			result.setReturnCode(MemberReturnCode.DB_WRITE_FAILED);
			return result;
		}
		return result;
	}
	
	public MemResult<Boolean> updateMerchantQualificationStatus(QualificationQueryDTO queryDTO) {
		 MemResult<Boolean> result  = new  MemResult<Boolean>();
		 MerchantQualificationDO merchantQualification = new MerchantQualificationDO();
		 merchantQualification.setDomainId(queryDTO.getDomainId());
		 merchantQualification.setSellerId(queryDTO.getSellerId());
		 List<MerchantQualificationDO> merchantQualificationList = merchantQualificationDao.getMerchantQualification(merchantQualification);
			if (merchantQualificationList != null) {
				for (MerchantQualificationDO merchantQualificationDO : merchantQualificationList) {
					merchantQualificationDO.setStatus(-1);
					merchantQualificationDO.setGmtModified(new Date());
					merchantQualificationDao.update(merchantQualificationDO);
				}
			}
		MerchantQualificationDO newMerchantQualification = MerchantConverter.convertQueryQualification2DO(queryDTO);
		MerchantQualificationDO updateResult = merchantQualificationDao.insert(newMerchantQualification);
		if (updateResult == null) {
			result.setReturnCode(MemberReturnCode.DB_UPDATE_FAILED);
			
		}
		return result;
	}
	
	public MemResult<Integer> updateStatusBatch(List<QualificationQueryDTO> queryDTOs) {
		 MemResult<Integer> result  = new  MemResult<Integer>();
		 List<MerchantQualificationDO> qualificationDOList = new ArrayList<MerchantQualificationDO>();
		 for (QualificationQueryDTO qualificationQueryDTO : queryDTOs) {
				MerchantQualificationDO merchantQualification = MerchantConverter.convertQueryQualification2DO(qualificationQueryDTO);
				merchantQualification.setGmtModified(new Date());
				qualificationDOList.add(merchantQualification);
		}
		 int updateCount = merchantQualificationDao.updateStatusBatch(qualificationDOList);
		 if (updateCount < queryDTOs.size()) {
			 result.setReturnCode(MemberReturnCode.DB_UPDATE_FAILED);
			 return result;
		}
		 result.setValue(updateCount);
		 return result;
	}

	public MemResult<Boolean> getQualificationRequired(QualificationQueryDTO dto) {
		Boolean result = categoryQualificationDao.getQualificationRequired(dto);
		MemResult<Boolean> memResult = new MemResult<>();
		memResult.setValue(result);
		return memResult;
	}
	
	public MemResult<Boolean> insertMerchantQualification(MerchantQualificationDTO dto) {
		MemResult<Boolean> result = new MemResult<Boolean>();
		try {
			MerchantQualificationDO merchantQualificationDO = MerchantConverter.convertMerchantQualificationDTO2DO(dto);
			MerchantQualificationDO insertResult = merchantQualificationDao.insert(merchantQualificationDO);
			if (insertResult == null) {
				result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
				return result;
			}
		} catch (Exception e) {
			log.error("params:MerchantQualificationDTO={} exception:{}",JSON.toJSONString(dto),e);
			result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
		}
		return result;
	}
}
