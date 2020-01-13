 package com.stu.aspct.aspect;

 import org.aspectj.lang.JoinPoint;
 import org.aspectj.lang.ProceedingJoinPoint;
 import org.aspectj.lang.annotation.*;
 import org.springframework.stereotype.Component;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.stu.aspct.aspect
 * @ClassName: TestAspect
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2020/1/13 13:55
 * @Version: 1.0
 */
@Aspect
@Component
public class LogAspect {

    @Pointcut("execution(public * com.stu.aspct.controller.*.*(..))")
    public void LogAspect(){}

    @Before("LogAspect()")
    public void doBefore(JoinPoint joinPoint){
        System.out.println("doBefore...");
    }

    @After("LogAspect()")
    public void doAfter(JoinPoint joinPoint){
        System.out.println("doAfter...");
    }

    @AfterReturning("LogAspect()")
    public void doAfterReturning(JoinPoint joinPoint){
        System.out.println("doAfterReturning...");
    }

    @AfterThrowing("LogAspect()")
    public void doAfterThrowing(JoinPoint joinPoint){
        System.out.println("doAfterThrowing...");
    }

     @Around("LogAspect()")
     public Object deAround(ProceedingJoinPoint joinPoint) throws Throwable{
         System.out.println("deAround...");
         return joinPoint.proceed();
     }
}
