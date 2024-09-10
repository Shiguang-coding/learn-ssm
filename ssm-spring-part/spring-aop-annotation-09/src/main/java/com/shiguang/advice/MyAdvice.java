package com.shiguang.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Modifier;

/**
 * Created By Shiguang On 2024/9/3 16:20
 *
 * 1.定义增强方法
 * 2.使用注解指定对应的位置
 * 3.配置切点表达式选中方法
 * 4.切面和ioc配置
 * 5.开启aspectj注解支持
 *
 * TODO  增强方法中获取目标方法信息
 *       1. 全部增强方法中,获取目标方法的信息(方法名,参数,访问修饰符,所属类信息...)
 *          增强方法添加形参 (JoinPoint joinPoint) org.aspectj.lang.JoinPoint
 *       2. 返回的结果（@AfterReturning）
 *          增强方法添加形参 (Object result)
 *          @AfterReturning 注解中添加 returning = "形参名"
 *       3. 异常信息 (@AfterThrowing)
 *          增强方法添加形参 (Throwable throwable)
 *          @AfterReturning 注解中添加 throwing = "形参名"
 */

@Aspect
@Component
@EnableAspectJAutoProxy
public class MyAdvice {

    @Before("execution(* com..impl.*.*(..))")
    public void before(JoinPoint joinPoint){
        // 1. 获取方法所属的类信息
        String simpleName = joinPoint.getTarget().getClass().getSimpleName();

        // 2. 获取方法信息
        //   获取方法名
        String methodName = joinPoint.getSignature().getName();
        //  获取方法访问修饰符
        int modifiers = joinPoint.getSignature().getModifiers();
        String s = Modifier.toString(modifiers);

        // 3. 获取参数列表
        Object[] args = joinPoint.getArgs();

    }

    @AfterReturning(value = "execution(* com..impl.*.*(..))",returning = "result")
    public void afterReturning(JoinPoint joinPoint,Object result){

    }


    @After("execution(* com..impl.*.*(..))")
    public void after(JoinPoint joinPoint){

    }


    @AfterThrowing(value = "execution(* com..impl.*.*(..))",throwing = "throwable")
    public void afterThrowing(JoinPoint joinPoint,Throwable throwable){

    }
}
