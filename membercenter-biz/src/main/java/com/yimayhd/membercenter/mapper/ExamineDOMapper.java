package com.yimayhd.membercenter.mapper;

import java.util.List;

import com.yimayhd.membercenter.client.domain.examine.ExamineDO;
import com.yimayhd.membercenter.client.query.examine.ExaminePageQueryDTO;

public interface ExamineDOMapper {

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
     * 〈根据ID查询〉
     *
     * @param id
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    ExamineDO selectById(ExamineDO record);

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
    
    /**
     * 
     * 功能描述: <br>
     * 〈分页查询〉
     *
     * @param examinQueryDTO
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    List<ExamineDO> queryMerchantExaminByPage(ExaminePageQueryDTO examinQueryDTO);
    /**
     * 
     * 功能描述: <br>
     * 〈分页查询获取总数〉
     *
     * @param examinQueryDTO
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    int queryMerchantExaminCount(ExaminePageQueryDTO examinQueryDTO);
}