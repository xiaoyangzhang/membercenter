/*
 * FileName: ExamineTest.java
 * Author:   liubb
 * Date:     2016年3月26日 下午7:55:33
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimay.membercenter.talent;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.yimayhd.membercenter.client.dto.ExamineDealDTO;
import com.yimayhd.membercenter.client.dto.ExamineInfoDTO;
import com.yimayhd.membercenter.client.query.InfoQueryDTO;
import com.yimayhd.membercenter.client.query.examine.ExaminePageQueryDTO;
import com.yimayhd.membercenter.client.result.MemPageResult;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.service.examine.ExamineDealService;
import com.yimayhd.membercenter.enums.ExamineType;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class ExamineTest extends BaseTest {

    private static final int domainId = 1200;

    private static final long userId = 10;

    @Autowired
    ExamineDealService examineDealService;

    @Test
    public void queryMerchantExamineInfoById() {
        InfoQueryDTO examineQueryDTO = new InfoQueryDTO();
        examineQueryDTO.setDomainId(domainId);
        examineQueryDTO.setSellerId(userId);
        examineQueryDTO.setType(ExamineType.TALENT.getId());
        MemResult<ExamineInfoDTO> result = examineDealService.queryMerchantExamineInfoById(examineQueryDTO);
        System.out.println("----->");
        System.out.println("*****  " + JSONObject.toJSONString(result));
        System.out.println("----->");
    }

    @Test
    public void queryExamineDealResult() {
        InfoQueryDTO examineQueryDTO = new InfoQueryDTO();
        examineQueryDTO.setDomainId(domainId);
        examineQueryDTO.setSellerId(userId);
        examineQueryDTO.setType(ExamineType.TALENT.getId());
        MemResult<String> result = examineDealService.queryExamineDealResult(examineQueryDTO);
        System.out.println("----->");
        System.out.println("*****  " + JSONObject.toJSONString(result));
        System.out.println("----->");
    }

    @Test
    public void queryMerchantExaminByPage() {
        ExaminePageQueryDTO examinQueryDTO = new ExaminePageQueryDTO();
        examinQueryDTO.setDomainId(domainId);
        examinQueryDTO.setPageNo(1);
        examinQueryDTO.setPageSize(10);
        MemPageResult<ExamineInfoDTO> result = examineDealService.queryMerchantExamineByPage(examinQueryDTO);
        System.out.println("----->");
        System.out.println("*****  " + JSONObject.toJSONString(result));
        System.out.println("----->");
    }

    @Test
    public void examineInfoIsOk() {
        ExamineDealDTO examineDealDTO = new ExamineDealDTO();
        examineDealDTO.setDomainId(domainId);
        examineDealDTO.setSellerId(userId + 2);
        examineDealDTO.setType(ExamineType.TALENT.getId());
        examineDealDTO.setCheckIsOk(true);
        examineDealDTO.setExamineMes("审核通过");
        MemResult<Boolean> result = examineDealService.examineInfoIsOk(examineDealDTO);
        System.out.println("----->");
        System.out.println("*****  " + JSONObject.toJSONString(result));
        System.out.println("----->");
    }

    @Test
    public void submitMerchantExamineInfoFristPage() {
        ExamineInfoDTO examinDTO = new ExamineInfoDTO();
        String pictureUrl = "FristPage.jpg";
        examinDTO.setId(1);
        examinDTO.setDomainId(domainId);
        examinDTO.setType(ExamineType.TALENT.getId());
        examinDTO.setSellerId(userId + 4);
        examinDTO.setMerchantName("审核信息testFristPage");
        examinDTO.setLegralName("审核信息testFristPage");
        examinDTO.setAddress("审核信息testFristPage");
        examinDTO.setSaleScope("审核信息testFristPage");
        examinDTO.setLegralCardUp(pictureUrl);
        examinDTO.setLegralCardDown(pictureUrl);
        examinDTO.setBusinessLicense(pictureUrl);
        examinDTO.setOrgCard(pictureUrl);
        examinDTO.setAffairsCard(pictureUrl);
        examinDTO.setOpenCard(pictureUrl);
        examinDTO.setTravingCard(pictureUrl);
        examinDTO.setTouchProve(pictureUrl);
        examinDTO.setTravelAgencyAuthorization(pictureUrl);
        examinDTO.setTravelAgencyInsurance(pictureUrl);
        // type
        examinDTO.setTouristCard(pictureUrl);
        examinDTO.setDrivingLinence(pictureUrl);
        examinDTO.setDivingLinence(pictureUrl);
        examinDTO.setPhotographyCertificate(pictureUrl);
        examinDTO.setClimbingCertificate(pictureUrl);
        examinDTO.setTrainingCertificate(pictureUrl);
        examinDTO.setTeacherCertificate(pictureUrl);
        examinDTO.setArtCertificate(pictureUrl);

        MemResult<Boolean> result = examineDealService.submitMerchantExamineInfo(examinDTO);
        System.out.println("----->");
        System.out.println("*****  " + JSONObject.toJSONString(result));
        System.out.println("----->");
    }

    @Test
    public void submitMerchantExamineInfoNextPage() {
        ExamineInfoDTO examinDTO = new ExamineInfoDTO();
        String pictureUrl = "NextPage.jpg";
        examinDTO.setDomainId(domainId);
        examinDTO.setType(ExamineType.TALENT.getId());
        examinDTO.setSellerId(userId + 4);
        examinDTO.setPrincipleName("审核信息testNextPage");
        examinDTO.setPrincipleCard(pictureUrl);
        examinDTO.setPrincipleCardId("审核信息testNextPage");
        examinDTO.setPrincipleTel(18651664499L);
        examinDTO.setPrincipleMail("审核信息testNextPage");
        examinDTO.setPrincipleCardUp(pictureUrl);
        examinDTO.setPrincipleCardDown(pictureUrl);
        examinDTO.setFinanceOpenBankId("审核信息testNextPage");
        examinDTO.setFinanceOpenBankName("审核信息testNextPage");
        examinDTO.setFinanceOpenName("审核信息testNextPage");
        examinDTO.setAccountNum("审核信息testNextPage");
        examinDTO.setAccountBankProvince("审核信息testNextPage");
        examinDTO.setAccountBankProvinceCode("审核信息testNextPage");
        examinDTO.setAccountBankCity("审核信息testNextPage");
        examinDTO.setAccountBankCityCode("审核信息testNextPage");
        examinDTO.setAccountBankName("审核信息testNextPage");
        examinDTO.setCooperation1(pictureUrl);
        examinDTO.setCooperation2(pictureUrl);
        examinDTO.setCooperation3(pictureUrl);
        examinDTO.setCooperation4(pictureUrl);
        examinDTO.setCooperation5(pictureUrl);
        MemResult<Boolean> result = examineDealService.submitMerchantExamineInfo(examinDTO);
        System.out.println("----->");
        System.out.println("*****  " + JSONObject.toJSONString(result));
        System.out.println("----->");
    }

}
