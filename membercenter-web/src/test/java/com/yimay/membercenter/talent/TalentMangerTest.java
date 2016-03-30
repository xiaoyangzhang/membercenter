/*
 * FileName: CommissionTest.java
 * Author:   liubb
 * Date:     2016年1月8日 上午9:35:33
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimay.membercenter.talent;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.yimayhd.membercenter.client.domain.merchant.MerchantInfoDO;
import com.yimayhd.membercenter.client.domain.talent.TalentInfoDO;
import com.yimayhd.membercenter.client.query.MerchantQueryDTO;
import com.yimayhd.membercenter.client.query.talent.TalentQueryDTO;
import com.yimayhd.membercenter.client.result.MemPageResult;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.manager.talent.TalentInfoManager;
import com.yimayhd.user.client.dto.MerchantUserDTO;
import com.yimayhd.user.client.enums.MerchantOption;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class TalentMangerTest extends BaseTest {

    @Autowired
    TalentInfoManager talentInfoManager;
    
    private static final int domainId = 1200;
    
    @Test
    public void queryTalentInfo(){
        long talentId = 10000000;
        MemResult<MerchantUserDTO> result = talentInfoManager.queryTalentInfo(talentId, domainId);
        System.out.println("----->");
        System.out.println("*****  " + JSONObject.toJSONString(result));
        System.out.println("----->");
    }
   
    @Test
    public void queryTalentList(){
        TalentQueryDTO talentQueryDTO = new TalentQueryDTO();
        talentQueryDTO.setDomainId(domainId);
//        talentQueryDTO.setTagId(ServiceTypeOption.CAR.getCode());
        talentQueryDTO.setSearchWord("家");
        talentQueryDTO.setPageNo(1);
        talentQueryDTO.setPageSize(10);
        MemPageResult<TalentInfoDO> result = talentInfoManager.queryTalentList(talentQueryDTO);
        System.out.println("----->");
        System.out.println("*****  " + JSONObject.toJSONString(result));
        System.out.println("----->");
    }
    
    @Test
    public void queryMerchantList(){
        MerchantQueryDTO merchantQueryDTO = new MerchantQueryDTO();
        merchantQueryDTO.setDomainId(domainId);
        merchantQueryDTO.setPageNo(1);
        merchantQueryDTO.setPageSize(10);
        merchantQueryDTO.setMerchantType(MerchantOption.EAT.getCode());
        MemPageResult<MerchantInfoDO> result = talentInfoManager.queryMerchantList(merchantQueryDTO);
        System.out.println("----->");
        System.out.println("*****  " + JSONObject.toJSONString(result));
        System.out.println("----->");
    }
    
    
}
