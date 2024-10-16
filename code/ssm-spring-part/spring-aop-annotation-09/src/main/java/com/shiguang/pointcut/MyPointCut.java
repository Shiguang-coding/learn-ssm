package com.shiguang.pointcut;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created By Shiguang On 2024/9/3 19:35
 */

@Component
public class MyPointCut {
    @Pointcut("execution(* com.shiguang.service.impl.*.*(..))")
    public void pointcut(){}
}
