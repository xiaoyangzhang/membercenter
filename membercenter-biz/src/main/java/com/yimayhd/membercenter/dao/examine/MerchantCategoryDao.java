package com.yimayhd.membercenter.dao.examine;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.membercenter.client.domain.merchant.MerchantCategoryDO;
import com.yimayhd.membercenter.client.query.MerchantCategoryQueryDTO;
import com.yimayhd.membercenter.mapper.MerchantCategoryDOMapper;

/**
 * 
* @ClassName: MerchantCategoryDao
* @Description: 商家身份
* @author zhangxy
* @date 2016年5月26日 上午11:10:07
*
 */
public class MerchantCategoryDao {
	@Autowired
	private MerchantCategoryDOMapper merchantCategoryDOMapper;

	public List<MerchantCategoryDO> getMerchantCategory(MerchantCategoryDO queryDTO) {
		return merchantCategoryDOMapper.getMerchantCategory(queryDTO);
	}
}
