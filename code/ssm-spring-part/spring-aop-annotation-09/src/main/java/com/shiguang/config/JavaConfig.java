package com.shiguang.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created By Shiguang On 2024/9/3 15:28
 */


@ComponentScan("com.shiguang")
@EnableAspectJAutoProxy // 开启aspectj的注解支持
@Configuration
public class JavaConfig {
}
