package com.yimayhd.membercenter.client.service;

import java.util.List;

import com.yimayhd.membercenter.client.domain.merchant.CategoryQualificationDO;
import com.yimayhd.membercenter.client.domain.merchant.MerchantQualificationDO;
import com.yimayhd.membercenter.client.domain.merchant.QualificationDO;
import com.yimayhd.membercenter.client.dto.ExamineInfoDTO;
import com.yimayhd.membercenter.client.query.QualificationQueryDTO;
import com.yimayhd.membercenter.client.result.MemResult;






/**
 * 〈一句话功能简述〉<br>
 * 〈入驻申请service〉
 *
 * @author zhangxy
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

public interface QualificationService {
	
	/**
	 * 获取资质<br/>
	 * @author zhangxy 24/05/2016
	 * @param idList, domainId
	 * @param domainId
	 * @return
	 */
	public MemResult<List<QualificationDO>> getQualification(QualificationQueryDTO queryDTO);
	/**
	 * 获取商家类目与资质关联对象的集合<br/>
	 * @author zhangxy 24/05/2016
	 * @param domainId
	 * @param merchantCategoryId
	 * @return
	 */
	public MemResult<List<CategoryQualificationDO>> getCategoryQualification(QualificationQueryDTO queryDTO);
	

	/**
	 * 
	* created by zhangxy
	* @date 2016年5月28日
	* @Title: updateMerchantQualification 
	* @Description: 保存/更新商户与资质的关联关系
	* @param @param merchantQualification
	* @param @return    设定文件 
	* @return MemResult<Boolean>    返回类型 
	* @throws
	 */
	public MemResult<Boolean> updateMerchantQualification(ExamineInfoDTO dto);
	

	public MemResult<List<MerchantQualificationDO>> getMerchantQualification(QualificationQueryDTO queryDTO) ;
	/**
	 * 更新商家与资质关联关系的可用性
	* created by zhangxy
	* @date 2016年6月5日
	* @Title: updateMerchantScopeStatus 
	* @Description: TODO
	* @param @param queryDTO
	* @param @return    设定文件 
	* @return MemResult<Boolean>    返回类型 
	* @throws
	 */
	public MemResult<Boolean> updateMerchantQualificationStatus(QualificationQueryDTO queryDTO);
	
	public MemResult<Integer> updateStatusBatch(List<QualificationQueryDTO> qualificationQueryDTOs) ;
		
	MemResult<Boolean> getQualificationRequired(QualificationQueryDTO queryDTO);
	
}
