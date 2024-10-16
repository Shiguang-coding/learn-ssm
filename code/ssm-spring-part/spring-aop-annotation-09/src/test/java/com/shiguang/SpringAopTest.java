package com.shiguang;

import com.shiguang.config.JavaConfig;
import com.shiguang.service.Calculator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * Created By Shiguang On 2024/9/3 15:29
 */

@SpringJUnitConfig(value = JavaConfig.class)
public class SpringAopTest {

    @Autowired
    private Calculator calculator;

    @Test
    public void test(){
        int result = calculator.add(1, 0);
        System.out.println("result = " + result);
    }
}
