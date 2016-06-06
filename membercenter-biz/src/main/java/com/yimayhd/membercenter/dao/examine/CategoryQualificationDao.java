package com.yimayhd.membercenter.dao.examine;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.membercenter.client.domain.merchant.CategoryQualificationDO;
import com.yimayhd.membercenter.client.query.QualificationQueryDTO;
import com.yimayhd.membercenter.mapper.CategoryQualificationDOMapper;

/**
 * 
* @ClassName: CategoryQualificationDao
* @Description: 商家身份与资质的关联关系
* @author zhangxy
* @date 2016年5月26日 上午11:18:28
*
 */
public class CategoryQualificationDao {
 
	@Autowired
	private CategoryQualificationDOMapper categoryQualificationDOMapper;
	//public List<CategoryQualificationDO> getCATGQualificationByMerchantCATGId(long merchantCategoryId,int domainId) {
//		if (merchantCategoryId <=0 || domainId <= 0) {
//			return null;
//		}
	//	List<CategoryQualificationDO> qualifications = categoryQualificationDOMapper.getQualificationIdsByMerchantCategoryId(merchantCategoryId, domainId);
//		if (qualifications == null) {
//			return null;
//		}
//		return qualifications;
//	}
	
	public List<CategoryQualificationDO> getCategoryQualification(CategoryQualificationDO categoryQualification) {
		return categoryQualificationDOMapper.getCategoryQualification(categoryQualification,categoryQualification.getScopeIdsList());
	}
}
