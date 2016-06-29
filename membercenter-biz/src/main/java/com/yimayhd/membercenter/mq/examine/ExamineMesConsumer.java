package com.yimayhd.membercenter.mq.examine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yimayhd.membercenter.client.domain.examine.ExamineDO;
import com.yimayhd.membercenter.client.enums.topic.MemberTopic;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.enums.ExamineStatus;
import com.yimayhd.membercenter.mapper.ExamineDOMapper;
import com.yimayhd.membercenter.mq.BaseConsumer;
import com.yimayhd.membercenter.repo.MsgRepo;
import com.yimayhd.membercenter.repo.UserRepo;
import com.yimayhd.msgcenter.client.param.SendSmsOption;

/**
 * Created with IntelliJ IDEA. User: lbb Date: 2016/3/31 Time: 17:39 审批结果消息消费者
 */
public class ExamineMesConsumer extends BaseConsumer {
    private static final Logger logger = LoggerFactory.getLogger("MQ");

    private static final int BIZTYPE = 5;
    
    private static final int BIZSUBTYPE_SUCCESS = 5002;
    
    private static final int BIZSUBTYPE_FAIL = 5001;
    
    @Autowired
    MsgRepo msgRepo;
    
    @Autowired
    UserRepo userRepo;
    @Autowired
    private ExamineDOMapper examineDOMapper ;

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
        ExamineDO msg = (ExamineDO) message;
        ExamineDO examineDO = examineDOMapper.selectById(msg) ;
        if( examineDO == null ){
        	
        	logger.error("result:ExamineDO={}",JSON.toJSONString(examineDO));
        	return true; 
        }
        MemResult<String> moblieResult = userRepo.queryUserMobile(examineDO.getSellerId());
        if(!moblieResult.isSuccess()){
            logger.info("ExamineMesConsumer par:{}  query mobileNO return null, sendMes fail",JSON.toJSONString(examineDO), JSONObject.toJSONString(examineDO.getSellerId()));
            return true;
        }
        SendSmsOption sendSmsOption = new SendSmsOption();
        sendSmsOption.setOutId(System.currentTimeMillis());
        List<String> mobiles = new ArrayList<String>();
        mobiles.add(moblieResult.getValue());
        sendSmsOption.setMobileNums(mobiles);
        sendSmsOption.setBizType(BIZTYPE);
        //判断发送审核通过短信or审核未通过短信
        if (examineDO.getStatues() == ExamineStatus.EXAMIN_OK.getStatus()) {
            sendSmsOption.setBizSubtype(BIZSUBTYPE_SUCCESS); 
        }else{
            sendSmsOption.setBizSubtype(BIZSUBTYPE_FAIL); 
        }
        sendSmsOption.setSendDate(new Date());
        sendSmsOption.setUserId(examineDO.getSellerId());
        boolean sendSms = msgRepo.sendSms(sendSmsOption);
        logger.info("ExamineMesConsumer par:{} sendMes return {}", JSONObject.toJSONString(sendSmsOption), sendSms);
        return sendSms;
    }
}
