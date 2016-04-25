/*
 * FileName: TalentApiTest.java
 * Author:   liubb
 * Date:     2016年3月30日 上午11:04:51
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimay.membercenter.talent;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.yimayhd.membercenter.api.MerchantApi;
import com.yimayhd.membercenter.api.talent.TalentMemberApi;
import com.yimayhd.membercenter.entity.PageInfo;
import com.yimayhd.membercenter.entity.merchant.Merchant;
import com.yimayhd.membercenter.entity.merchant.MerchantList;
import com.yimayhd.membercenter.entity.merchant.MerchantQuery;
import com.yimayhd.membercenter.entity.talent.TalentInfo;
import com.yimayhd.membercenter.entity.talent.TalentInfoList;
import com.yimayhd.membercenter.entity.talent.TalentQuery;
import com.yimayhd.user.client.enums.MerchantOption;

/**
 * 〈一句话功能简述〉<br> 
 * 〈API接口测试〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class TalentApiTest extends BaseTest{

    @Autowired
    TalentMemberApi talentMemberApi;
    
    
    @Autowired
    MerchantApi merchantApi;
    
    @Test
    public void getTalentDetail(){
        TalentInfo talentDetail = talentMemberApi.getTalentDetail(21, 1200, 0, 0, 0, 10707);
        System.out.println("-------->");
        System.out.println("---->" + JSONObject.toJSONString(talentDetail));
        System.out.println("-------->");
    }
    
    
    @Test
    public void queryTalentList(){
        TalentQuery talentQueryDTO = new TalentQuery();
//        talentQueryDTO.tagId = ServiceTypeOption.CAR.getCode();
        talentQueryDTO.searchWord = "带你玩";
        PageInfo pageInfo = new PageInfo();
        pageInfo.pageNo= 1;
        pageInfo.pageSize = 10;
        talentQueryDTO.pageInfo = pageInfo;
        TalentInfoList queryTalentList = talentMemberApi.queryTalentList(21, 1200, 0, 0, 0, talentQueryDTO);
        System.out.println("-------->");
        System.out.println("---->" + JSONObject.toJSONString(queryTalentList));
        System.out.println("-------->");
    }
    
    
    @Test
    public void queryMerchantInfo(){
        Merchant talentDetail = merchantApi.queryMerchantInfo(21, 1200, 0, 0, 0, 22403);
        System.out.println("-------->");
        System.out.println("---->" + JSONObject.toJSONString(talentDetail));
        System.out.println("-------->");
    }
    
    @Test
    public void queryMerchantList(){
        MerchantQuery mechantQuery = new MerchantQuery();
        mechantQuery.merchantType = MerchantOption.EAT.name();
        PageInfo pageInfo = new PageInfo();
        pageInfo.pageNo= 1;
        pageInfo.pageSize = 10;
        mechantQuery.pageInfo = pageInfo;
        MerchantList merchantList = merchantApi.queryMerchantList(21, 1200, 0, 0, 0, mechantQuery);
        System.out.println("-------->");
        System.out.println("---->" + JSONObject.toJSONString(merchantList));
        System.out.println("-------->");
    }
    
}
