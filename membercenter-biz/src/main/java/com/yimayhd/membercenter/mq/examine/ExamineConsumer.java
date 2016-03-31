package com.yimayhd.membercenter.mq.examine;

import com.yimayhd.membercenter.client.domain.examine.ExamineDO;
import com.yimayhd.membercenter.client.enums.topic.MemberTopic;
import com.yimayhd.membercenter.manager.msgpush.MsgHandlerManager;
import com.yimayhd.membercenter.mq.BaseConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: zhaoyue
 * Date: 2016/3/31
 * Time: 17:39
 * 审批结果消息消费者
 */
public class ExamineConsumer extends BaseConsumer{
    private static final Logger logger = LoggerFactory.getLogger(ExamineConsumer.class);

    @Autowired
    private MsgHandlerManager msgHandlerManager;


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

        logger.info("get examine mq:" + message);
        if(message == null){
            return true;
        }

        ExamineDO examineDO = (ExamineDO)message;
        boolean sendMsgStatus = msgHandlerManager.pushExamineMsg(examineDO);//push消息
        boolean sendSmsStatus = msgHandlerManager.sendExamineSms(examineDO);//发送短信
        if(sendMsgStatus && sendSmsStatus){
            return true;
        }
        return false;
    }
}
