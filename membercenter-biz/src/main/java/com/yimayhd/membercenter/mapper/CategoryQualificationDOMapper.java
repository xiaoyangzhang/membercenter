package com.yimayhd.membercenter.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yimayhd.membercenter.client.domain.merchant.CategoryQualificationDO;

public interface CategoryQualificationDOMapper {
    int deleteByPrimaryKey(long id);

    int insert(CategoryQualificationDO record);

    int insertSelective(CategoryQualificationDO record);

    CategoryQualificationDO selectByPrimaryKey(long id);

    int updateByPrimaryKeySelective(CategoryQualificationDO record);

    int updateByPrimaryKey(CategoryQualificationDO record);
   /**
    * 获取商家身份与资质的关联对象集合
    * @param categoryQualificationDO 
    * @param domainId
    * @return
    */
    
  //  public List<CategoryQualificationDO> getQualificationIdsByMerchantCategoryId(@Param("categoryId")long merchantCategoryId,@Param("domainId")int domainId);
    
    public List<CategoryQualificationDO> getCategoryQualification(@Param("category")CategoryQualificationDO categoryQualificationDO,@Param("scopeIdsList")List<Long> idList);
}