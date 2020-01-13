 package com.stu.aspct.aspect;

 import com.stu.aspct.annotation.Log;
 import org.aspectj.lang.JoinPoint;
 import org.aspectj.lang.ProceedingJoinPoint;
 import org.aspectj.lang.Signature;
 import org.aspectj.lang.annotation.After;
 import org.aspectj.lang.annotation.Aspect;
 import org.springframework.stereotype.Component;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.stu.aspct.aspect
 * @ClassName: LogAnnotationAspect
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2020/1/13 14:23
 * @Version: 1.0
 */
@Aspect
@Component
public class LogAnnotationAspect {

    @After("@annotation(log)")
    public void after(JoinPoint joinPoint, Log log) throws Throwable {
        String param = log.param();
        System.out.println("=====param=====>"+param);
        Signature signature = joinPoint.getSignature();
        System.out.println(signature.getDeclaringType());
        System.out.println(signature.getName());
        System.out.println(signature.getModifiers());
        System.out.println("@annotation aspect method after().... ");
    }

}
