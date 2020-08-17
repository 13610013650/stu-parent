package com.stu.rocketmq.test;


import com.stu.rocketmq.component.RsaComponent;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(topic = "topic-queue-two", consumerGroup = "consumer_topic-queue-two")
public class MessageConsumerTwo implements RocketMQListener<String> {

    @Autowired
    private RsaComponent rsaComponent;

    @Override
    public void onMessage(String message) {
        String decrypt = null;
        try {
            decrypt = rsaComponent.decrypt(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("consumer_topic-queue-two 解密后消费的消息：" + decrypt);
    }

}
