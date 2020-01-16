 package com.stu.design.composite;
/**
 * @ProjectName: stu-parent 
 * @Package: com.stu.design.composite
 * @ClassName: Client
 * @Author: ZhangSheng
 * @Description: 组合模式client端测试类
 * @Date: 2020/1/15 10:16
 * @Version: 1.0
 */
public class Client {

    public static void main(String[] args) {
        // 一个组件的材料
        Composite branch = new Composite();
        // 叶子节点
        Leaf leaf = new Leaf();

        branch.add(leaf);

        branch.doSomething();
    }

}
