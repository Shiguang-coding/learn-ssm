package com.shiguang.ioc_04;

/**
 * Created By Shiguang On 2024/9/1 16:57
 */
public class JavaBean {

    /**
     * 周期方法要求： 方法命名随意，但是要求方法必须是 public void 无形参列表
     */

    /**
     * 初始化方法
     */
    public void init(){
        System.out.println("JavaBean.init");
    }

    /**
     * 销毁方法
     */

    public void clear(){
        System.out.println("JavaBean.clear");
    }
}
