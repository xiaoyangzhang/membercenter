package com.yimayhd.membercenter.mq;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.LocalTransactionExecuter;
import com.alibaba.rocketmq.client.producer.LocalTransactionState;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.client.producer.SendStatus;
import com.alibaba.rocketmq.client.producer.TransactionCheckListener;
import com.alibaba.rocketmq.client.producer.TransactionMQProducer;
import com.alibaba.rocketmq.client.producer.TransactionSendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.yimayhd.membercenter.util.HessianUtils;

/**
 * 
 * @author wuzhengfei
 *
 */
public class MsgSenderService implements MsgSender {
    protected static final Logger logger = LoggerFactory.getLogger(MsgSenderService.class);
    private TransactionMQProducer producer;
    private String nameServer;
    private boolean test = false;
    
    private static final String DOMAIN = "rocketmq.namesrv.domain";
    private static final String PRODUCER_GROUP = "p_member_trans";

    public static List<Checker> checkerList = new ArrayList<Checker>();
    static {
    }

    public void init() throws MQClientException {
        //为本地测试关闭metaq的接收
        if ("localTest".equals(nameServer)) {
            return;
        }
        //设置nameserver 地址
        if (StringUtils.isBlank(System.getProperty(DOMAIN))) {
            System.setProperty(DOMAIN, nameServer);
        }
        TransactionCheckListener transactionCheckListener = new TransactionCheckListener() {
            @Override
            public LocalTransactionState checkLocalTransactionState(MessageExt messageExt) {
                try {
                    Object msg = HessianUtils.decode(messageExt.getBody());
                    return getCommitStatus(messageExt.getTopic(),messageExt.getTags(), msg);
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                    return LocalTransactionState.COMMIT_MESSAGE;
                }
            }
        };
        producer = new TransactionMQProducer(PRODUCER_GROUP);
        //事务回查最小并发数
        producer.setCheckThreadPoolMinSize(2);
        //事务回查最大并发数
        producer.setCheckThreadPoolMaxSize(2);
        //队列数
        producer.setCheckRequestHoldMax(2000);

        producer.setNamesrvAddr(nameServer);
        //设置消息体最大值，默认只有128k
        producer.setMaxMessageSize(1024*512);

        //设置事务会查监听器
        producer.setTransactionCheckListener(transactionCheckListener);

        producer.start();

        logger.info("metaq start success! nameServer={},producer group={}",System.getProperty(DOMAIN), PRODUCER_GROUP);
    }

    private LocalTransactionState getCommitStatus(String topic, String tags, Object msg) {
        for (Checker checker :checkerList) {
            if (checker.in(topic, tags)) {
                return checker.check(msg);
            }
        }
        return LocalTransactionState.COMMIT_MESSAGE;
    }

    /**
     * 发送消息服务
     *
     * @param message 消息对象
     * @param topic   topic名称
     * @param tag     tag名称
     * @return 发送结果
     */
    @Override
    public SendResult sendMessage(Serializable message, String topic, String tag) {
        if (test) {
            SendResult sendResult = new SendResult();
            sendResult.setSendStatus(SendStatus.SEND_OK);
            return sendResult;
        }
        try {
            logger.info("send MQ msg, topic={}, tag={}, message={}", topic, tag, message.toString());
            Message msg = new Message(topic, tag, HessianUtils.encode(message));
            SendResult sendResult = producer.send(msg);
            logger.info("send MQ msg result, topic={}, tag={}, message={}, sendResult={},",  topic, tag, JSON.toJSONString(message), JSON.toJSONString(sendResult));
            return sendResult;
        } catch (IOException ioe) {
            logger.error("send MQ msg IOException!, topic={}, tag={}, message={}", topic, tag, message.toString(), ioe);
            throw new RuntimeException("meta io exception   message={}" + JSON.toJSONString(message)+" error="+ioe.getMessage());
        } catch (Exception e) {
        	logger.error("send MQ msg Exception!, topic={}, tag={}, message={}", topic, tag, message.toString(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public TransactionSendResult sendMessage(Serializable msg, String topic, String tag, LocalTransactionExecuter transactionExecuter) {
        logger.info("ready to send transaction msg! topic={},tags={},message={}", topic, tag, JSON.toJSONString(msg) );
        TransactionSendResult sendResult = null;
        try {
            Message message = new Message(topic, tag, HessianUtils.encode(msg));
            //发送事务性消息
            sendResult = producer.sendMessageInTransaction(message, transactionExecuter, null);
            if (sendResult.getLocalTransactionState() == LocalTransactionState.ROLLBACK_MESSAGE) {
                logger.error("send transaction msg failed! topic={}, tags={}, message={}, sendResult={}", topic, tag, msg, JSON.toJSONString(sendResult));
                return sendResult;
            }
        } catch (Exception e) {
            logger.error("send transaction msg Exception! topic={}, tags={}, message={}, sendResult={}", topic, tag, msg, JSON.toJSONString(sendResult), e);
            return sendResult;
        }

        return sendResult;
    }


    public void destroy() {
        if (producer != null) {
            producer.shutdown();
            logger.info("metaq sender close success!; rocketmq.namesrv.domain={}", System.getProperty(DOMAIN));
        }
    }

    /**
     * Setter method for property <tt>nameServer</tt>.
     *
     * @param nameServer value to be assigned to property nameServer
     */
    public void setNameServer(String nameServer) {
        this.nameServer = nameServer;
    }
}
