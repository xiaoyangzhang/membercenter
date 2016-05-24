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
    * 根据商家类目id查询需要上传的资质id集合
    * @param merchantCategoryId 商家类目
    * @param domainId
    * @return
    */
    
   // public List<CategoryQualificationDO> getQualificationIdsByMerchantCategoryId(@Param("categoryId")long merchantCategoryId,@Param("domainId")int domainId);
}