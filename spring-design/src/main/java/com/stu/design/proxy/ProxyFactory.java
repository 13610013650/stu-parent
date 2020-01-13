 package com.stu.design.proxy;

 import java.lang.reflect.InvocationHandler;
 import java.lang.reflect.Proxy;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.stu.design.proxy
 * @ClassName: ProxyFactory
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2020/1/13 15:13
 * @Version: 1.0
 */
public class ProxyFactory {

    public static Object getMiddlemanProxy(Object target) {
        InvocationHandler handler = new MiddlemanProxy(target);
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),handler);
    }

}
