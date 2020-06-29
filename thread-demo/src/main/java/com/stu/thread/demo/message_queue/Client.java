package com.stu.thread.demo.message_queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Client {

    public static void main(String[] args) throws IOException, InterruptedException {
        MessageQueue queue = new MessageQueue();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                queue.add(new Message("消息"+i));
            }
        },"s1").start();

        for (int i = 0; i < 3; i++) {
            new Thread(()->{
            while (true) {
                        queue.take();
            }
            },"x"+i).start();
        }
        Thread.sleep(3000);
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            System.out.println("请输入一个消息：");
            String message = bf.readLine();
            Message ms = new Message(message);
            queue.add(ms);
        }

    }
}
