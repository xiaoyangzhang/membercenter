/*
 * FileName: MerchantRepoTest.java
 * Author:   liubb
 * Date:     2016年4月1日 下午2:25:56
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
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.repo.MerchantRepo;
import com.yimayhd.membercenter.repo.UserOptionRepo;
import com.yimayhd.membercenter.repo.UserRepo;
import com.yimayhd.user.client.domain.MerchantDO;
import com.yimayhd.user.client.dto.MerchantUserDTO;
import com.yimayhd.user.client.enums.MerchantOption;
import com.yimayhd.user.client.enums.UserOptions;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class MerchantRepoTest extends BaseTest {

    @Autowired
    MerchantRepo merchantRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserOptionRepo userOptionRepo;

    @Test
    public void batchInsertMerchantInfo() {
        MemResult<MerchantUserDTO> memResult = merchantRepo.queryMerchantBySellerId(13, 1200);
        MemResult<MerchantDO> saveMerchant = null;
        if (memResult.isSuccess()) {
            List<UserOptions> userOptionsList = new ArrayList<UserOptions>();
            userOptionsList.add(UserOptions.COMMERCIAL_TENANT);
            MerchantDO merchantDO = memResult.getValue().getMerchantDO();
            for (int i = 61; i < 100; i++) {
                merchantDO.setSellerId(i);
                merchantDO.setName(merchantDO.getName() + i);
                merchantDO.setOption(MerchantOption.EAT.getOption());
                merchantDO.setGmtCreated(new Date());
                merchantDO.setGmtModified(new Date());
                merchantDO.setSalesQuantity(i);
                saveMerchant = merchantRepo.saveMerchant(merchantDO);
                userOptionRepo.addUserOption(i, userOptionsList);
                System.out.println("----->");
                System.out.println("*****  " + JSONObject.toJSONString(saveMerchant));
                System.out.println("----->");
            }
        }
    }

    @Test
    public void queryUserDO() {
        MemResult<String> result = userRepo.queryUserMobile(21302);
        System.out.println("----->");
        System.out.println("*****  " + JSONObject.toJSONString(result));
        System.out.println("----->");
    }
}
