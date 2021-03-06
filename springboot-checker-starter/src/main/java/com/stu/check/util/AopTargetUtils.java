package com.stu.check.util;

import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.support.AopUtils;

import java.lang.reflect.Field;

/**
 * @author: CipherCui
 */
public class AopTargetUtils {

    /**
     * Get target object
     *
     * @param proxy proxy object
     * @return real object
     */
    public static Object getTarget(Object proxy) {
        if (!AopUtils.isAopProxy(proxy)) {
            return proxy;
        }
        Object obj;
        try {
            if (AopUtils.isJdkDynamicProxy(proxy)) {
                obj = getJdkDynamicProxyTargetObject(proxy);
            } else {
                obj = getCglibProxyTargetObject(proxy);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return obj == null ? proxy : obj;
    }

    /**
     * get cglib proxy target object
     *
     * @param proxy proxy object
     * @return real object
     * @throws Exception exception
     */
    private static Object getCglibProxyTargetObject(Object proxy) throws Exception {
        Field h = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");
        h.setAccessible(true);
        Object dynamicAdvisedInterceptor = h.get(proxy);
        Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
        advised.setAccessible(true);
        return ((AdvisedSupport) advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();
    }

    /**
     * get jdk dynamic proxy target object
     *
     * @param proxy proxy object
     * @return real object
     * @throws Exception exception
     */
    private static Object getJdkDynamicProxyTargetObject(Object proxy) throws Exception {
        Field h = proxy.getClass().getSuperclass().getDeclaredField("h");
        h.setAccessible(true);
        AopProxy aopProxy = (AopProxy) h.get(proxy);
        Field advised = aopProxy.getClass().getDeclaredField("advised");
        advised.setAccessible(true);
        return ((AdvisedSupport) advised.get(aopProxy)).getTargetSource().getTarget();
    }

}
