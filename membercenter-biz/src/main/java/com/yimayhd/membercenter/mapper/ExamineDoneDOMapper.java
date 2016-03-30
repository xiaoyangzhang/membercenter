package com.yimayhd.membercenter.mapper;

import com.yimayhd.membercenter.client.domain.examine.ExamineDO;

public interface ExamineDoneDOMapper {

    /**
     * 
     * 功能描述: <br>
     * 〈保存〉
     *
     * @param record
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    int insert(ExamineDO record);

    /**
     * 
     * 功能描述: <br>
     * 〈查询〉
     *
     * @param record
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    ExamineDO selectBySellerId(ExamineDO record);

    /**
     * 
     * 功能描述: <br>
     * 〈更新〉
     *
     * @param record
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    int updateByPrimaryKey(ExamineDO record);
}