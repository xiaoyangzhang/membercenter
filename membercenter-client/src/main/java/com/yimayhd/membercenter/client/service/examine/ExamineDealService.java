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
     * 〈提交审核达人基本信息〉
     *
     * @param examineInfoDTO
     * @return 
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    MemResult<Boolean> submitMerchantExamineInfo(ExamineInfoDTO examineInfoDTO);
    /**
     * 
     * 功能描述: <br>
     * 〈提交审核店铺基本信息〉
     *
     * @param examineInfoDTO
     * @return 
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    MemResult<Boolean> submitExamineInfo(ExamineInfoDTO examineInfoDTO);
    
    
    /**
     * 
     * 功能描述: <br>
     * 〈修改审核信息状态为审核中〉
     *
     * @param examineQueryDTO
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    MemResult<Boolean> changeExamineStatusIntoIng(InfoQueryDTO examineQueryDTO);
    
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
    MemResult<ExamineInfoDTO> queryMerchantExamineInfoBySellerId(InfoQueryDTO examineQueryDTO);
    
    /**
     * 
     * 功能描述: <br>
     * 〈根据Id获取未审核通过申请信息〉
     *
     * @param id
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    MemResult<ExamineInfoDTO> queryMerchantExamineInfoById(long id);
    
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
     * 〈商户审核驳回、达人审核通过or驳回〉
     *
     * @param examineDealDTO
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    MemResult<Boolean> refuseMerchantOrAuditTalent(ExamineDealDTO examineDealDTO);
    
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
    /**
     * 
    * created by zhangxy
    * @date 2016年6月7日
    * @Title: checkMerchantNameIsExist 
    * @Description: 验证店铺名称是否存在
    * @param @param infoQueryDTO
    * @param @return    设定文件 
    * @return MemResult<Boolean>    返回类型 
    * @throws
     */
    public MemResult<Boolean> checkMerchantNameIsExist(ExamineInfoDTO examineInfoDTO);
}
