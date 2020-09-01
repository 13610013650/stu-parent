package com.stu.rocketmq.test;
import com.alibaba.fastjson.JSON;
import com.stu.rocketmq.bean.Message;
import com.stu.rocketmq.component.RsaComponent;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/test")
public class MessageProducer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    private RsaComponent rsaComponent;

    @RequestMapping(value = "/queue1/{message}", method = RequestMethod.GET)
    public String testRocketMq(@PathVariable("message") String message) throws Exception {
        log.info("开始发送消息-------");
        Message<String> newMessage = new Message<String>();
        newMessage.setId(UUID.randomUUID().toString().replace("-",""));
        newMessage.setObject(message);
        String messageJson = JSON.toJSONString(newMessage);
        log.info("加密前的消息:" + messageJson);
        String jmMessage = rsaComponent.encrypt(messageJson);
        log.info("加密后的消息："+jmMessage);
        rocketMQTemplate.convertAndSend("topic-queue-one", jmMessage);
        log.info("发送消息完成-------");
        return messageJson;
    }

    @RequestMapping(value = "/queue2/{message}", method = RequestMethod.GET)
    public String testRocketMq2(@PathVariable("message") String message) throws Exception {
        log.info("222开始发送消息-------");
        Message<String> newMessage2 = new Message<String>();
        newMessage2.setId(UUID.randomUUID().toString().replace("-",""));
        newMessage2.setObject(message);
        String messageJson2 = JSON.toJSONString(newMessage2);
        String encrypt = rsaComponent.encrypt(messageJson2);
        log.info("加密后的消息2:"+encrypt);
        rocketMQTemplate.convertAndSend("topic-queue-two", encrypt);
        log.info("222发送消息完成-------");
        return messageJson2;
    }

}
