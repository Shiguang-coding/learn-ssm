package com.shiguang;

import com.shiguang.dynamic.ProxyFactory;
import com.shiguang.statics.StaticProxyCalculator;

/**
 * Created By Shiguang On 2024/9/3 13:47
 */
public class UseAop {
    public static void main(String[] args) {

        // 目标
        Calculator calculator = new CalculatorPureImpl();

        // 代理
        Calculator proxy = new StaticProxyCalculator(calculator);

        //调用
        int result = proxy.add(1, 2);



        //jdk代理
        ProxyFactory factory = new ProxyFactory(calculator);
        // 使用接口接值 -> 代理对象
        Calculator jdkProxy = (Calculator) factory.getProxy();
        jdkProxy.add(1,1);

    }
}
