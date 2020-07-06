package com.stu.thread.demo.thread_threadLocal;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;

public class TestThreadLocal {


    private static ThreadLocal threadLocal = new ThreadLocal();

    public static void main(String[] args) {


        CountDownLatch countDownLatch = new CountDownLatch(10);

        threadLocal.set("xxxx");
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                String uuid = UUID.randomUUID().toString();
                System.out.println("生成的UUID："+uuid);
                threadLocal.set(uuid);
                System.out.println("线程内部存入的uuid:"+uuid);
                countDownLatch.countDown();
            }).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(threadLocal.get());
    }
}
