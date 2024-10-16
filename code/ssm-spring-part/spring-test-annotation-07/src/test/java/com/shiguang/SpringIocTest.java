package com.shiguang;

import com.shiguang.components.A;
import com.shiguang.components.B;
import com.shiguang.config.JavaConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * Created By Shiguang On 2024/9/3 12:30
 */

// @SpringJUnitConfig locations 指定配置文件xml,value 指定配置类
@SpringJUnitConfig(value = JavaConfig.class)
public class SpringIocTest {

    @Autowired
    private A a;
    @Autowired
    private B b;



    @Test
    public void test() {
        System.out.println("A:" + a);

    }
}
