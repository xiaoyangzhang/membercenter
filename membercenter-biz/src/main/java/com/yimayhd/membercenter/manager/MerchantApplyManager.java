package com.yimayhd.membercenter.manager;

import java.util.List;






import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.alibaba.fastjson.JSON;
import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.domain.MerchantScopeDO;
import com.yimayhd.membercenter.client.domain.merchant.MerchantCategoryDO;
import com.yimayhd.membercenter.client.dto.ExamineInfoDTO;
import com.yimayhd.membercenter.client.query.BusinessScopeQueryDTO;
import com.yimayhd.membercenter.client.query.MerchantCategoryQueryDTO;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.dao.examine.MerchantCategoryDao;
import com.yimayhd.membercenter.dao.examine.MerchantScopeDao;
import com.yimayhd.membercenter.idgen.IDPool;
import com.yimayhd.membercenter.manager.talent.TalentExamineManager;

/**
 * 〈一句话功能简述〉<br>
 * 〈入驻申请manager〉
 *
 * @author zhangxy
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class MerchantApplyManager {
	private static final Logger log  = LoggerFactory.getLogger(MerchantApplyManager.class);
	@Autowired
	private TransactionTemplate transactionTemplate ;
	@Autowired
	private IDPool memberIdpool ;
	@Autowired
	private  TalentExamineManager talentExamineManager;
    @Autowired
	private MerchantScopeDao merchantScopeDao;
	@Autowired
	private MerchantCategoryDao merchantCategoryDao;
	
	public MemResult<Boolean> submitExamineInfo(final ExamineInfoDTO examineInfoDTO) {
		MemResult<Boolean> result = new MemResult<Boolean>();
		if ((examineInfoDTO == null) ) {
			log.error("param :examineInfoDTO={}",JSON.toJSONString(examineInfoDTO));
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return result;
		}
		final List<MerchantScopeDO> merchantScopes = examineInfoDTO.getMerchantScopes();
//		BusinessScopeQueryDTO businessScopeQueryDTO = new BusinessScopeQueryDTO();
//		businessScopeQueryDTO.setDomainId(examineInfoDTO.getDomainId());
//		businessScopeQueryDTO.setSellerId(examineInfoDTO.getSellerId());
//		List<MerchantScopeDO> merchantScopeList = merchantScopeDao.getMerchantScope(businessScopeQueryDTO);
//		if (merchantScopeList != null) {
//			for (MerchantScopeDO merchantScopeDO : merchantScopeList) {
////				MerchantScopeDO newMerchantScopeDO = new  MerchantScopeDO();
////				newMerchantScopeDO.setId(merchantScopeDO.getId());
//				merchantScopeDO.setStatus(-1);
//				merchantScopeDao.update(merchantScopeDO);
//			}
//		}
				Boolean dbResult = transactionTemplate.execute(new TransactionCallback<Boolean>() {

					@Override
					public Boolean doInTransaction(TransactionStatus status) {
						try {
							MemResult<Boolean> saveExamineResult = null;
							saveExamineResult = talentExamineManager.submitMerchantExamineInfo(examineInfoDTO);
							if ((saveExamineResult == null) || !saveExamineResult.isSuccess()) {
								status.setRollbackOnly();
								return false;
							}
		
							MerchantScopeDO merScopeDO = null;
							for (MerchantScopeDO ms : merchantScopes) {
								
							//	if (ms.getId() <= 0) {
									merScopeDO = merchantScopeDao.insert(ms);
//								}else {
//									merScopeDO = merchantScopeDao.update(ms);
//								}
								if (merScopeDO == null) {
									status.setRollbackOnly();
									return false;
								}
							}
							return true;
						} catch (Exception e) {

							status.setRollbackOnly(); 
							log.error("param:examineInfoDTO={}   error:{}", JSON.toJSONString(examineInfoDTO) ,e);
							
							return false;
						}
					}
				});
				
		if (dbResult == null || !dbResult) {
			result.setReturnCode(MemberReturnCode.DB_WRITE_FAILED);
			return result;
		}
		return result ;

	}
	public MemResult<List<MerchantCategoryDO>> getMerchantCategory(MerchantCategoryQueryDTO merchantCategoryQueryDTO) {
		MemResult<List<MerchantCategoryDO>> result = new MemResult<List<MerchantCategoryDO>>();
		MerchantCategoryDO merchantCategory  = new MerchantCategoryDO();
		merchantCategory.setDomainId(merchantCategoryQueryDTO.getDomainId());
		merchantCategory.setId(merchantCategoryQueryDTO.getId());
		List<MerchantCategoryDO> merchantCategoryList = merchantCategoryDao.getMerchantCategory(merchantCategory);
		if (merchantCategoryList == null) {
			result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
			return result;
		}
		result.setValue(merchantCategoryList);
		return result;
		
	}
	
}
