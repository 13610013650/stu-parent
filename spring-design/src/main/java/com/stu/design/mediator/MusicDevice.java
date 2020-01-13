 package com.stu.design.mediator;

 import com.stu.design.mediator.constant.CommandState;
 import org.springframework.stereotype.Component;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.stu.design.mediator
 * @ClassName: MusicDevice
 * @Author: ZhangSheng
 * @Description: 具体同事类（音响设备）
 * @Date: 2019/12/30 10:10
 * @Version: 1.0
 */
@Component
public class MusicDevice implements SmartDevice{

    @Override
    public void readyState(String instruction) {
        if (instruction.equals(CommandState.OPEN)){
            System.out.println("音乐设备准备开启...");
        }else if(instruction.equals(CommandState.CLOSE)){
            System.out.println("音乐设备准备关闭...");
        }
    }

    @Override
    public void operateDevice(String instruction, SmartMediator mediator) {
        if (instruction.equals(CommandState.OPEN)){
            System.out.println("音乐设备已开启...");
        }else if(instruction.equals(CommandState.CLOSE)){
            System.out.println("音乐设备已关闭...");
        }
        mediator.music(instruction);
    }

}
