 package com.stu.design.mediator;

 import com.stu.design.mediator.constant.CommandState;
 import org.springframework.stereotype.Component;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.stu.design.mediator
 * @ClassName: CurtainDevice
 * @Author: ZhangSheng
 * @Description: 具体同事类（窗帘设备）
 * @Date: 2019/12/30 10:05
 * @Version: 1.0
 */
@Component
public class CurtainDevice implements SmartDevice{

    /**
     * @Author ZhangSheng
     * @param
     * @Description 准备状态
     */
    @Override
    public void readyState(String instruction) {
        if (instruction.equals(CommandState.OPEN)) {
            System.out.println("窗帘正在准备拉开...");
        }else if(instruction.equals(CommandState.CLOSE)){
            System.out.println("窗帘正在准备拉下...");
        }
    }

    /**
     * @Author ZhangSheng
     * @param
     * @Description 操作设备
     */
    @Override
    public void operateDevice(String instruction, SmartMediator mediator) {
        if (instruction.equals(CommandState.OPEN)){
            System.out.println("窗帘已拉开...");
        }else if (instruction.equals(CommandState.CLOSE)){
            System.out.println("窗帘已拉下...");
        }
        mediator.curtain(instruction);
    }

}
