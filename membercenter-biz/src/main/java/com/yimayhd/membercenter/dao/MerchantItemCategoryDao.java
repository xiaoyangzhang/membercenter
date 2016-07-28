package com.yimayhd.membercenter.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.yimayhd.membercenter.client.domain.merchant.MerchantItemCategoryDO;
import com.yimayhd.membercenter.client.query.MerchantItemQuery;
import com.yimayhd.membercenter.mapper.MerchantItemCategoryDOMapper;

public class MerchantItemCategoryDao {

	private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){

		@Override
		protected Integer initialValue() {
			return 0;
		}
		
	};
	
	@Autowired
	private MerchantItemCategoryDOMapper merchantItemCategoryDOMapper;
	@Autowired
	private TransactionTemplate transactionTemplate ;
	
	public List<MerchantItemCategoryDO> selectMerchantItemCategoriesByMerchant(int domainId, long sellerId) {
		return merchantItemCategoryDOMapper.selectByMerchant(domainId, sellerId);
	}
	@Deprecated
	public List<MerchantItemCategoryDO> selectByCategoryIdAndSellerId(int domain, long categoryId, long sellerId) {
		return merchantItemCategoryDOMapper.selectByCategoryIdAndSellerId(domain,categoryId,sellerId);
	}
	public MerchantItemCategoryDO selectObjByCategoryIdAndSellerId(int domain, long categoryId, long sellerId) {
		return merchantItemCategoryDOMapper.selectObjByCategoryIdAndSellerId(domain,categoryId,sellerId);
	}
	public List<MerchantItemCategoryDO> selectMerchantItemCategory(MerchantItemQuery queryDTO) {
		return merchantItemCategoryDOMapper.selectMerchantItemCategory(queryDTO);
	}
	public boolean saveMerchanItemCategories(final List<MerchantItemCategoryDO> merchantItemCategoryDOs) {
		return  transactionTemplate.execute(new TransactionCallback<Boolean>() {

			@Override
			public Boolean doInTransaction(TransactionStatus transactionStatus) {

				for(MerchantItemCategoryDO merchantItemCategoryDO : merchantItemCategoryDOs) {
					 merchantItemCategoryDO.setGmtCreated(new Date());
                     merchantItemCategoryDO.setGmtModified(new Date());
					int count = merchantItemCategoryDOMapper.insert(merchantItemCategoryDO);
					if(count != 1) {
						transactionStatus.setRollbackOnly();
						return false;
					}
				}
				return true;
			}
		});
	}
	public boolean updateMerchantItemCategory(final List<MerchantItemCategoryDO> merchantItemCategoryDOs) {
		return  transactionTemplate.execute(new TransactionCallback<Boolean>() {

			@Override
			public Boolean doInTransaction(TransactionStatus transactionStatus) {

		
		for(MerchantItemCategoryDO merchantItemCategoryDO : merchantItemCategoryDOs) {
            merchantItemCategoryDO.setGmtModified(new Date());
			int count = merchantItemCategoryDOMapper.updateByPrimaryKeySelective(merchantItemCategoryDO);
			if(count != 1) {
				return false;
			}
		}
		return true;
	}
	});
}
}
