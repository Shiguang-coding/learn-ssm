package com.shiguang.advice;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created By Shiguang On 2024/9/3 19:41
 * description: 使用普通方式进行事务的添加
 */
//@Component
//@Aspect
public class TxAdvice {


    @Before("com.shiguang.pointcut.MyPointCut.pointcut()")
    public void begin(){
        System.out.println("开启事务");
    }

    @AfterReturning("com.shiguang.pointcut.MyPointCut.pointcut()")
    public void commit(){
        System.out.println("事务提交");
    }

    @AfterThrowing("com.shiguang.pointcut.MyPointCut.pointcut()")
    public void rollback(){
        System.out.println("事务回滚");
    }
}
