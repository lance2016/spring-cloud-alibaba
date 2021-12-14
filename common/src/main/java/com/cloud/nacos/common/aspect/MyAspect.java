package com.cloud.nacos.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @program: springcloud  MyAspect
 * @description:
 * @author: flchen
 * @create: 2021-05-06 16:10
 **/

@Aspect
@Component
public class MyAspect {
    //定义切入点表达式
    @Pointcut("execution(* com.cloud.nacos.common.aspect.*.*(..))")
    //定义一个返回值为void、方法体为空的方法来命名切入点
    private void myPointCut(){

    }
    @Before("myPointCut()")
    public void myBefore(JoinPoint joinpoint){
        System.out.println("前置通知："+joinpoint);
        System.out.println("被植入增强处理的目标方法为："+joinpoint.getSignature().getName());
    }
    @AfterReturning("myPointCut()")
    public void myAfterReturning(JoinPoint joinpoint){
        System.out.println("后置通知："+joinpoint);
        System.out.println(",被植入增强处理的目标方法为："+joinpoint.getSignature().getName());
    }
    @Around("myPointCut()")
    public Object myAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        System.out.println("环绕开始。。。");
        Object obj = proceedingJoinPoint.proceed();
        System.out.println("环绕结束。。。");
        return obj;
    }
    @AfterThrowing(value="myPointCut()",throwing="e")
    public void myAfterThrowing(JoinPoint joinpoint,Throwable e){
        System.out.println("异常通知："+e.getMessage());
    }
    @After("myPointCut()")
    public void myAfter(){
        System.out.println("最终通知");
    }

}
