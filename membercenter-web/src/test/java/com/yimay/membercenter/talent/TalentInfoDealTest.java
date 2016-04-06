/*
 * FileName: TalentBackInfoTest.java
 * Author:   liubb
 * Date:     2016年3月25日 下午2:03:17
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimay.membercenter.talent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.yimayhd.membercenter.client.domain.CertificatesDO;
import com.yimayhd.membercenter.client.domain.PictureTextDO;
import com.yimayhd.membercenter.client.domain.talent.TalentInfoDO;
import com.yimayhd.membercenter.client.dto.AccountDTO;
import com.yimayhd.membercenter.client.dto.BankInfoDTO;
import com.yimayhd.membercenter.client.dto.PictureTextDTO;
import com.yimayhd.membercenter.client.dto.TalentInfoDTO;
import com.yimayhd.membercenter.client.query.InfoQueryDTO;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.service.back.TalentInfoDealService;
import com.yimayhd.membercenter.enums.ExamineType;
import com.yimayhd.user.client.enums.MerchantOption;
import com.yimayhd.user.client.enums.ServiceTypeOption;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class TalentInfoDealTest extends BaseTest {

    private static final int domainId = 1200;

    private static final int userId = 10;

    @Autowired
    TalentInfoDealService talentInfoDealService;

    @Test
    public void queryTalentServiceType() {
        MemResult<List<CertificatesDO>> result = talentInfoDealService.queryTalentServiceType();
        System.out.println("----->");
        System.out.println("*****  " + JSONObject.toJSONString(result));
        System.out.println("----->");
    }

    @Test
    public void updateTalentInfo() {
        for (int i = 1; i <= 1; i++) {
            String pictureUrl = "T1jyJTBXAT1RXrhCrK.jpg";
            TalentInfoDTO talentInfoDTO = new TalentInfoDTO();
            TalentInfoDO talentInfoDO = new TalentInfoDO();
            talentInfoDO.setId(userId + 4);
            talentInfoDO.setAvatar(pictureUrl);
            talentInfoDO.setNickName("pztyz"  + i);
            talentInfoDO.setReallyName("刘二斌121"  + i);
            talentInfoDO.setGender(1);
            talentInfoDO.setServeDesc("大家好才是真的好  test" + i);
            talentInfoDO.setServeCount(100 + i);
            talentInfoDO.setCity("北京市");
            talentInfoDO.setCityCode(110100);
            talentInfoDO.setServeDesc("飞吧飞吧飞");
            List<String> pictures = new ArrayList<String>();
            pictures.add(pictureUrl);
            pictures.add(pictureUrl);
            talentInfoDO.setPictures(pictures);

            List<CertificatesDO> certificates = new ArrayList<CertificatesDO>();
            CertificatesDO certificatesDO1 = new CertificatesDO();
            certificatesDO1.setId(Integer.valueOf(ServiceTypeOption.TRAVEL.getCode()));
            certificatesDO1.setName(ServiceTypeOption.TRAVEL.getDesc());
            certificatesDO1.setType(Integer.valueOf(MerchantOption.TALENT.getCode()));
            certificates.add(certificatesDO1);
            CertificatesDO certificatesDO2 = new CertificatesDO();
            certificatesDO2.setId(Integer.valueOf(ServiceTypeOption.ASK.getCode()));
            certificatesDO2.setName(ServiceTypeOption.ASK.getDesc());
            certificatesDO2.setType(Integer.valueOf(MerchantOption.TALENT.getCode()));
            certificates.add(certificatesDO2);
            CertificatesDO certificatesDO3 = new CertificatesDO();
            certificatesDO3.setId(Integer.valueOf(ServiceTypeOption.CAR.getCode()));
            certificatesDO3.setName(ServiceTypeOption.CAR.getDesc());
            certificatesDO3.setType(Integer.valueOf(MerchantOption.TALENT.getCode()));
            certificates.add(certificatesDO3);
            talentInfoDO.setCertificates(certificates);
            talentInfoDO.setType(true);
            talentInfoDO.setTelNum("18651664499");
            talentInfoDO.setBirthday(new Date());
            talentInfoDTO.setTalentInfoDO(talentInfoDO);

            PictureTextDTO pictureTextDTO = new PictureTextDTO();
            List<PictureTextDO> picTextList = new ArrayList<PictureTextDO>();
            PictureTextDO pictureTextDO = new PictureTextDO();
            pictureTextDO.setType(1);
            pictureTextDO.setValue("这里的山路十八弯");
            picTextList.add(pictureTextDO);
            pictureTextDTO.setPicTexts(picTextList);
            talentInfoDTO.setPictureTextDTO(pictureTextDTO);

            talentInfoDTO.setDomainId(domainId);
            MemResult<Boolean> result = talentInfoDealService.updateTalentInfo(talentInfoDTO);
            System.out.println("----->");
            System.out.println("*****  " + JSONObject.toJSONString(result));
            System.out.println("----->");
        }
    }

    @Test
    public void queryTalentInfoByUserId() {
        MemResult<TalentInfoDTO> result = talentInfoDealService.queryTalentInfoByUserId(19000, domainId);
        System.out.println("----->");
        System.out.println("*****  " + JSONObject.toJSONString(result));
        System.out.println("----->");
    }

    @Test
    public void queryMerchantAccountInfoById() {
        InfoQueryDTO infoQueryDTO = new InfoQueryDTO();
        infoQueryDTO.setDomainId(domainId);
        infoQueryDTO.setSellerId(19000);
        infoQueryDTO.setType(ExamineType.TALENT.getType());
        MemResult<AccountDTO> result = talentInfoDealService.queryMerchantAccountInfoById(infoQueryDTO);
        System.out.println("----->");
        System.out.println("*****  " + JSONObject.toJSONString(result));
        System.out.println("----->");
    }

    @Test
    public void updateMerchantAccountInfo() {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setDomainId(domainId);
        accountDTO.setSellerId(userId);
        accountDTO.setType(ExamineType.TALENT.getType());
        accountDTO.setProducterName("testName");
        accountDTO.setProducterTel("18651664499");
        accountDTO.setProducterMail("test@yimayholiday.com");
        accountDTO.setFinanceName("financeName");
        accountDTO.setFinanceTel("18651664499");
        accountDTO.setFinanceMail("test@yimayholiday.com");
        MemResult<Boolean> result = talentInfoDealService.updateMerchantAccountInfo(accountDTO);
        System.out.println("----->");
        System.out.println("*****  " + JSONObject.toJSONString(result));
        System.out.println("----->");
    }
    
    @Test
    public void queryBankList(){
        MemResult<List<BankInfoDTO>> result = talentInfoDealService.queryBankList();
        System.out.println("----->");
        System.out.println("*****  " + JSONObject.toJSONString(result));
        System.out.println("----->");
    }
}
