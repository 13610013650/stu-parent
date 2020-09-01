package com.stu.rocketmq.config;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 没有引入 spring-boot-starter.jar包 不去手动通过 @Value 来获取配置文件里的值
 * 会抛出 producer 空指针异常，producer 会没有自动注入
 * 所以要在 rocketMQTemplate() 方法中new出来DefaultMQProducer()
 */
@Configuration
public class RocketMQConfig {

    @Value("${rocketmq.producer.send-message-timeout:3000}")
    private int sendMessageTimeout;
    @Value("${rocketmq.producer.compress-message-body-threshold:4096}")
    private int compressMessageBodyThreshold;
    @Value("${rocketmq.producer.max-message-size:4194304}")
    private int maxMessageSize;
    @Value("${rocketmq.producer.retry-times-when-send-async-failed:0}")
    private int retryTimesWhenSendAsyncFailed;
    @Value("${rocketmq.producer.retry-next-server:true}")
    private boolean retryNextServer;
    @Value("${rocketmq.producer.retry-times-when-send-failed:2}")
    private int retryTimesWhenSendFailed;
    @Value("${rocketmq.producer.group:rocketmq-group}")
    private String group;
    @Value("${rocketmq.name-server:localhost:9876}")
    private String nameServer;



    @Bean
    public RocketMQTemplate rocketMQTemplate() {
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer();
        defaultMQProducer.setSendMsgTimeout(this.sendMessageTimeout);
        defaultMQProducer.setCompressMsgBodyOverHowmuch(this.compressMessageBodyThreshold);
        defaultMQProducer.setMaxMessageSize(this.maxMessageSize);
        defaultMQProducer.setRetryTimesWhenSendAsyncFailed(this.retryTimesWhenSendAsyncFailed);
        defaultMQProducer.setRetryAnotherBrokerWhenNotStoreOK(this.retryNextServer);
        defaultMQProducer.setRetryTimesWhenSendFailed(this.retryTimesWhenSendFailed);
        defaultMQProducer.setProducerGroup(this.group);
        defaultMQProducer.setNamesrvAddr(this.nameServer);
        RocketMQTemplate rocketMQTemplate = new RocketMQTemplate();
        rocketMQTemplate.setProducer(defaultMQProducer);
        return rocketMQTemplate;
    }


}
