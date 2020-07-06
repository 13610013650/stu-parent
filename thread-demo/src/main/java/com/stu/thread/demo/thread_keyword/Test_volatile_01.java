package com.stu.thread.demo.thread_keyword;


import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试多线程 volatile 关键字
 */
public class Test_volatile_01 {

    /**
     * volatile：保证内存可见性,不保证原子性;
     * 底层原理：
     *      1、CAS算法: compare-and-swap 比较并替换;
     *      2、调用Unsafe类的compareAndSwap方法;
     */
    private volatile static boolean FLAG = true;

    private static AtomicInteger integer = new AtomicInteger(0);

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
        integer.compareAndSet(0,1);
    }
}
