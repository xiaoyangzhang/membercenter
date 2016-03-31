package com.yimayhd.membercenter.manager.msgpush;

import com.yimayhd.membercenter.client.domain.examine.ExamineDO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: zhaoyue
 * Date: 2016/3/30
 * Time: 17:51
 * 消息分发处理类
 */
public class MsgHandlerManager {

    @Autowired
    private JxMsgManager jxMsgManager;

    /**
     * 审核结果的推送
     * @param examineDO
     * @return
     */
    public boolean pushExamineMsg(ExamineDO examineDO){


        return true;
    }

    /**
     * 审核结果发送短信
     * @param examineDO
     * @return
     */
    public boolean sendExamineSms(ExamineDO examineDO){


        return true;
    }



}
