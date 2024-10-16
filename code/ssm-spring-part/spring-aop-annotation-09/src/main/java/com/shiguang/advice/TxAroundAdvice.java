package com.shiguang.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created By Shiguang On 2024/9/3 19:45
 */
@Component
@Aspect
@Order(10)
public class TxAroundAdvice {
    /**
     * 环绕通知需要在通知方法中定义目标方法的执行
     * @param joinPoint 目标方法（获取目标方法信息,多了一个执行方法）
     * @return
     */


    @Around("com.shiguang.pointcut.MyPointCut.pointcut()")
    public Object transaction(ProceedingJoinPoint joinPoint){

        Object[] args = joinPoint.getArgs();
        Object result = null;
        try {
            System.out.println("开启事务");
            result = joinPoint.proceed(args);
            System.out.println("提交事务");

        } catch (Throwable e) {
            //出现异常必须抛出异常
            System.out.println("事务回滚");
            throw new RuntimeException(e);
        } finally {
            System.out.println("事务结束");
        }
        return result;
    }
}
