package com.stu.thread.demo.ticket_windows;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 模拟多个购票员去窗口买票的过程。
 */
public class Client {


    public static void main(String[] args) throws InterruptedException {
        TicketWindows ticketWindows = new TicketWindows(1000);
        List<Integer> values = new Vector<>();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 100; i++) {

            Thread thread = new Thread(() -> {
                int value = 0;
                try {
                    value = ticketWindows.sell(getGeneralNumber(5));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                values.add(value);
            });
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println("余票数："+ticketWindows.getTicketNumber());
        System.out.println("卖出的票数："+values.stream().mapToInt(i-> i).reduce(Integer::sum).getAsInt());

    }

    public static int getGeneralNumber(int scope){
        return (int)Math.round(Math.random() * 5);
    }
}
