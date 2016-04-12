package com.yimayhd.membercenter.repo;

import com.alibaba.fastjson.JSONObject;
import com.yimayhd.msgcenter.client.domain.PushRecordDO;
import com.yimayhd.msgcenter.client.param.SendSmsOption;
import com.yimayhd.msgcenter.client.result.BaseResult;
import com.yimayhd.msgcenter.client.service.MsgCenterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA. User: zhaoyue Date: 2015/12/15 Time: 15:16 客户端push消息
 */
public class MsgRepo {

    public static final Logger logger = LoggerFactory.getLogger(MsgRepo.class);

    @Autowired
    MsgCenterService msgCenterService;

    /**
     * 往客户端发送推送消息
     * 
     * @param pushRecordDO
     * @return
     */
    public boolean sendPush(PushRecordDO pushRecordDO) {
        try {
            BaseResult<Boolean> baseResult = msgCenterService.sendPush(pushRecordDO);
            // if (baseResult != null && baseResult.isSuccess()) {
            // result = baseResult.getValue();
            // } else if (baseResult != null) {
            // logger.error("MsgRepo pushMsg error:{}",baseResult.getResultMsg());
            // }
            logger.info("par:{} sendPush return :{}", JSONObject.toJSONString(pushRecordDO),
                    JSONObject.toJSONString(baseResult));
            return baseResult.isSuccess();
        } catch (Exception e) {
            logger.error("par:{} sendPush failed, error:{}", JSONObject.toJSONString(pushRecordDO), e);
        }
        return false;
    }

    /**
     * 发送短信
     * 
     * @param sendSmsOption
     * @return
     */
    public boolean sendSms(SendSmsOption sendSmsOption) {
        try {
            BaseResult<Boolean> baseResult = msgCenterService.sendSms(sendSmsOption);
            logger.info("par:{} sendSms return :{}", JSONObject.toJSONString(sendSmsOption),
                    JSONObject.toJSONString(baseResult));
            return baseResult.isSuccess();
        } catch (Exception e) {
            logger.error("par:{} sendSms failed, error:{}", JSONObject.toJSONString(sendSmsOption), e);
        }
        return false;
    }

}
