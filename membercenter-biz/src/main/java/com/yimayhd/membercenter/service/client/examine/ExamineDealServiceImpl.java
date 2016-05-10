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

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.domain.examine.ExamineDO;
import com.yimayhd.membercenter.client.dto.ExamineDealDTO;
import com.yimayhd.membercenter.client.dto.ExamineInfoDTO;
import com.yimayhd.membercenter.client.dto.ExamineResultDTO;
import com.yimayhd.membercenter.client.query.InfoQueryDTO;
import com.yimayhd.membercenter.client.query.examine.ExaminePageQueryDTO;
import com.yimayhd.membercenter.client.result.MemPageResult;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.service.examine.ExamineDealService;
import com.yimayhd.membercenter.converter.ExamineConverter;
import com.yimayhd.membercenter.enums.ExamineStatus;
import com.yimayhd.membercenter.manager.talent.TalentBackInfoManager;
import com.yimayhd.membercenter.manager.talent.TalentExamineManager;
import com.yimayhd.membercenter.util.ParmCheckUtil;

/**
 * 〈一句话功能简述〉<br>
 * 〈审核信息实现类〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class ExamineDealServiceImpl implements ExamineDealService {

    private static final Logger logger = LoggerFactory.getLogger(ExamineDealServiceImpl.class);

    @Autowired
    TalentExamineManager talentExamineManager;

    @Autowired
    TalentBackInfoManager talentBackInfoManager;

    /*
     * (non-Javadoc)
     * @see com.yimayhd.membercenter.client.service.examin.ExaminDealService#submitMerchantExaminInfo(com.yimayhd.
     * membercenter.client.dto.ExaminDTO)
     */
    @Override
    public MemResult<Boolean> submitMerchantExamineInfo(ExamineInfoDTO examineInfoDTO) {
        MemResult<Boolean> result = new MemResult<Boolean>();
        long start = System.currentTimeMillis();
        try {
            if (ParmCheckUtil.checkExamineDTO(examineInfoDTO)) {
                result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
                logger.info("submitMerchantExaminInfo par:{} is error", JSONObject.toJSONString(examineInfoDTO));
                return result;
            }
            // 根据银行ID查询银行name
            if (StringUtils.isNotBlank(examineInfoDTO.getFinanceOpenBankId())) {
                MemResult<String> memResult = talentBackInfoManager
                        .queryBankNameById(examineInfoDTO.getFinanceOpenBankId());
                logger.info("queryBankNameById par:{} return: {}", examineInfoDTO.getFinanceOpenBankId(),
                        JSONObject.toJSONString(memResult));
                if (memResult.isSuccess()) {
                    examineInfoDTO.setFinanceOpenBankName(memResult.getValue());
                }
            }
            // 数据转换
            ExamineDO examinDO = ExamineConverter.examinDTOToDO(examineInfoDTO);
            result = talentExamineManager.submitMerchantExamineInfo(examinDO, examineInfoDTO.getMerchantName());
            logger.info("submitMerchantExaminInfo par:{} submit return:{}, cost:{}ms",
                    JSONObject.toJSONString(examineInfoDTO), JSONObject.toJSONString(result),
                    (System.currentTimeMillis() - start));
        } catch (Exception e) {
            logger.error("submitMerchantExaminInfo par:{} error:{}", JSONObject.toJSONString(examineInfoDTO), e);
            result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * @see com.yimayhd.membercenter.client.service.examin.ExaminDealService#queryMerchantExaminInfo()
     */
    @Override
    public MemResult<ExamineInfoDTO> queryMerchantExamineInfoBySellerId(InfoQueryDTO examineQueryDTO) {
        MemResult<ExamineInfoDTO> result = new MemResult<ExamineInfoDTO>();
        long start = System.currentTimeMillis();
        try {
            if (ParmCheckUtil.checkQueryDTO(examineQueryDTO)) {
                result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
                logger.info("queryMerchantExamineInfoBySellerId par:{} is error",
                        JSONObject.toJSONString(examineQueryDTO));
                return result;
            }
            // 数据转换
            ExamineDO examineDO = ExamineConverter.examineQueryToDO(examineQueryDTO);
            MemResult<ExamineDO> queryResult = talentExamineManager.queryMerchantExamineInfoById(examineDO);
            if (queryResult.isSuccess()) {
                ExamineInfoDTO examinDTO = ExamineConverter.examineDOToDTO(queryResult.getValue());
                result.setValue(examinDTO);
                logger.info("queryMerchantExamineInfoBySellerId par:{} return success, costs:{}ms",
                        JSONObject.toJSONString(examineQueryDTO), (System.currentTimeMillis() - start));
            } else {
                result.setReturnCode(queryResult.getReturnCode());
                logger.info("queryMerchantExamineInfoBySellerId par:{} return error:{}",
                        JSONObject.toJSONString(examineQueryDTO), queryResult.getErrorMsg());
            }
        } catch (Exception e) {
            logger.error("queryMerchantExamineInfoBySellerId par:{} error:{}", JSONObject.toJSONString(examineQueryDTO),
                    e);
            result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * @see com.yimayhd.membercenter.client.service.examin.ExaminDealService#queryMerchantExaminByPage(com.yimayhd.
     * membercenter.client.query.examinQueryDTO)
     */
    @Override
    public MemPageResult<ExamineInfoDTO> queryMerchantExamineByPage(ExaminePageQueryDTO examinQueryDTO) {
        MemPageResult<ExamineInfoDTO> result = new MemPageResult<ExamineInfoDTO>();
        long start = System.currentTimeMillis();
        try {
            if (ParmCheckUtil.MIN_CODE >= examinQueryDTO.getDomainId()) {
                result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
                logger.info("queryMerchantExamineByPage domainId:{} is error", examinQueryDTO.getDomainId());
                return result;
            }
            MemPageResult<ExamineDO> pageResult = talentExamineManager.queryMerchantExamineByPage(examinQueryDTO);
            if (pageResult.isSuccess()) {
                // 判断value是否为空
                if (!ParmCheckUtil.checkListNull(pageResult.getList())) {
                    List<ExamineInfoDTO> examineInfoDTOs = new ArrayList<ExamineInfoDTO>();
                    for (ExamineDO examineDO : pageResult.getList()) {
                        // 数据转换
                        examineInfoDTOs.add(ExamineConverter.examineDOToDTO(examineDO));
                    }
                    result.setList(examineInfoDTOs);
                    result.setTotalCount(pageResult.getTotalCount());
                    result.setPageNo(pageResult.getPageNo());
                    result.setHasNext(pageResult.isHasNext());
                }
                logger.info("queryMerchantExamineByPage par:{} return ok， cost:{}ms",
                        JSONObject.toJSONString(examinQueryDTO), (System.currentTimeMillis() - start));
            } else {
                result.setReturnCode(pageResult.getReturnCode());
                logger.info("queryMerchantExamineByPage par:{} is error return:{}",
                        JSONObject.toJSONString(examinQueryDTO), JSONObject.toJSONString(pageResult));
            }
        } catch (Exception e) {
            logger.error("queryMerchantExamineByPage par:{} error:{}", JSONObject.toJSONString(examinQueryDTO), e);
            result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * @see com.yimayhd.membercenter.client.service.examin.ExaminDealService#queryMerchantExaminByPage(com.yimayhd.
     * membercenter.client.examineDealDTO)
     */
    @Override
    public MemResult<Boolean> dealExamineInfo(ExamineDealDTO examineDealDTO) {
        MemResult<Boolean> result = new MemResult<Boolean>();
        long start = System.currentTimeMillis();
        try {
            if (ParmCheckUtil.checkExamineDealDTO(examineDealDTO)) {
                result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
                logger.info("examinInfoIsOk par:{} is error", JSONObject.toJSONString(examineDealDTO));
                return result;
            }
            // 数据转化
            ExamineDO examineDO = ExamineConverter.examineDealToDO(examineDealDTO);
            result = talentExamineManager.dealExamineInfo(examineDO);
            logger.info("examinInfoIsOk par:{} return:{}, costs:{}ms", JSONObject.toJSONString(examineDealDTO),
                    JSONObject.toJSONString(result), (System.currentTimeMillis() - start));
        } catch (Exception e) {
            logger.error("examineInfoIsOk par:{} error:{}", JSONObject.toJSONString(examineDealDTO), e);
            result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * @see com.yimayhd.membercenter.client.service.examin.ExaminDealService#queryExamineDealResult(com.yimayhd.
     * membercenter.client.InfoQueryDTO)
     */
    @Override
    public MemResult<ExamineResultDTO> queryExamineDealResult(InfoQueryDTO examineQueryDTO) {
        MemResult<ExamineResultDTO> result = new MemResult<ExamineResultDTO>();
        long start = System.currentTimeMillis();
        try {
            if (ParmCheckUtil.checkQueryDTO(examineQueryDTO)) {
                result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
                logger.info("queryMerchantExaminInfo par:{} is error", JSONObject.toJSONString(examineQueryDTO));
                return result;
            }
            // 数据转换
            ExamineDO examineDO = ExamineConverter.examineQueryToDO(examineQueryDTO);
            MemResult<ExamineDO> queryResult = talentExamineManager.queryMerchantExamineInfoById(examineDO);
            if (queryResult.isSuccess()) {
                if (null != queryResult.getValue()) {
                    ExamineResultDTO examineResultDTO = new ExamineResultDTO();
                    examineResultDTO.setDealMes(queryResult.getValue().getExamineMes());
                    examineResultDTO.setStatus(ExamineStatus.getByStatus(queryResult.getValue().getStatues()));
                    result.setValue(examineResultDTO);
                }
                logger.info("queryExamineDealResult par:{} return success, costs:{}ms",
                        JSONObject.toJSONString(examineQueryDTO), (System.currentTimeMillis() - start));
            } else {
                result.setReturnCode(queryResult.getReturnCode());
                logger.info("queryExamineDealResult par:{} return error:{}", JSONObject.toJSONString(examineQueryDTO),
                        queryResult.getErrorMsg());
            }
        } catch (Exception e) {
            logger.error("queryExamineDealResult par:{} error:{}", JSONObject.toJSONString(examineQueryDTO), e);
            result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * @see com.yimayhd.membercenter.client.service.examin.ExaminDealService#queryMerchantExamineInfoById()
     */
    @Override
    public MemResult<ExamineInfoDTO> queryMerchantExamineInfoById(long id) {
        MemResult<ExamineInfoDTO> result = new MemResult<ExamineInfoDTO>();
        long start = System.currentTimeMillis();
        try {
            if (ParmCheckUtil.MIN_CODE >= id) {
                result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
                logger.info("queryMerchantExamineInfoById par:{} is error", id);
                return result;
            }
            ExamineDO examineDO = new ExamineDO();
            examineDO.setId(id);
            MemResult<ExamineDO> queryResult = talentExamineManager.queryMerchantExamineInfoById(examineDO);
            if (queryResult.isSuccess()) {
                ExamineInfoDTO examinDTO = ExamineConverter.examineDOToDTO(queryResult.getValue());
                result.setValue(examinDTO);
                logger.info("queryMerchantExamineInfoById par:{} return success, costs:{}ms", id,
                        (System.currentTimeMillis() - start));
            } else {
                result.setReturnCode(queryResult.getReturnCode());
                logger.info("queryMerchantExamineInfoById par:{} return error:{}", id, queryResult.getErrorMsg());
            }
        } catch (Exception e) {
            logger.error("queryMerchantExamineInfoBySellerId par:{} error:{}", id, e);
            result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
        }
        return result;
    }

    @Override
    public MemResult<Boolean> changeExamineStatusIntoIng(InfoQueryDTO examineQueryDTO) {
        MemResult<Boolean> result = new MemResult<Boolean>();
        long start = System.currentTimeMillis();
        try {
            if (ParmCheckUtil.checkQueryDTO(examineQueryDTO)) {
                result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
                logger.info("queryMerchantExaminInfo par:{} is error", JSONObject.toJSONString(examineQueryDTO));
                return result;
            }
            // 数据转换
            ExamineDO examineDO = ExamineConverter.examineQueryToDO(examineQueryDTO);
            result = talentExamineManager.changeExamineStatus(examineDO);
            logger.info("queryMerchantExamineInfoById par:{} return success, costs:{}ms",
                    JSONObject.toJSONString(examineQueryDTO), (System.currentTimeMillis() - start));
        } catch (Exception e) {
            logger.error("queryExamineDealResult par:{} error:{}", JSONObject.toJSONString(examineQueryDTO), e);
            result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
        }
        return result;
    }

}
