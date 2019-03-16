package com.netease.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author Muyuxi
 * @Date 2019/2/26
 * @Describtion 系统执行日志切面类
 */

@Aspect
@Component
public class  SystemLogAspect{

    private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);

    //切入点表达式
    @Pointcut("execution(* com.netease.controller..*(..))")
    public void controllerAspect() {

    }

    //前置通知
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("执行controller方法的前置通知");
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        if(logger.isInfoEnabled())
            logger.info("在 {} 中开始执行 {}() 方法",targetName,methodName);
    }



    @After("controllerAspect()")
    public void after(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        if(logger.isInfoEnabled())
            logger.info("{}() 方法处理请求完成",methodName);
    }



}
