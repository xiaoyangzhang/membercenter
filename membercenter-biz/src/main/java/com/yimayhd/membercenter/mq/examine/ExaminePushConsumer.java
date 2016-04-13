package com.yimayhd.membercenter.mq.examine;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yimayhd.membercenter.client.domain.examine.ExamineDO;
import com.yimayhd.membercenter.client.enums.topic.MemberTopic;
import com.yimayhd.membercenter.enums.ExamineStatus;
import com.yimayhd.membercenter.mq.BaseConsumer;
import com.yimayhd.membercenter.repo.MsgRepo;
import com.yimayhd.membercenter.util.ParmCheckUtil;
import com.yimayhd.msgcenter.client.domain.PushRecordDO;
import com.yimayhd.msgcenter.client.enums.PushSendType;

/**
 * Created with IntelliJ IDEA. User: zhaoyue Date: 2016/3/31 Time: 17:39 审批结果消息消费者
 */
public class ExaminePushConsumer extends BaseConsumer {
    private static final Logger logger = LoggerFactory.getLogger("MQ");

    private static final String SUCCESS_PUSH = "恭喜您的入驻审核通过，请立即登录九休商家中心进行查看吧！";

    private static final String PARTTEN = "{mes}";

    private static final String FAIL_PUSH = "很抱歉的通知您，您在九休商家中心申请的入驻因以下问题未审核通过，请尽快登陆九休商家中心，修改相关信息后可再次提交申请。" + PARTTEN
            + "如您有任何疑问，请直接联系：4000-696-888。";

    private static final String PUSH_TITLE = " 商户信息审核";

    private static final String SPLIT = ";";
    
    private static final int BIZ_TYPE = 1;
    
    private static final int BIZ_SUB_TYPE = 3001;
    
    private static final int APPLICATION_ID = 21;

    @Autowired
    MsgRepo msgRepo;

    @Override
    public String getTopic() {
        return MemberTopic.EXAMINE_RESULT.getTopic();
    }

    @Override
    public String getTags() {
        return MemberTopic.EXAMINE_RESULT.getTags();
    }

    @Override
    public boolean doConsumeMessage(Serializable message) {
        String log = "UUID+" + UUID.randomUUID() + "  topic=" + getTopic() + "  msg={}" + JSON.toJSONString(message);
        logger.info(log);
        if (!(message instanceof ExamineDO)) {
            logger.error(log + "   Message not ExamineDO!");
            return true;
        }
        ExamineDO examineDO = (ExamineDO) message;
        PushRecordDO pushRecordDO = new PushRecordDO();
        pushRecordDO.setSendType(PushSendType.REGISTRATION_ID.getType());
        pushRecordDO.setUserId(examineDO.getSellerId());
        pushRecordDO.setPushTitle(PUSH_TITLE);
        pushRecordDO.setOutId(examineDO.getId());
        pushRecordDO.setBizType(BIZ_TYPE);
        pushRecordDO.setBizSubtype(BIZ_SUB_TYPE);
        pushRecordDO.setApplicationId(APPLICATION_ID);
        // 判断发送审核通过or审核未通过PUSH
        if (examineDO.getStatues() == ExamineStatus.EXAMIN_OK.getStatus()) {
            pushRecordDO.setPushContent(SUCCESS_PUSH);
        } else {
            pushRecordDO.setPushContent(FAIL_PUSH.replace(PARTTEN, examineMesFormat(examineDO.getExamineMes())));
        }
        boolean sendPush = msgRepo.sendPush(pushRecordDO);
        logger.info("ExamineMesConsumer par:{} pushMes return {}", JSONObject.toJSONString(pushRecordDO), sendPush);
        return sendPush;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈审批信息format〉
     * 
     * @param examineMes
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private static String examineMesFormat(String examineMes) {
        StringBuilder stringBuilder = new StringBuilder();
        List<String> mesList = Arrays.asList(examineMes.split(SPLIT));
        if (!ParmCheckUtil.checkListNull(mesList)) {
            stringBuilder.append(" \n未通过原因:\n");
            for (int i = 1; i <= mesList.size(); i++) {
                stringBuilder.append(i);
                stringBuilder.append(":" + mesList.get(i - 1) + ";\n");
            }
        }
        return stringBuilder.toString();
    }
}
