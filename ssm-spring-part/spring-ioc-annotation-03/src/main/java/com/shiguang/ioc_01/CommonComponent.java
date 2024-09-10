package com.shiguang.ioc_01;

import org.springframework.stereotype.Component;

/**
 * description: 普通的组件
 * 1. 标记注解 @Component
 * 2. 配置指定要扫描的包
 */

//@Component(value = "commonComponent") // 可以使用 value = "xx" 指定组件id名称
//@Component("commonComponent") // 属性名可以省略不写, 直接写value属性值即可
@Component  //相当于 <bean id = "当前类的首字母小写" class = "当前类"
public class CommonComponent {
}