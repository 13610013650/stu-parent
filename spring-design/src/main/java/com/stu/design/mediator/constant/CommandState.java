 package com.stu.design.mediator.constant;
/**
 * @ProjectName: stu-parent 
 * @Package: com.stu.design.mediator.constant
 * @ClassName: CommandState
 * @Author: ZhangSheng
 * @Description: 常量
 * @Date: 2019/12/30 10:31
 * @Version: 1.0
 */
public class CommandState {

    /**关闭*/
    public static final String OPEN = "open";

    /**开启*/
    public static final String CLOSE = "close";

    /** 类名常量*/
    public static class ClassType{
        /** 洗浴*/
        public static final String BATH = "bathDevice";
        /** 音乐*/
        public static final String MUSIC = "musicDevice";
        /** 窗帘*/
        public static final String CURTAIN = "curtainDevice";
    }
}
