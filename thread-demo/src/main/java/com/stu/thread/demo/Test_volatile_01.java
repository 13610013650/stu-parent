package com.stu.thread.demo;


import java.io.IOException;

/**
 * 测试多线程
 */
public class Test_volatile_01 {

    private volatile static boolean FLAG = true;

    public static void main(String[] args) throws IOException, InterruptedException {
        new Thread(() -> {
            System.out.println("线程1等待数据。");
            while (FLAG) {
            }
            System.out.println("线程1执行线程结束。");
        }, "thread1").start();
        Thread.sleep(100);
        new Thread(() -> {
            System.out.println("线程2:开始修改数据。");
            FLAG = false;
            System.out.println("线程2:修改数据结束。");
        }, "thread2").start();
    }
}
