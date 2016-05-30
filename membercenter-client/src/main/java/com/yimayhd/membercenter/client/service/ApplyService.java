package com.yimayhd.membercenter.client.service;

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
	public MemResult<List<MerchantScopeDO>> getMerchantScopeBySellerId(long sellerId,int domainId);
	/**
	 * 获取用户与资质的关联对象集合<br/>
	 * @author zhangxy 24/05/2016
	 * @param sellerId
	 * @param domainId
	 * @return
	 */
	public MemResult<List<MerchantQualificationDO>> getMerchantQualificationBySellerId(long sellerId,int domainId);
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
	 * 
	* created by zhangxy
	* @date 2016年5月25日
	* @Title: submitExamineInfo 
	* @Description: 保存入驻审核信息
	* @param @param dto
	* @param @return    设定文件 
	* @return MemResult<Boolean>    返回类型 
	* @throws
	 */
	public MemResult<Boolean> submitExamineInfo(ExamineInfoDTO dto,MerchantQualificationDO mqDO,MerchantScopeDO msDO);

	/**
	 * 根据id集合获取经营范围
	 * @param domainId
	 * @param ids
     * @return
     */
	MemResult<List<BusinessScopeDO>> getBusinessScopesByIds(int domainId, long[] ids);

	/**
	 * 根据id集合获取对应的商家身份
	 * @param domainId
	 * @param sellerId
     * @return
     */
	MemResult<List<MerchantCategoryDO>> getMerchantCategoriesBySellerId(int domainId, long sellerId);
}
