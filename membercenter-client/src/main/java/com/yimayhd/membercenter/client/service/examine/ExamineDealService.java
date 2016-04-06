/*
 * FileName: ExaminDealService.java
 * Author:   liubb
 * Date:     2016年3月24日 下午5:13:11
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimayhd.membercenter.client.service.examine;

import com.yimayhd.membercenter.client.dto.ExamineDealDTO;
import com.yimayhd.membercenter.client.dto.ExamineInfoDTO;
import com.yimayhd.membercenter.client.dto.ExamineResultDTO;
import com.yimayhd.membercenter.client.dto.ExamineSubmitDTO;
import com.yimayhd.membercenter.client.query.InfoQueryDTO;
import com.yimayhd.membercenter.client.query.examine.ExaminePageQueryDTO;
import com.yimayhd.membercenter.client.result.MemPageResult;
import com.yimayhd.membercenter.client.result.MemResult;

/**
 * 〈一句话功能简述〉<br> 
 * 〈审核信息service〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface ExamineDealService {
    
    /**
     * 
     * 功能描述: <br>
     * 〈提交审核基本信息〉
     *
     * @param examineSubmitDTO
     * @return 
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    MemResult<Boolean> submitMerchantExamineInfo(ExamineSubmitDTO examineSubmitDTO);
    
    /**
     * 
     * 功能描述: <br>
     * 〈根据userId获取未审核通过申请信息〉
     *
     * @param examineQueryDTO
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    MemResult<ExamineInfoDTO> queryMerchantExamineInfoById(InfoQueryDTO examineQueryDTO);
    
    /**
     * 
     * 功能描述: <br>
     * 〈店铺审核分页查询〉
     *
     * @param examinQueryDTO
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    MemPageResult<ExamineInfoDTO> queryMerchantExamineByPage(ExaminePageQueryDTO examinQueryDTO);
    
    
    /**
     * 
     * 功能描述: <br>
     * 〈审核结果表〉
     *
     * @param examineDealDTO
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    MemResult<Boolean> dealExamineInfo(ExamineDealDTO examineDealDTO);
    
    /**
     * 
     * 功能描述: <br>
     * 〈查询审核结果〉
     *
     * @param examineQueryDTO
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    MemResult<ExamineResultDTO> queryExamineDealResult(InfoQueryDTO examineQueryDTO);

}
