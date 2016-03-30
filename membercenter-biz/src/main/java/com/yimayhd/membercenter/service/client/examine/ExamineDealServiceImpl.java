/*
 * FileName: ExaminDealServiceImpl.java
 * Author:   liubb
 * Date:     2016年3月24日 下午8:08:01
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimayhd.membercenter.service.client.examine;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.domain.examine.ExamineDO;
import com.yimayhd.membercenter.client.dto.ExamineDealDTO;
import com.yimayhd.membercenter.client.dto.ExamineInfoDTO;
import com.yimayhd.membercenter.client.query.InfoQueryDTO;
import com.yimayhd.membercenter.client.query.examine.ExaminePageQueryDTO;
import com.yimayhd.membercenter.client.result.MemPageResult;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.service.examine.ExamineDealService;
import com.yimayhd.membercenter.converter.ExamineConverter;
import com.yimayhd.membercenter.manager.talent.TalentExamineManager;
import com.yimayhd.membercenter.util.ParmCheckUtil;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class ExamineDealServiceImpl implements ExamineDealService {

    private static final Logger logger = LoggerFactory.getLogger(ExamineDealServiceImpl.class);
    
    @Autowired
    TalentExamineManager talentExamineManager;
    
    /* (non-Javadoc)
     * @see com.yimayhd.membercenter.client.service.examin.ExaminDealService#submitMerchantExaminInfo(com.yimayhd.membercenter.client.dto.ExaminDTO)
     */
    @Override
    public MemResult<Boolean> submitMerchantExamineInfo(ExamineInfoDTO examinDTO) {
        MemResult<Boolean> result = new MemResult<Boolean>();
        if(ParmCheckUtil.checkExamineDTO(examinDTO)){
            result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
            logger.info("submitMerchantExaminInfo par:{} is error", JSONObject.toJSONString(examinDTO));
            return result;
        }
        //数据转换
        ExamineDO examinDO = ExamineConverter.examinDTOToDO(examinDTO);
        result = talentExamineManager.submitMerchantExamineInfo(examinDO);
       logger.info("submitMerchantExaminInfo par:{} submit return:{}", JSONObject.toJSONString(examinDTO), JSONObject.toJSONString(result));
        return result;
    }

    /* (non-Javadoc)
     * @see com.yimayhd.membercenter.client.service.examin.ExaminDealService#queryMerchantExaminInfo()
     */
    @Override
    public MemResult<ExamineInfoDTO> queryMerchantExamineInfoById(InfoQueryDTO examineQueryDTO) {
        MemResult<ExamineInfoDTO> result = new MemResult<ExamineInfoDTO>();
        if(ParmCheckUtil.checkQueryDTO(examineQueryDTO)){
            result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
            logger.info("queryMerchantExaminInfo par:{} is error", JSONObject.toJSONString(examineQueryDTO));
            return result;
        }
        //数据转换
        ExamineDO examineDO = ExamineConverter.examineQueryToDO(examineQueryDTO);
        MemResult<ExamineDO> queryResult = talentExamineManager.queryMerchantExamineInfoById(examineDO);
        if(queryResult.isSuccess()){
            ExamineInfoDTO examinDTO = ExamineConverter.examineDOToDTO(queryResult.getValue());
            result.setValue(examinDTO);
            logger.info("queryMerchantExaminInfo par:{} return success", JSONObject.toJSONString(examineQueryDTO));
        }else{
            result.setReturnCode(queryResult.getReturnCode());
            logger.info("queryMerchantExaminInfo par:{} return error:{}", JSONObject.toJSONString(examineQueryDTO), queryResult.getErrorMsg());
        }
        return result;
    }

    /* (non-Javadoc)
     * @see com.yimayhd.membercenter.client.service.examin.ExaminDealService#queryMerchantExaminByPage(com.yimayhd.membercenter.client.query.examinQueryDTO)
     */
    @Override
    public MemPageResult<ExamineInfoDTO> queryMerchantExamineByPage(ExaminePageQueryDTO examinQueryDTO) {
        MemPageResult<ExamineInfoDTO> result = new MemPageResult<ExamineInfoDTO>();
        if(0 >= examinQueryDTO.getDomainId()){
            result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
            logger.info("queryMerchantExaminByPage domainId:{} is error", examinQueryDTO.getDomainId());
            return result;
        }
        MemPageResult<ExamineDO> pageResult = talentExamineManager.queryMerchantExamineByPage(examinQueryDTO);
        if(pageResult.isSuccess()){
            List<ExamineInfoDTO> examineInfoDTOs =  new ArrayList<ExamineInfoDTO>();
            for (ExamineDO examineDO : pageResult.getList()) { 
                //数据转换
                examineInfoDTOs.add(ExamineConverter.examineDOToDTO(examineDO));
            }
            result.setList(examineInfoDTOs);
            logger.info("queryMerchantExaminByPage par:{} return ok", JSONObject.toJSONString(examinQueryDTO));
        }else{
            result.setReturnCode(pageResult.getReturnCode());
            logger.info("queryMerchantExaminByPage par:{} is error return:{}", JSONObject.toJSONString(examinQueryDTO), JSONObject.toJSONString(pageResult));
        }
        return result;
    }

    /* (non-Javadoc)
     * @see com.yimayhd.membercenter.client.service.examin.ExaminDealService#queryMerchantExaminByPage(com.yimayhd.membercenter.client.examineDealDTO)
     */
    @Override
    public MemResult<Boolean> examineInfoIsOk(ExamineDealDTO examineDealDTO) {
        MemResult<Boolean> result = new MemResult<Boolean>();
        if(ParmCheckUtil.checkExamineDealDTO(examineDealDTO)){
            result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
            logger.info("examinInfoIsOk par:{} is error", JSONObject.toJSONString(examineDealDTO));
            return result;
        }
        //数据转化
        ExamineDO examineDO = ExamineConverter.examineDealToDO(examineDealDTO);
        result = talentExamineManager.updateMerchantExamineById(examineDO);
        logger.info("examinInfoIsOk par:{} return:{}", JSONObject.toJSONString(examineDealDTO), JSONObject.toJSONString(result));
        return result;
    }
    
    @Override
    public MemResult<String> queryExamineDealResult(InfoQueryDTO examineQueryDTO) {
        MemResult<String> result = new MemResult<String>();
        if(ParmCheckUtil.checkQueryDTO(examineQueryDTO)){
            result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
            logger.info("queryMerchantExaminInfo par:{} is error", JSONObject.toJSONString(examineQueryDTO));
            return result;
        }
        //数据转换
        ExamineDO examineDO = ExamineConverter.examineQueryToDO(examineQueryDTO);
        MemResult<ExamineDO> queryResult = talentExamineManager.queryMerchantExamineInfoById(examineDO);
        if(queryResult.isSuccess()){
            result.setValue(queryResult.getValue().getExamineMes());
            logger.info("queryExamineDealResult par:{} return success", JSONObject.toJSONString(examineQueryDTO));
        }else{
            result.setReturnCode(queryResult.getReturnCode());
            logger.info("queryExamineDealResult par:{} return error:{}", JSONObject.toJSONString(examineQueryDTO), queryResult.getErrorMsg());
        }
        return result;
    }
}
