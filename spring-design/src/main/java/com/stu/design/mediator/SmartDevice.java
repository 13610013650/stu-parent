 package com.stu.design.mediator;
/**
 * @ProjectName: stu-parent 
 * @Package: com.stu.design.mediator
 * @ClassName: SmartDevice
 * @Author: ZhangSheng
 * @Description: 同事类接口(智能设备)
 * @Date: 2019/12/30 9:53
 * @Version: 1.0
 */
public interface SmartDevice {

    /**相关设备打开之后 使其进入准备状态*/
    void readyState(String instruction);
    /**操作该设备*/
    void operateDevice(String instruction, SmartMediator mediator);

}
