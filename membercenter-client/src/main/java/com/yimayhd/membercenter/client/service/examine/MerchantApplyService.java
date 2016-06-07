package com.yimayhd.membercenter.client.service.examine;

import com.yimayhd.membercenter.client.domain.merchant.*;
import com.yimayhd.membercenter.client.dto.ExamineInfoDTO;
import com.yimayhd.membercenter.client.query.MerchantCategoryQueryDTO;
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

public interface MerchantApplyService {
	
	/**
	 * 
	* created by zhangxy
	* @date 2016年6月4日
	* @Title: submitExamineInfo 
	* @Description: 保存入驻信息，商家和经营范围关联信息
	* @param @param dto
	* @param @return    设定文件 
	* @return MemResult<Boolean>    返回类型 
	* @throws
	 */
	public MemResult<Boolean> submitExamineInfo(ExamineInfoDTO dto);
	/**
	 * 
	* created by zhangxy
	* @date 2016年6月5日
	* @Title: getMerchantCategory 
	* @Description: 查询身份
	* @param @param merchantCategoryQueryDTO
	* @param @return    设定文件 
	* @return MemResult<List<MerchantCategoryDO>>    返回类型 
	* @throws
	 */
	public MemResult<List<MerchantCategoryDO>> getMerchantCategory(MerchantCategoryQueryDTO merchantCategoryQueryDTO);
}
