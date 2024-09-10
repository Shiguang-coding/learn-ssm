package com.shiguang.advice;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

/**
 * Created By Shiguang On 2024/9/3 21:20
 */
@Component

public class TxAdvice {

    public void begin(){
        System.out.println("开启事务");
    }


    public void commit(JoinPoint joinPoint, Object result){
        System.out.println("事务提交");
    }


    public void rollback(JoinPoint joinPoint,Throwable e){
        System.out.println("事务回滚");
    }
}
