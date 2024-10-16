package com.shiguang.ioc_02;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created By Shiguang On 2024/9/2 15:15
 */

//@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON) // 单例 默认值
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE) // 多例 多例时不会调用销毁方法
@Component
public class JavaBean {
    // 周期方法要求:命名随意,public,void,无参

    @PostConstruct
    public void init(){
        System.out.println("JavaBean init");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("JavaBean destroy");
    }
}
