package com.stu.thread.demo.thread_pool;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 自定义线程池
 */
@Slf4j(topic = "z.ThreadPool")
public class ThreadPool {

    // 任务队列
    private final BlockingQueue<Runnable> queue;
    // 最大线程数
    private final int maxSize;
    // 核心线程数
    private final int coreSize;
    // 重试过期时间
    private final long timeOut;
    // 过期时间单位
    private final TimeUnit timeUnit;
    // 核心线程集合
    private final HashSet<Worker> workSet = new HashSet<Worker>();
    // 拒绝策略
    private CustomizeExecutionHandler customizeExecutionHandler;

    // 锁
    private final Lock lock = new ReentrantLock();


    public ThreadPool(int coreSize,int maxSize,long timeOut,TimeUnit timeUnit, BlockingQueue<Runnable> queue, CustomizeExecutionHandler customize){
        this.coreSize = coreSize;
        this.queue = queue;
        this.maxSize = maxSize;
        this.timeOut = timeOut;
        this.timeUnit = timeUnit;
        this.customizeExecutionHandler = customize;
    }


    // 执行线程
    public void execute(Runnable task){
        lock.lock();
        try {
            if (coreSize > workSet.size()){
                Worker worker  = new Worker(task);
                workSet.add(worker);
                log.debug("添加一个任务：{}",task);
                worker.start();
            }else{
                queue.put(task);
            }
        }finally {
            lock.unlock();
        }
    }

    // 线程工作者
    class Worker extends Thread {

        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            while (this.task != null || (task = queue.take())!=null){
                try {
                    log.debug("开始执行一个任务：{}",task);
                    task.run();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    task = null;
                }
            }
            synchronized (workSet){
                workSet.remove(this);
                log.debug("删除一个任务：{}",task);
            }
        }
    }

}

/**
 * 阻塞队列
 * @param <T>
 */
@Slf4j(topic = "z.BlockingQueue")
class  BlockingQueue<T>{

    private  final LinkedList<T> linkedList = new LinkedList<>();

    private final int capcity;

    private final Lock lock = new ReentrantLock();

    private final Condition readLock = lock.newCondition();

    private final Condition writeLock = lock.newCondition();


    BlockingQueue(int capcity){
        this.capcity = capcity;
    }


    public T take(){
        lock.lock();
        try {
            while (linkedList.isEmpty()){
                readLock.await();
            }
            T t = linkedList.removeLast();
            log.debug("从队列获取一个任务：{}",t);
            writeLock.signal();
            return t;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }


    public void put(T t){
        lock.lock();
        try {
            while (linkedList.size() == this.capcity){
                writeLock.await();
            }
            linkedList.addFirst(t);
            log.debug("放入队列一个人任务：{}",t);
            readLock.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public int size(){
        lock.lock();
        try {
            return linkedList.size();
        }finally {
            lock.unlock();
        }
    }

}
