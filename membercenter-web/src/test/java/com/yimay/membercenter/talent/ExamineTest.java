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
import com.alibaba.rocketmq.client.producer.SendResult;
import com.yimayhd.membercenter.client.domain.examine.ExamineDO;
import com.yimayhd.membercenter.client.dto.ExamineDealDTO;
import com.yimayhd.membercenter.client.dto.ExamineInfoDTO;
import com.yimayhd.membercenter.client.dto.ExamineResultDTO;
import com.yimayhd.membercenter.client.enums.topic.MemberTopic;
import com.yimayhd.membercenter.client.query.InfoQueryDTO;
import com.yimayhd.membercenter.client.query.examine.ExaminePageQueryDTO;
import com.yimayhd.membercenter.client.result.MemPageResult;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.service.examine.ExamineDealService;
import com.yimayhd.membercenter.enums.ExamineStatus;
import com.yimayhd.membercenter.enums.ExamineType;
import com.yimayhd.membercenter.mq.MsgSender;

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

    @Autowired
    MsgSender msgSender;
    
    @Test
    public void queryMerchantExamineInfoById() {
        InfoQueryDTO examineQueryDTO = new InfoQueryDTO();
        examineQueryDTO.setDomainId(domainId);
        examineQueryDTO.setSellerId(40100);
        examineQueryDTO.setType(ExamineType.MERCHANT.getType());
        MemResult<ExamineInfoDTO> result = examineDealService.queryMerchantExamineInfoBySellerId(examineQueryDTO);
        System.out.println("----->");
        System.out.println("*****  " + JSONObject.toJSONString(result));
        System.out.println("----->");
    }

    @Test
    public void queryExamineDealResult() {
        InfoQueryDTO examineQueryDTO = new InfoQueryDTO();
        examineQueryDTO.setDomainId(domainId);
        examineQueryDTO.setSellerId(40100);
        examineQueryDTO.setType(ExamineType.TALENT.getType());
        MemResult<ExamineResultDTO> result = examineDealService.queryExamineDealResult(examineQueryDTO);
        System.out.println("----->");
        System.out.println("*****  " + JSONObject.toJSONString(result));
        System.out.println("----->");
    }

    @Test
    public void queryMerchantExaminByPage() {
        ExaminePageQueryDTO examinQueryDTO = new ExaminePageQueryDTO();
        examinQueryDTO.setDomainId(domainId);
        examinQueryDTO.setPageNo(1);
        examinQueryDTO.setPageSize(1);
        // examinQueryDTO.setPrincipleName("审核信息");
        // examinQueryDTO.setStatus(ExamineStatus.EXAMIN_NOT_ABLE.getStatus());
        // examinQueryDTO.setPrincipleTel("186");
        int[] statusArray = new int[4];
        statusArray[0] = 1;
        statusArray[2] = 2;
        statusArray[3] = 3;
        statusArray[1] = 4;
        examinQueryDTO.setStatusArray(statusArray);
        MemPageResult<ExamineInfoDTO> result = examineDealService.queryMerchantExamineByPage(examinQueryDTO);
        System.out.println("----->");
        System.out.println("*****  " + JSONObject.toJSONString(result));
        System.out.println("----->");
    }

    @Test
    public void dealExamineInfo() {
        ExamineDealDTO examineDealDTO = new ExamineDealDTO();
        examineDealDTO.setDomainId(domainId);
        examineDealDTO.setSellerId(21506);
        examineDealDTO.setType(ExamineType.MERCHANT.getType());
        // examineDealDTO.setId(1220l);
        examineDealDTO.setCheckIsOk(true);
//        examineDealDTO.setExamineMes("审核通过");
        examineDealDTO.setReviewerId(19202);
        MemResult<Boolean> result = examineDealService.dealExamineInfo(examineDealDTO);
        System.out.println("----->");
        System.out.println("*****  " + JSONObject.toJSONString(result));
        System.out.println("----->");
    }

    @Test
    public void submitMerchantExamineInfoFristPage() {
        // ExamineSubmitDTO examineSubmitDTO = new ExamineSubmitDTO();
        ExamineInfoDTO examinDTO = new ExamineInfoDTO();
        String pictureUrl = "FristPage.jpg";
        examinDTO.setId(1);
        examinDTO.setDomainId(domainId);
        examinDTO.setType(ExamineType.MERCHANT.getType());
        examinDTO.setSellerId(40100);
        examinDTO.setSellerName("审核信息SellerName11" + 40100);
        examinDTO.setLegralName("审核信息LegralName");
        examinDTO.setAddress("审核信息testFristPage");
        examinDTO.setSaleScope("审核信息testFristPage");
//        examinDTO.setLegralCardUp(pictureUrl);
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

        // examineSubmitDTO.setExamineInfoDTO(examinDTO);
        // examineSubmitDTO.setPageNo(ExaminePageNo.PAGE_ONE.getPageNO());
        MemResult<Boolean> result = examineDealService.submitMerchantExamineInfo(examinDTO);
        System.out.println("----->");
        System.out.println("*****  " + JSONObject.toJSONString(result));
        System.out.println("----->");
    }

    @Test
    public void submitMerchantExamineInfoNextPage() {
        // ExamineSubmitDTO examineSubmitDTO = new ExamineSubmitDTO();
        ExamineInfoDTO examinDTO = new ExamineInfoDTO();
        String pictureUrl = "NextPage.jpg";
        examinDTO.setDomainId(domainId);
        examinDTO.setType(ExamineType.MERCHANT.getType());
        examinDTO.setSellerId(40100);
        examinDTO.setPrincipleName("审核信息testNextPage");
        examinDTO.setPrincipleCard("审核信息testNextPage");
        examinDTO.setPrincipleCardId("审核信息testNextPage");
        examinDTO.setPrincipleTel("18651664499");
        examinDTO.setPrincipleMail("审核信息testNextPage");
//        examinDTO.setPrincipleCardUp(pictureUrl);
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
//        examinDTO.setCooperation1(pictureUrl);
//        examinDTO.setCooperation2(pictureUrl);
//        examinDTO.setCooperation3(pictureUrl);
//        examinDTO.setCooperation4(pictureUrl);
//        examinDTO.setCooperation5(pictureUrl);

        // examineSubmitDTO.setExamineInfoDTO(examinDTO);
        // examineSubmitDTO.setPageNo(ExaminePageNo.PAGE_TWO.getPageNO());
        MemResult<Boolean> result = examineDealService.submitMerchantExamineInfo(examinDTO);
        System.out.println("----->");
        System.out.println("*****  " + JSONObject.toJSONString(result));
        System.out.println("----->");
    }

    @Test
    public void sendMes() {
        ExamineDO examineDO = new ExamineDO();
        examineDO.setDomainId(1200);
        examineDO.setSellerId(1900);
        examineDO.setTelNum("18761640297");
        examineDO.setType(ExamineType.MERCHANT.getType());
        examineDO.setStatues(ExamineStatus.EXAMIN_ERROR.getStatus());
        examineDO.setExamineMes("test");
        examineDO.setId(11111);
        SendResult sendResult = msgSender.sendMessage(examineDO, MemberTopic.EXAMINE_RESULT.getTopic(),
                MemberTopic.EXAMINE_RESULT.getTags());
        System.out.println("----->");
        System.out.println("*****  " + JSONObject.toJSONString(sendResult));
        System.out.println("----->");
    }
    
    @Test
    public void changeExamineStatusIntoIng() {
        InfoQueryDTO examineQueryDTO = new InfoQueryDTO();
        examineQueryDTO.setDomainId(domainId);
        examineQueryDTO.setSellerId(444);
        examineQueryDTO.setType(ExamineType.TALENT.getType());
        MemResult<Boolean> result = examineDealService.changeExamineStatusIntoIng(examineQueryDTO);
        System.out.println("----->");
        System.out.println("*****  " + JSONObject.toJSONString(result));
        System.out.println("----->");
    }

}
