package com.yimayhd.membercenter.dao.examine;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.membercenter.client.domain.MerchantScopeDO;
import com.yimayhd.membercenter.client.domain.merchant.BusinessScopeDO;
import com.yimayhd.membercenter.client.domain.merchant.MerchantQualificationDO;
import com.yimayhd.membercenter.client.query.BusinessScopeQueryDTO;
import com.yimayhd.membercenter.mapper.MerchantQualificationDOMapper;
import com.yimayhd.membercenter.mapper.MerchantScopeDOMapper;

/**
 * 
* @ClassName: MerchantApplyDao
* @Description: 商家入驻审核操作
* @author zhangxy
* @date 2016年5月26日 上午11:22:31
*
 */
public class MerchantScopeDao {

	@Autowired
	private MerchantScopeDOMapper merchantScopeDOMapper;

	public MerchantScopeDO update(MerchantScopeDO record) {
		if (null == record) {
			return null;
		}
		record.setGmtModified(new Date());
		int result = merchantScopeDOMapper.updateByPrimaryKey(record);
		if (result == 1) {
			return record;
		}
		return null;
	}
	
	public MerchantScopeDO insert(MerchantScopeDO record) {
		if (null == record) {
			return null;
		}
		record.setGmtCreated(new Date());
		record.setGmtModified(new Date());
		int result = merchantScopeDOMapper.insert(record);
		if (result == 1) {
			return record;
		}
		return null;
	}


	
	public List<MerchantScopeDO> getMerchantScope(MerchantScopeDO businessScopeQueryDTO) {
//		if (sellerId <=0 || domainId <= 0) {
//			return null;
//		}
//		List<MerchantScopeDO> merchantScopes = merchantScopeDOMapper.getBusinessScopeBySellerId(sellerId, domainId);
//		if (merchantScopes == null) {
//			return null;
//		}
		
		return merchantScopeDOMapper.getMerchantScope(businessScopeQueryDTO);
	}
	
	public int updateStatusBatch(List<MerchantScopeDO> scopeDOs) {
		return merchantScopeDOMapper.updateStatusBatch(scopeDOs);
		
	}
}
