 package com.stu.design.chain;
/**
 * @ProjectName: stu-parent 
 * @Package: com.stu.design.chain
 * @ClassName: Person
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2019/12/30 14:55
 * @Version: 1.0
 */
public class Person extends ApplyInfo {

    @Override
    public void setMoney(int money) {
        super.money = money;
    }

    @Override
    public int getMoney() {
        return super.money;
    }

}
