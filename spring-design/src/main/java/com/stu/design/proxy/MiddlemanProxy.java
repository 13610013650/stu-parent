 package com.stu.design.proxy;

 import java.lang.reflect.InvocationHandler;
 import java.lang.reflect.Method;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.stu.design.proxy
 * @ClassName: ProxyMiddleman
 * @Author: ZhangSheng
 * @Description: 动态代理类
 * @Date: 2020/1/13 15:04
 * @Version: 1.0
 */
public class MiddlemanProxy implements InvocationHandler{

    /**
     * @Author ZhangSheng
     * @param
     * @Description 被代理的目标对象
     */
    private Object target;

    public MiddlemanProxy(Object object){
        this.target = object;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (method.getName().equals("sign")){
            System.out.println("经纪人签署协议...");
        }else if(method.getName().equals("disseminate")){
            System.out.println("经纪人做宣传...");
        }else if (method.getName().equals("fixUpMeeting")){
            System.out.println("经纪人布置会场...");
        }else {
            return method.invoke(target,args);
        }
        return null;
    }
 }
