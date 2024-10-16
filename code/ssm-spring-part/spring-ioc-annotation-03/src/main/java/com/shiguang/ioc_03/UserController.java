package com.shiguang.ioc_03;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

/**
 * Created By Shiguang On 2024/9/2 15:41
 */

@Controller
public class UserController {
    // @Autowired 注解相当于 <property id = "userService" class = "com.xxx.xxx.UserController"/>
    // 自动装配注解(DI) : 1. ioc容器中查找符合类型的组件对象 2. 设置值给当前属性
//    @Autowired(required = false) 佛系装配(不推荐),可以没有该类型的对象,ioc装配过程中不会报错,但是后期调用时会出现空指针


//    @Qualifier("userServiceImpl")
//    @Autowired

    // @Autowired(required=true) + @Qualifier(value = "userServiceImpl") = @Resource(name = "userServiceImpl")
    @Resource(name = "userServiceImpl")
    private UserService userService;

    public void show(){
        userService.show();
    }
}
