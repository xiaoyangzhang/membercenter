package com.yimayhd.membercenter.client.service.examine;

import com.yimayhd.membercenter.client.domain.MerchantScopeDO;
import com.yimayhd.membercenter.client.domain.merchant.*;
import com.yimayhd.membercenter.client.dto.ExamineInfoDTO;
import com.yimayhd.membercenter.client.result.MemResult;

import java.util.List;





/**
 * 〈一句话功能简述〉<br>
 * 〈入驻申请service〉
 *
 * @author zhangxy
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

public interface ApplyService {
	
	/** 
	 * 获取用户与经营范围的关联对象集合<br/>
	 * @author zhangxy 24/05/2016
	 * @param sellerId
	 * @param domainId
	 * @return
	 */
	public MemResult<List<BusinessScopeDO>> getBusinessScope(BusinessScopeDO businessScope,List<Long> idList);
	/**
	 * 获取资质<br/>
	 * @author zhangxy 24/05/2016
	 * @param idList, domainId
	 * @param domainId
	 * @return
	 */
	public MemResult<List<QualificationDO>> getQualification(QualificationDO qualification,List<Long> idList);
	/**
	 * 获取商家的经营范围<br/>
	 * @author zhangxy 24/05/2016
	 * @param idList, domainId
	 * @param domainId
	 * @return
	 */
	//public MemResult<List<BusinessScopeDO>> getBusinessScope(List<Long> idList,int domainId);
	/**
	 * 获取用户的资质<br/>
	 * @author zhangxy 24/05/2016
	 * @param sellerId
	 * @param domainId
	 * @return
	 */
	public MemResult<List<QualificationDO>> getQualificationBySellerId(MerchantQualificationDO merchantQualification);
	/**
	 * 获取所有的经营范围<br/>
	 * @author zhangxy 24/05/2016
	 * @param domainId
	 * @return
	 */
	//public MemResult<List<BusinessScopeDO>> getAllBusinessScope(int domainId);
	/**
	 * 获取所有的资质<br/>
	 * @author zhangxy 24/05/2016
	 * @param domainId
	 * @return
	 */
	//public MemResult<List<QualificationDO>> getAllQualification(int domainId);
	/**
	 * 获取商家类目与经营范围管理对象集合<br/>
	 * @author zhangxy 24/05/2016
	 * @param domainId
	 * @return
	 */
	public MemResult<List<MerchantCategoryScopeDO>> getMerchantCategoryScope(MerchantCategoryScopeDO merchantCategoryScope,List<Long> idList);
	/**
	 * 获取商家类目与资质关联对象的集合<br/>
	 * @author zhangxy 24/05/2016
	 * @param domainId
	 * @param merchantCategoryId
	 * @return
	 */
	public MemResult<List<CategoryQualificationDO>> getCategoryQualification(CategoryQualificationDO categoryQualificationDO,List<Long> idList);
	
	/**
	 * 获取用户的经营范围
	 * @param domainId
	 * @param ids
     * @return
     */
	MemResult<List<BusinessScopeDO>> getBusinessScopesBySellerId(MerchantScopeDO merchantScope);

	public MemResult<Boolean> submitExamineInfo(ExamineInfoDTO dto);
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
	
	/**
	 * 
	* created by zhangxy
	* @date 2016年5月29日
	* @Title: getMerchantCategory 
	* @Description: 获取商家身份
	* @param @param domainId
	* @param @return    设定文件 
	* @return MemResult<List<MerchantCategoryDO>>    返回类型 
	* @throws
	 */
	public MemResult<List<MerchantCategoryDO>> getMerchantCategory(MerchantCategoryDO merchantCategory);

	//MemResult<MerchantCategoryDO> getMerchantCategory(MerchantCategoryDO merchantCategory);
}
