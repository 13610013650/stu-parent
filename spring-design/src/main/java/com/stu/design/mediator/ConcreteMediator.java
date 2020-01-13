 package com.stu.design.mediator;

 import com.stu.design.mediator.constant.CommandState;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;

 import java.util.HashMap;
 import java.util.Map;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.stu.design.mediator
 * @ClassName: ConcreteMediator
 * @Author: ZhangSheng
 * @Description: 具体的中介者
 * @Date: 2019/12/30 10:17
 * @Version: 1.0
 */
@Service
public class ConcreteMediator implements SmartMediator{

    @Autowired
    private Map<String,SmartDevice> smartDeviceMap = new HashMap<>();


    /**
     * @Author ZhangSheng
     * @param instruction
     * @Description  音乐响起
     */
    @Override
    public void music(String instruction) {
        smartDeviceMap.get(CommandState.ClassType.MUSIC).readyState(instruction);
        smartDeviceMap.get(CommandState.ClassType.MUSIC).operateDevice(instruction,this);
    }

    @Override
    public void curtain(String instruction) {
        smartDeviceMap.get(CommandState.ClassType.CURTAIN).readyState(instruction);
        smartDeviceMap.get(CommandState.ClassType.CURTAIN).operateDevice(instruction,this);
    }

     @Override
     public void bath(String instruction) {
         smartDeviceMap.get(CommandState.ClassType.BATH).readyState(instruction);
         smartDeviceMap.get(CommandState.ClassType.BATH).operateDevice(instruction,this);
     }

}
