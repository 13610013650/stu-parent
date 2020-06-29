package com.stu.thread.demo.ticket_windows;


/**
 * 售票窗口
 */
public class TicketWindows {

    private int ticketNumber;

    TicketWindows(int ticketNumber){
        this.ticketNumber = ticketNumber;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    /**
     *  卖票
     * @param number：票数
     * @return： 卖出的票数
     */
    public synchronized int sell(int number) throws InterruptedException {
        Thread.sleep(Math.round(Math.random()*5));
        if (this.ticketNumber > 0 && ticketNumber >number){
            ticketNumber -= number;
            return number;
        }else {
            int emptyNumber = this.ticketNumber;
            for (int i = 0; i < emptyNumber; i++) {
                this.ticketNumber --;
            }
            return emptyNumber;
        }
    }
}
