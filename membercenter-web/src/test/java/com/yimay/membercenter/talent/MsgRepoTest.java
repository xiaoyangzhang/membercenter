/*
 * FileName: MsgRepoTest.java
 * Author:   liubb
 * Date:     2016年4月12日 下午6:33:51
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimay.membercenter.talent;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.membercenter.repo.MsgRepo;
import com.yimayhd.msgcenter.client.domain.PushRecordDO;
import com.yimayhd.msgcenter.client.enums.PushSendType;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class MsgRepoTest extends BaseTest {

    private static final String SUCCESS_PUSH = "恭喜您的入驻审核通过，请立即登录九休商家中心进行查看吧！";

    private static final String PUSH_TITLE = " 商户信息审核";


    private static final int BIZ_TYPE = 1;

    private static final int BIZ_SUB_TYPE = 3001;

    @Autowired
    MsgRepo msgRepo;

    @Test
    public void sendPush() {
        PushRecordDO pushRecordDO = new PushRecordDO();
        pushRecordDO.setSendType(PushSendType.REGISTRATION_ID.getType());
        pushRecordDO.setUserId(17100);
        pushRecordDO.setPushTitle(PUSH_TITLE);
        pushRecordDO.setOutId(111);
        pushRecordDO.setBizType(BIZ_TYPE);
        pushRecordDO.setBizSubtype(BIZ_SUB_TYPE);
        pushRecordDO.setPushContent(SUCCESS_PUSH);
        // pushRecordDO.setPushContent(FAIL_PUSH.replace(PARTTEN, examineMesFormat(examineDO.getExamineMes())));
        pushRecordDO.setApplicationId(21);
        boolean sendPush = msgRepo.sendPush(pushRecordDO);
        System.out.println("----->");
        System.out.println("*****  " + sendPush);
        System.out.println("----->");
    }

}
