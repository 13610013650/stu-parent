 package com.stu.design.proxy;
/**
 * @ProjectName: stu-parent 
 * @Package: com.stu.design.proxy
 * @ClassName: HoldConcert
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2020/1/13 14:55
 * @Version: 1.0
 */
public interface HoldConcert {
    /**
     * @Author ZhangSheng
     * @param
     * @Description 签协议
     */
    void sign();
    /**
     * @Author ZhangSheng
     * @param
     * @Description 宣传
     */
    void disseminate();

    /**
     * @Author ZhangSheng
     * @param
     * @Description 布置会场
     */
    void fixUpMeeting();
    /**
     * @Author ZhangSheng
     * @param
     * @Description 唱歌
     */
    void sing();
}
