 package com.stu.design.proxy;
/**
 * @ProjectName: stu-parent 
 * @Package: com.stu.design.proxy
 * @ClassName: Client
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2020/1/13 15:26
 * @Version: 1.0
 */
public class Client {

    public static void main(String[] args) {
        HoldConcert middlemanProxy = (HoldConcert)ProxyFactory.getMiddlemanProxy(new JieLunMrZhou());
        middlemanProxy.sign();
        middlemanProxy.disseminate();
        middlemanProxy.fixUpMeeting();
        middlemanProxy.sing();
    }
}
