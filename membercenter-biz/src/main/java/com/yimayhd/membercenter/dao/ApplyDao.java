package com.yimayhd.membercenter.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.domain.merchant.MerchantCategoryScopeDO;
import com.yimayhd.membercenter.client.domain.merchant.MerchantQualificationDO;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.result.MemResultSupport;
import com.yimayhd.membercenter.mapper.MerchantCategoryScopeDOMapper;
import com.yimayhd.membercenter.mapper.MerchantQualificationDOMapper;

public class ApplyDao {
	@Autowired
	private MerchantCategoryScopeDOMapper merchantCategoryScopeDOMapper;
	@Autowired
	private MerchantQualificationDOMapper merchantQualificationDOMapper;
	public MerchantCategoryScopeDO insert(MerchantCategoryScopeDO record) {
		if (null == record) {
			return null;
		}
		record.setGmtCreated(new Date());
		record.setGmtModified(new Date());
		int result = merchantCategoryScopeDOMapper.insert(record);
		if (result == 1) {
			return record;
		}
		return null;
	}
	public MerchantCategoryScopeDO update(MerchantCategoryScopeDO record) {
		if (null == record) {
			return null;
		}
		int result = merchantCategoryScopeDOMapper.updateByPrimaryKey(record);
		if (result == 1) {
			return record;
		}
		return null;
	}
	public MerchantQualificationDO update(MerchantQualificationDO record) {
		if (null == record) {
			return null;
		}
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
		int result = merchantQualificationDOMapper.insert(record);
		if (result == 1) {
			return record;
		}
		return null;
	}
	
	public MemResultSupport createOrUpdate(final MerchantCategoryScopeDO merchantCategoryScopeDO,final MerchantQualificationDO merchantQualificationDO) {
		MemResultSupport result = new MemResultSupport();
		if ((null == merchantCategoryScopeDO) || (null == merchantQualificationDO)) {
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return result;
		}
		
		return null;
		
	}
	
}
