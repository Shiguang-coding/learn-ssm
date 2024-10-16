package com.shiguang.statics;

import com.shiguang.Calculator;

/**
 * Created By Shiguang On 2024/9/3 13:42
 */
public class StaticProxyCalculator implements Calculator {

    private Calculator calculator;

    public StaticProxyCalculator(Calculator target) {
        this.calculator = target;
    }

    @Override
    public int add(int i, int j) {
        System.out.println("i = " + i + ", j = " + j);
        int result = calculator.add(i, j);
        System.out.println("result = " + result);
        return result;
    }

    @Override
    public int sub(int i, int j) {
        return 0;
    }

    @Override
    public int mul(int i, int j) {
        return 0;
    }

    @Override
    public int div(int i, int j) {
        return 0;
    }
}
