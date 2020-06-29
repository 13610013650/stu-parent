package com.stu.thread.demo.thread_pool;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j(topic = "c.Client")
public class Client {


    public static void main(String[] args) {

        CustomizeExecutionHandler customize = new CustomizeExecution();

        ThreadPoolExecutor javaThreadPool = new ThreadPoolExecutor(1, 2,
                18000L, TimeUnit.SECONDS,
                 new LinkedBlockingQueue<>(2), new ThreadPoolExecutor.CallerRunsPolicy());

        ThreadPool threadPool = new ThreadPool(2,4,1L,TimeUnit.SECONDS,new BlockingQueue<>(3),customize);
        for (int i = 0; i < 9; i++) {
            int j  = i;
            Task task = new Task(new Integer(i).toString());
            threadPool.execute(task);
        }

    }

    /**
     *
     */
    public static class CustomizeExecution implements CustomizeExecutionHandler{

        @Override
        public void rejectedExection(Runnable task, ThreadPool exectPool) {
            Task r1 = (Task) task;
            log.debug("线程被拒绝："+r1.getName()+","+r1.getClass());
        }
    }

     static class Task implements Runnable{

       private String name;

       public Task(String name){
           this.name = name;
       }

         public String getName() {
             return name;
         }

         @Override
         public void run() {
             try {
                 Thread.sleep(3000L);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
             System.out.println(name);
         }
     }
}
