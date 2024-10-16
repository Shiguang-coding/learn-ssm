package com.shiguang.ioc_04;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created By Shiguang On 2024/9/2 17:28
 */

@Component
public class JavaBean {


    /**
     * 方案一: 直接赋值 name = "张三"
     * 方案二: @Value 赋值,通常用于读取外部配置文件
     */

    private String name = "時光";

    @Value("18")
    private int age;

    @Value("${jdbc.driver:com.mysql.cj.jdbc.Driver}")
    private String driver;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Override
    public String toString() {
        return "JavaBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", driver='" + driver + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
