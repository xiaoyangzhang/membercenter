package com.yimayhd.membercenter.client.service;

import java.util.List;

import com.yimayhd.membercenter.client.domain.MerchantScopeDO;
import com.yimayhd.membercenter.client.domain.merchant.BusinessScopeDO;
import com.yimayhd.membercenter.client.domain.merchant.MerchantCategoryScopeDO;
import com.yimayhd.membercenter.client.query.BusinessScopeQueryDTO;
import com.yimayhd.membercenter.client.query.QualificationQueryDTO;
import com.yimayhd.membercenter.client.result.MemResult;

/**
 * Created by hanlei on 2016/5/24.  
 */
public interface BusinessScopeService {
	/**
	 * 
	* created by zhangxy
	* @date 2016年6月4日
	* @Title: findBusinessScopesByScope 
	* @Description: 查询经营范围
	* @param  queryDTO
	* @return MemResult<List<BusinessScopeDO>>    返回类型 
	* @throws
	 */
	MemResult<List<BusinessScopeDO>> findBusinessScopesByScope(BusinessScopeQueryDTO queryDTO);
	/**
	 * 
	* created by zhangxy
	* @date 2016年6月4日
	* @Title: getMerchantCategoryScope 
	* @Description: 查询商家身份与经营范围的关联对象集合
	* @param  queryDTO
	* @return MemResult<List<MerchantCategoryScopeDO>>    返回类型 
	* @throws
	 */
	public MemResult<List<MerchantCategoryScopeDO>> getMerchantCategoryScope(BusinessScopeQueryDTO queryDTO);
	/**
	 * 
	* created by zhangxy
	* @date 2016年6月4日
	* @Title: getMerchantScope 
	* @Description: 获取商家与经营范围的关联关系
	* @param @param queryDTO
	* @param @return    设定文件 
	* @return MemResult<List<MerchantScopeDO>>    返回类型 
	* @throws
	 */
	public MemResult<List<MerchantScopeDO>> getMerchantScope(BusinessScopeQueryDTO queryDTO);
	/**
	 * 更新商家与经营范围关联关系的可用性
	* created by zhangxy
	* @date 2016年6月5日
	* @Title: updateMerchantScopeStatus 
	* @Description: TODO
	* @param @param queryDTO
	* @param @return    设定文件 
	* @return MemResult<Boolean>    返回类型 
	* @throws
	 */
	public MemResult<Boolean> updateMerchantScopeStatus(BusinessScopeQueryDTO queryDTO);
	public MemResult<Integer> updateStatusBatch(List<BusinessScopeQueryDTO> queryDTO) ;

}
