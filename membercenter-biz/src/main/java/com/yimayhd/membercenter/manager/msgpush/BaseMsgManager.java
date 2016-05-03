package com.yimayhd.membercenter.manager.msgpush;

import com.alibaba.fastjson.JSONObject;
import com.yimayhd.membercenter.client.domain.examine.ExamineDO;
import com.yimayhd.membercenter.repo.MsgRepo;
import com.yimayhd.msgcenter.client.domain.PushRecordDO;
import com.yimayhd.msgcenter.client.enums.PushBizType;
import com.yimayhd.msgcenter.client.enums.PushSendType;
import com.yimayhd.msgcenter.client.param.SendSmsOption;
import com.yimayhd.tradecenter.client.model.domain.order.BizOrderDO;
import com.yimayhd.tradecenter.client.model.domain.person.ContactUser;
import com.yimayhd.tradecenter.client.model.enums.BizOrderFeatureKey;
import com.yimayhd.tradecenter.client.model.result.order.MsgPushDO;
import com.yimayhd.tradecenter.client.util.BizOrderUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: zhaoyue
 * Date: 2016/3/30
 * Time: 17:49
 * 消息相关基类
 */
public abstract class BaseMsgManager {
    private static final Logger logger = LoggerFactory.getLogger(BaseMsgManager.class);

    @Autowired
    protected MsgRepo msgRepo;

    private static final String EXTEND_KEY = "data";


    /**
     * 构建对象发送消息
     * @param content
     * @param userId
     * @param appId
     * @param outId
     * @param subType
     * @return
     */
    protected boolean pushMsg(String content, long userId, int appId, long outId, int subType) {

        //发送消息提醒载体对象
        PushRecordDO pushRecordDO = new PushRecordDO();
        pushRecordDO.setUserId(userId);
        pushRecordDO.setApplicationId(appId);
        pushRecordDO.setPushContent(content);
        pushRecordDO.setSendType(PushSendType.REGISTRATION_ID.getType());
        pushRecordDO.setBizSubtype(subType);
        pushRecordDO.setBizType(PushBizType.TRANSACTION.getType());
        pushRecordDO.setOutId(outId);

        return msgRepo.sendPush(pushRecordDO);
    }

    /**
     * 发送短信
     * @param replaceContent
     * @param userId
     * @param outId
     * @param mobileNums
     * @param bizType
     * @param bizSubType
     * @return
     */
    protected boolean sendSms(List<Object> replaceContent,List<String> mobileNums,long userId,long outId,int bizType,int bizSubType){

        SendSmsOption sendSmsOption = new SendSmsOption();
        sendSmsOption.setBizType(bizType);
        sendSmsOption.setBizSubtype(bizSubType);

        if(CollectionUtils.isEmpty(mobileNums)){
            return false;
        }

        sendSmsOption.setReplaceContent(replaceContent);
        sendSmsOption.setMobileNums(mobileNums);
        sendSmsOption.setOutId(outId);
        sendSmsOption.setSendDate(new Date());
        sendSmsOption.setUserId(userId);
        return msgRepo.sendSms(sendSmsOption);
    }

    /**
     * 商家审核后,审核结果发送推送消息
      * @param examineDO
     * @return
     */
    protected abstract boolean pushExamineMsg(ExamineDO examineDO);

    /**
     * 商家审核后,审核结果发送短信
     * @param examineDO
     * @return
     */
    protected abstract boolean sendExamineSms(ExamineDO examineDO);


}
