package com.stu.thread.demo.thread_alternate_print;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 利用多线程 交替打印：AABBCCAABBCCAABBCCAABBCC...
 */
public class AlternatePrint {

    public static void main(String[] args) {
        PrintAction printAction = new PrintAction(2);
        for (int i = 0; i < 101; i++) {
            new Thread(()->{
                printAction.printA();
            }).start();
            new Thread(()->{
                printAction.printB();
            }).start();
            new Thread(()->{
                printAction.printC();
            }).start();
        }
    }
}


class PrintAction{

    private int value = 1;

    private int count;

    private Lock lock = new ReentrantLock();

    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    PrintAction(int value){
        this.count = value;
    }


    public void printA(){
        lock.lock();
        try {
            if (value == 1){
                for (int i = 0; i <count; i++) {
                    System.out.print("A");
                }
                value = 2;
                condition2.signal();
            }else {
                try {
                    condition1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }finally {
            lock.unlock();
        }
    }

    public void printB(){
        lock.lock();
        try{
            if (value == 2){
                for (int i = 0; i <count; i++) {
                    System.out.print("B");
                }
                value = 3;
                condition3.signal();
            }else {
                try {
                    condition2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }finally {
            lock.unlock();
        }
    }

    public void printC(){
        lock.lock();
        try{
            if (value == 3){
                for (int i = 0; i < count; i++) {
                    System.out.print("C");
                }
                value =1;
                condition1.signal();
            }else {
                try {
                    condition3.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }finally {
            lock.unlock();
        }
    }
}