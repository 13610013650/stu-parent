package com.stu.thread.demo;


import java.util.concurrent.CountDownLatch;

public class Test_synchronized_02 {


    private static int number = 0;

    /**
     * synchronized 确保线程安全
     */
    private static synchronized void increament() {

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        number++;
    }


    public static void main(String[] args) throws InterruptedException {

        int threadNumber = 10;

        CountDownLatch countDownLatch = new CountDownLatch(threadNumber);

        for (int i = 0; i < threadNumber; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    increament();
                }
                countDownLatch.countDown();
            }, "thread" + i);
            thread.start();
        }
        countDownLatch.await();
//        Thread.sleep(3000);
        System.out.println(number);

    }


}
