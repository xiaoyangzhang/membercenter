package com.yimayhd.membercenter.manager.msgpush;

import com.yimayhd.membercenter.client.domain.examine.ExamineDO;
import com.yimayhd.membercenter.enums.ExamineStatus;
import com.yimayhd.msgcenter.client.enums.PushBizSubtype;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhaoyue
 * Date: 2016/3/30
 * Time: 17:51
 * 九休商户审核发送推送消息
 */
public class JxMsgManager extends BaseMsgManager {
    private static final Logger logger = LoggerFactory.getLogger(JxMsgManager.class);

    private static String MSG_APPROVE_TEMP = "恭喜您的入驻审核通过，请立即登录九休商家中心进行查看吧！";

    private static String SMS_APPROVE_TEMP = "恭喜您的入驻审核通过，请立即登录九休商家中心进行查看吧！【九休旅行】";

    private static String MSG_NOT_APPROVE_TEMP = "很抱歉的通知您，您在九休商家中心申请的入驻因以下问题未审核通过，请尽快登陆九休商家中心，" +
                                                    "修改相关信息后可再次提交申请。为通过原因：{0},如您有任何疑问，请直接联系：{1}。";

    private static String SMS_NOT_APPROVE_TEMP = "很抱歉的通知您，您在九休商家中心提交的入驻申请未审核通过，请尽快登陆九休商家中心查看未通过原因，" +
                                                    "修改相关信息后可再次提交申请。如您有任何疑问，请直接联系：4000-696-888。【九休旅行】";


    @Override
    protected boolean pushExamineMsg(ExamineDO examineDO) {

        //审核状态
        ExamineStatus examineStatus = ExamineStatus.getByStatus(examineDO.getStatues());
        String content = "";
        switch (examineStatus){
            case EXAMIN_OK:
                content = MSG_APPROVE_TEMP;
                break;
            case EXAMIN_ERROR:
                content = MessageFormat.format(MSG_NOT_APPROVE_TEMP,examineDO.getExamineMes());
                break;
        }

        //发送消息
        if(StringUtils.isNotBlank(content)){
            pushMsg(content,examineDO.getSellerId(),1,examineDO.getId(),PushBizSubtype.ORDER_PAYMENT_SUCCESS.getType());
            pushMsg(content,examineDO.getSellerId(),2,examineDO.getId(),PushBizSubtype.ORDER_PAYMENT_SUCCESS.getType());
        }
        return true;
    }

    @Override
    protected boolean sendExamineSms(ExamineDO examineDO) {

        //审核状态
        ExamineStatus examineStatus = ExamineStatus.getByStatus(examineDO.getStatues());
        switch (examineStatus){
            case EXAMIN_OK:

                break;
            case EXAMIN_ERROR:
                break;
        }

        List<String> mobileNums = new ArrayList<String>();
        mobileNums.add(String.valueOf(examineDO.getTelNum()));

        sendSms(null, mobileNums,examineDO.getSellerId(),examineDO.getId(),1,1);//发送短信
        return true;
    }



}
