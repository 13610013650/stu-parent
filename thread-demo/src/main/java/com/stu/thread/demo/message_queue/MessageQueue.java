package com.stu.thread.demo.message_queue;

import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.UUID;


/**
 * 自定义实现消息队列
 */
@Slf4j
public class MessageQueue {

    private static int MAX_NUMBER = 10;

    private static final Object LOCK = new Object();

    private LinkedList<Message> queue = new LinkedList();

    /**
     * 往队列添加消息
     * @param message： 消息对象
     */
    public void add(Message message) {
        synchronized (LOCK) {
            while (queue.size() == MAX_NUMBER) {
                log.debug("队列满了");
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.addLast(message);
            log.debug("添加一个消息：{}",message.toString());
            LOCK.notifyAll();
        }
    }

    /**
     * 获取消息队列的消息，无消息时阻塞。
     * @return
     */
    public Message take() {
        synchronized (LOCK) {
            while (queue.isEmpty()) {
                log.debug("队列无消息可消费.");
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Message message = queue.removeFirst();
            log.debug("消费一个消息：{}", message.toString());
            LOCK.notifyAll();
            return message;
        }
    }




}
