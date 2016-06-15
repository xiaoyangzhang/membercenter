package com.yimayhd.membercenter.mapper;

import com.yimayhd.membercenter.client.domain.examine.ExamineDetailDO;

public interface ExamineDetailDOMapper {

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
    int insert(ExamineDetailDO record);

    /**
     * 
     * 功能描述: <br>
     * 〈查询〉
     *
     * @param examineDetailDO
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    ExamineDetailDO selectBySellerId(ExamineDetailDO examineDetailDO);
}