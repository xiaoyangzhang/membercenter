package com.yimayhd.membercenter.mq;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.yimayhd.membercenter.util.HessianUtils;

/**
 *
 * @author wuzhengfei
 *
 */
public abstract class BaseConsumer implements MessageListenerConcurrently {

    private final static Logger logger = LoggerFactory.getLogger(BaseConsumer.class);
    protected DefaultMQPushConsumer consumer;
    protected String nameServer;
    protected int minConsumeThread = 2;
    protected int maxConsumeThread = 5;
    protected String group;
    
    private static final String DOMAIN = "rocketmq.namesrv.domain";
    // 定时消息相关
    // 线上环境：messageDelayLevel=1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 40m 50m 1h 2h 6h
    // 开发环境：messageDelayLevel=1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 40m 50m 1h 2h 6h 12h 1d
    private final static int[] DELAY_LEVELS = new int[]{3, 5, 9, 14, 15, 16, 17, 18, 19, 20, 21};
    protected int maxRetryCount = 10;


    public void init() throws Exception {
        if ("localTest".equals(nameServer)) {
            return;
        }
        if (StringUtils.isBlank(System.getProperty(DOMAIN))) {
            System.setProperty(DOMAIN, nameServer);
        }
        consumer = new DefaultMQPushConsumer(getGroup());
        consumer.setNamesrvAddr(nameServer);
        consumer.setConsumeThreadMin(minConsumeThread);
        consumer.setConsumeThreadMax(maxConsumeThread);
        //可以不设置 设置后可以起多个 消费端
        consumer.setInstanceName(getInstanceName());
        //设置订阅的topic 设置订阅过滤表达式
        consumer.subscribe(getTopic(), getTags());
        try {
            consumer.registerMessageListener(this);
            consumer.start();
        } catch (MQClientException e) {
            logger.error("consumer start error!group={}", group, e);
        }
        logger.info("consumer start! group={}", getGroup());
    }

    public void destroy() {
        if (consumer != null) {
            consumer.shutdown();
            logger.info("consumer shutdown! group={}", group);
        }
    }

    /**
     * 基类实现消息监听接口，加上打印metaq监控日志的方法
     */
    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                    ConsumeConcurrentlyContext context) {
        long startTime = System.currentTimeMillis();
        logger.info("receive_message:{}", msgs);
        if (msgs == null || msgs.size() < 1) {
            logger.error("receive empty msg!");
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        final int reconsumeTimes = msgs.get(0).getReconsumeTimes();
        if (reconsumeTimes >= maxRetryCount) {
            logger.error("reconsumeTimes >" + maxRetryCount + "msgs:" + msgs + "context:" + context);
        }
        context.setDelayLevelWhenNextConsume(getDelayLevelWhenNextConsume(reconsumeTimes));
        boolean consumeSuccess = true;
        for (MessageExt message : msgs) {
            if(!doConsumeMessage(decodeMsg(message))) {
                consumeSuccess = false;
            }
        }
        ConsumeConcurrentlyStatus status = ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        if (!consumeSuccess) {
            status = ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }
        long cost= System.currentTimeMillis() - startTime ;
        logger.info("ConsumeConcurrentlyStatus:{}|cost:{}", status, cost);
        return status;
    }

    /**
     * 根据重试次数设置重新消费延迟时间
     * 1s 10s 30s 2m 10m 30m 1h 2h 12h 1d
     *
     * @param reconsumeTimes 重试的次数
     * @return level级别
     */
    public int getDelayLevelWhenNextConsume(int reconsumeTimes) {
        if (reconsumeTimes >= DELAY_LEVELS.length) {
            return DELAY_LEVELS[DELAY_LEVELS.length - 1];
        }
        return DELAY_LEVELS[reconsumeTimes];
    }

    private Serializable decodeMsg(MessageExt msg) {
        if (msg == null) {
            return null;
        }
        //1.反序列化
        try {
            return HessianUtils.decode(msg.getBody());
        } catch (IOException e) {
            logger.error("IOException!  msg=" + e.getMessage(), e);
            return null;
        }
    }


    public void setNameServer(String nameServer) {
        this.nameServer = nameServer;
    }

    public void setMinConsumeThread(int minConsumeThread) {
        this.minConsumeThread = minConsumeThread;
    }

    public void setMaxConsumeThread(int maxConsumeThread) {
        this.maxConsumeThread = maxConsumeThread;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getGroup() {
    	return group + "_" + getTopic() + "_" + getTags();
    }

    public abstract String getTopic();

    public abstract String getTags();

    public abstract boolean doConsumeMessage(Serializable message);

    public String getInstanceName() {
        return "MemberBaseConsumer" + "_" + getTopic() + "_" + getTags();
    }
}
