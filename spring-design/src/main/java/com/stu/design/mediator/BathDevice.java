 package com.stu.design.mediator;

 import com.stu.design.mediator.constant.CommandState;
 import org.springframework.stereotype.Component;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.stu.design.mediator
 * @ClassName: BathDevice
 * @Author: ZhangSheng
 * @Description: 具体同事类（洗浴设备）
 * @Date: 2019/12/30 10:11
 * @Version: 1.0
 */
@Component
public class BathDevice implements SmartDevice{

    @Override
    public void readyState(String instruction) {
        if (instruction.equals(CommandState.OPEN)){
            System.out.println("洗浴设备正在打开...");
        }else if (instruction.equals(CommandState.CLOSE)){
            System.out.println("洗浴设备正在关闭...");
        }
    }

    @Override
    public void operateDevice(String instruction, SmartMediator mediator) {
        if (instruction.equals(CommandState.OPEN)){
            System.out.println("洗浴设备已打开...");
        }else if (instruction.equals(CommandState.CLOSE)){
            System.out.println("洗浴设备已关闭...");
        }
        mediator.bath(instruction);
    }

}
