package com.sky.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//自定义切面, 实现自动填充字段
@Aspect
@Component
@Slf4j
public class AutoFillAspect {

    //切入点:
    @Pointcut("execution(* com.sky.mapper.*.*(..)) && @annotation(com.sky.annotation.Autofill)")
    public void autoFillPointCut(){
    }

    //前置通知
    @Before("autoFillPointCut()")
    //参数:连接点
    public void autoFill(JoinPoint joinPoint){
        log.info("开始自动填充公共字段");



    }





}
