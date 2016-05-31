package com.yimayhd.membercenter.dao.examine;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.membercenter.client.domain.MerchantScopeDO;
import com.yimayhd.membercenter.client.domain.merchant.MerchantQualificationDO;
import com.yimayhd.membercenter.mapper.MerchantQualificationDOMapper;
import com.yimayhd.membercenter.mapper.MerchantScopeDOMapper;

/**
 * 
* @ClassName: MerchantQualificationDao
* @Description: 商家与资质的关联类
* @author zhangxy
* @date 2016年5月26日 上午11:22:31
*
 */
public class MerchantQualificationDao {

	@Autowired
	private MerchantQualificationDOMapper merchantQualificationDOMapper;
	
	public MerchantQualificationDO update(MerchantQualificationDO record) {
		if (null == record) {
			return null;
		}
		record.setGmtModified(new Date());
		int result = merchantQualificationDOMapper.updateByPrimaryKey(record);
		if (result == 1) {
			return record;
		}
		return null;
	}
	
	public MerchantQualificationDO insert(MerchantQualificationDO record) {
		if (null == record) {
			return null;
		}
		record.setGmtCreated(new Date());
		record.setGmtModified(new Date());
		int result = merchantQualificationDOMapper.insert(record);
		if (result == 1) {
			return record;
		}
		return null;
	}

	

	
	
	public List<MerchantQualificationDO> getMerchantQualification(MerchantQualificationDO merchantQualification) {
		return merchantQualificationDOMapper.getMerchantQualification(merchantQualification);
	}
}
