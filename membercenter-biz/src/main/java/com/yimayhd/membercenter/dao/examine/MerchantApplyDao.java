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
* @ClassName: MerchantApplyDao
* @Description: 商家入驻审核操作
* @author zhangxy
* @date 2016年5月26日 上午11:22:31
*
 */
public class MerchantApplyDao {

	@Autowired
	private MerchantQualificationDOMapper merchantQualificationDOMapper;
	@Autowired
	private MerchantScopeDOMapper merchantScopeDOMapper;
//	@Autowired
//	private IDPool idPool;
//	public MerchantCategoryScopeDO insert(MerchantCategoryScopeDO record) {
//		if (null == record) {
//			return null;
//		}
//		record.setGmtCreated(new Date());
//		record.setGmtModified(new Date());
//		int result = merchantCategoryScopeDOMapper.insert(record);
//		if (result == 1) {
//			return record;
//		}
//		return null;
//	}
//	public MerchantCategoryScopeDO update(MerchantCategoryScopeDO record) {
//		if (null == record) {
//			return null;
//		}
//		record.setGmtModified(new Date());
//		int result = merchantCategoryScopeDOMapper.updateByPrimaryKey(record);
//		if (result == 1) {
//			return record;
//		}
//		return null;
//	}
//	public MerchantQualificationDO update(MerchantQualificationDO record) {
//		if (null == record) {
//			return null;
//		}
//		record.setGmtModified(new Date());
//		int result = merchantQualificationDOMapper.updateByPrimaryKey(record);
//		if (result == 1) {
//			return record;
//		}
//		return null;
//	}
//	
//	public MerchantQualificationDO insert(MerchantQualificationDO record) {
//		if (null == record) {
//			return null;
//		}
//		record.setGmtCreated(new Date());
//		record.setGmtModified(new Date());
//		int result = merchantQualificationDOMapper.insert(record);
//		if (result == 1) {
//			return record;
//		}
//		return null;
//	}
//	public CategoryQualificationDO update(CategoryQualificationDO record) {
//		if (null == record) {
//			return null;
//		}
//		record.setGmtModified(new Date());
//		int result = categoryQualificationDOMapper.updateByPrimaryKey(record);
//		if (result == 1) {
//			return record;
//		}
//		return null;
//	}
//	
//	public CategoryQualificationDO insert(CategoryQualificationDO record) {
//		if (null == record) {
//			return null;
//		}
//		record.setGmtCreated(new Date());
//		record.setGmtModified(new Date());
//		int result = categoryQualificationDOMapper.insert(record);
//		if (result == 1) {
//			return record;
//		}
//		return null;
//	}
//	public MerchantScopeDO update(MerchantScopeDO record) {
//		if (null == record) {
//			return null;
//		}
//		record.setGmtModified(new Date());
//		int result = merchantScopeDOMapper.updateByPrimaryKey(record);
//		if (result == 1) {
//			return record;
//		}
//		return null;
//	}
//	
//	public MerchantScopeDO insert(MerchantScopeDO record) {
//		if (null == record) {
//			return null;
//		}
//		record.setGmtCreated(new Date());
//		record.setGmtModified(new Date());
//		int result = merchantScopeDOMapper.insert(record);
//		if (result == 1) {
//			return record;
//		}
//		return null;
//	}
//	public MerchantScopeDO update(MerchantScopeDO record) {
//		if (null == record) {
//			return null;
//		}
//		record.setGmtModified(new Date());
//		int result = merchantScopeDOMapper.updateByPrimaryKey(record);
//		if (result == 1) {
//			return record;
//		}
//		return null;
//	}
//	
//	public MerchantScopeDO insert(MerchantScopeDO record) {
//		if (null == record) {
//			return null;
//		}
//		record.setGmtCreated(new Date());
//		record.setGmtModified(new Date());
//		int result = merchantScopeDOMapper.insert(record);
//		if (result == 1) {
//			return record;
//		}
//		return null;
//	}
	
	/*public MemResultSupport createOrUpdate(final CategoryQualificationDO categoryQualificationDO,final MerchantCategoryScopeDO merchantCategoryScopeDO,final MerchantQualificationDO merchantQualificationDO,final MerchantScopeDO merchantScopeDO) {
		MemResultSupport result = new MemResultSupport();
		if ((null == merchantCategoryScopeDO) || (null == merchantQualificationDO)) {
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return result;
		}
		boolean isNewMerchantCatgScope = false;
		if (merchantCategoryScopeDO.getId() <= 0) {
			long id = idPool.getNewId();
			merchantCategoryScopeDO.setId(id);
			isNewMerchantCatgScope = true;
		}
		boolean isNewMerchantQualification = false;
		if (merchantQualificationDO.getId() <= 0) {
			long id = idPool.getNewId();
			merchantQualificationDO.setId(id);
			isNewMerchantQualification = true;
		}
		boolean isNewCatgQualification = false;
		if (categoryQualificationDO.getId() <= 0) {
			long id = idPool.getNewId();
			categoryQualificationDO.setId(id);
			isNewCatgQualification = true;
		}
		boolean isNewMerchantScope = false;
		if (merchantScopeDO.getId() <= 0) {
			long id = idPool.getNewId();
			merchantScopeDO.setId(id);
			isNewMerchantScope = true;
		}
		
		return null;
		
	}
	*/
	
	
	
	
	

	
//	public List<MerchantScopeDO> getMerchantScopeBySellerId(long sellerId,int domainId) {
//		if (sellerId <=0 || domainId <= 0) {
//			return null;
//		}
//		List<MerchantScopeDO> merchantScopes = merchantScopeDOMapper.getBusinessScopeBySellerId(sellerId, domainId);
//		if (merchantScopes == null) {
//			return null;
//		}
//		return merchantScopeDOMapper.getBusinessScopeBySellerId(sellerId, domainId);
//	}
//	
//	public List<MerchantQualificationDO> getMerchantQualificationBySellerId(long sellerId,int domainId) {
//		return merchantQualificationDOMapper.getMerchantQualificationBySellerId(sellerId, domainId);
//	}
}
