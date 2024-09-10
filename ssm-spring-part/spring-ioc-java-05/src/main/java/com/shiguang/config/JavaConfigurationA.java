package com.shiguang.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created By Shiguang On 2024/9/3 11:59
 */

@Configuration
@Import(JavaConfigurationB.class)
public class JavaConfigurationA {
}
