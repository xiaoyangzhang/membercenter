package com.yimayhd.membercenter.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.membercenter.client.domain.merchant.MerchantItemCategoryDO;
import com.yimayhd.membercenter.mapper.MerchantItemCategoryDOMapper;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

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

	public MerchantItemCategoryDO selectByCategoryIdAndSellerId(int domain, long categoryId, long sellerId) {
		return merchantItemCategoryDOMapper.selectByCategoryIdAndSellerId(domain,categoryId,sellerId);
	}
	
	public boolean saveMerchanItemCategories(final List<MerchantItemCategoryDO> merchantItemCategoryDOs) {
		return  transactionTemplate.execute(new TransactionCallback<Boolean>() {

			@Override
			public Boolean doInTransaction(TransactionStatus transactionStatus) {

				for(MerchantItemCategoryDO merchantItemCategoryDO : merchantItemCategoryDOs) {
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
}
