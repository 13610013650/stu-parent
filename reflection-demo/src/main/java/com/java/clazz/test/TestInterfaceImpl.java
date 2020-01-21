package com.java.clazz.test;

import com.java.clazz.annotation.Test;

/**
 * @ProjectName: stu-parent 
 * @Package: com.java.clazz.test
 * @ClassName: TestInterfaceImpl
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2020/1/18 20:55
 * @Version: 1.0
 */
public class TestInterfaceImpl implements TestInterface {

    @Override
    @Test(value = "sayHello()")
    public void sayHello() {
        System.out.println("TestInterfaceImpl say hello java reflect......");
    }
}
