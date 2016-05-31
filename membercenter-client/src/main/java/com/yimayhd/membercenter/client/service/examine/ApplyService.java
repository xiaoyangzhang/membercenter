package com.yimayhd.membercenter.client.service.examine;

import java.util.List;
import com.yimayhd.membercenter.client.domain.MerchantScopeDO;
/**
 * 〈一句话功能简述〉<br>
 * 〈入驻申请service〉
 *
 * @author zhangxy
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
import com.yimayhd.membercenter.client.domain.merchant.*;
import com.yimayhd.membercenter.client.domain.merchant.BusinessScopeDO;
import com.yimayhd.membercenter.client.domain.merchant.CategoryQualificationDO;
import com.yimayhd.membercenter.client.domain.merchant.MerchantCategoryDO;
import com.yimayhd.membercenter.client.domain.merchant.MerchantCategoryScopeDO;
import com.yimayhd.membercenter.client.domain.merchant.MerchantQualificationDO;
import com.yimayhd.membercenter.client.domain.merchant.QualificationDO;
import com.yimayhd.membercenter.client.dto.ExamineInfoDTO;
import com.yimayhd.membercenter.client.result.MemResult;

public interface ApplyService {
	
	/** 
	 * 获取用户与经营范围的关联对象集合<br/>
	 * @author zhangxy 24/05/2016
	 * @param sellerId
	 * @param domainId
	 * @return
	 */
	public MemResult<List<BusinessScopeDO>> getMerchantScopeBySellerId(long sellerId,int domainId);
	/**
	 * 根据id集合获取商家的资质<br/>
	 * @author zhangxy 24/05/2016
	 * @param sellerId
	 * @param domainId
	 * @return
	 */
	public MemResult<List<QualificationDO>> getQualificationByIds(List<Long> idList,int domainId);
	/**
	 * 根据id集合获取商家的经营范围<br/>
	 * @author zhangxy 24/05/2016
	 * @param sellerId
	 * @param domainId
	 * @return
	 */
	public MemResult<List<BusinessScopeDO>> getBusinessScopeByIds(List<Long> idList,int domainId);
	/**
	 * 获取用户与资质的关联对象集合<br/>
	 * @author zhangxy 24/05/2016
	 * @param sellerId
	 * @param domainId
	 * @return
	 */
	public MemResult<List<QualificationDO>> getMerchantQualificationBySellerId(long sellerId,int domainId);
	/**
	 * 获取所有的经营范围<br/>
	 * @author zhangxy 24/05/2016
	 * @param domainId
	 * @return
	 */
	public MemResult<List<BusinessScopeDO>> getAllBusinessScope(int domainId);
	/**
	 * 获取所有的资质<br/>
	 * @author zhangxy 24/05/2016
	 * @param domainId
	 * @return
	 */
	public MemResult<List<QualificationDO>> getAllQualification(int domainId);
	/**
	 * 根据商家类目id获取商家类目与经营范围管理对象集合<br/>
	 * @author zhangxy 24/05/2016
	 * @param domainId
	 * @return
	 */
	public MemResult<List<MerchantCategoryScopeDO>> getBusinessScopeByMerchantCategoryId(long merchantCategoryId,int domainId);
	/**
	 * 根据商家类目id获取商家类目与资质关联对象的集合<br/>
	 * @author zhangxy 24/05/2016
	 * @param domainId
	 * @param merchantCategoryId
	 * @return
	 */
	public MemResult<List<CategoryQualificationDO>> getQualificationByMerchantCategoryId(long merchantCategoryId,int domainId);
	
	/**
	 * 根据id集合获取经营范围
	 * @param domainId
	 * @param ids
     * @return
     */
	MemResult<List<BusinessScopeDO>> getBusinessScopesByIds(int domainId, List<Long> ids);

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
	* @Title: findBusinessScopesByScope 
	* @Description: 根据id获取经营范围
	* @param @param domainId
	* @param @param scopeIds
	* @param @return    设定文件 
	* @return MemResult<List<BusinessScopeDO>>    返回类型 
	* @throws
	 */
	/**
	 * 
	* created by zhangxy
	* @date 2016年5月29日
	* @Title: getAllMerchantCategory 
	* @Description: 获取商家身份
	* @param @param domainId
	* @param @return    设定文件 
	* @return MemResult<List<MerchantCategoryDO>>    返回类型 
	* @throws
	 */
	public MemResult<List<MerchantCategoryDO>> getAllMerchantCategory(int domainId);

	MemResult<MerchantCategoryDO> getMerchantCategory(int domainId, long merchantCategoryId);
}
