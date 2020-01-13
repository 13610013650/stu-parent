 package com.stu.design.mediator;
/**
 * @ProjectName: stu-parent 
 * @Package: com.stu.design.mediator
 * @ClassName: SmartMediator
 * @Author: ZhangSheng
 * @Description: 中介者
 * @Date: 2019/12/30 9:55
 * @Version: 1.0
 */
public interface SmartMediator {

    /**
     * @Author ZhangSheng
     * @param
     * @Description 音乐的操作
     */
    void music(String instruction);

    /**
     * @Author ZhangSheng
     * @param 
     * @Description 窗帘的操作
     */
    void curtain(String instruction);
    /**
     * @Author ZhangSheng
     * @param instruction
     * @Description 洗浴
     */
    void bath(String instruction);


}
