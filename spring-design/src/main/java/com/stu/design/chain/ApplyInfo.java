 package com.stu.design.chain;
/**
 * @ProjectName: stu-parent 
 * @Package: com.stu.design.chain
 * @ClassName: ApplyInfo
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2019/12/30 14:39
 * @Version: 1.0
 */
public abstract class ApplyInfo {

    int money;

    public abstract void setMoney(int money);

    public abstract int getMoney();

    public void getApply() {
        System.out.println("我需要的出差费用是:"+getMoney());
    }

}
