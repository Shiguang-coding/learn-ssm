package com.atguigu;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.springframework.web.WebApplicationInitializer;

/**
 * Created By Shiguang On 2024/9/8 12:23
 */
public class Main implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // 每当web项目启动,就会自动调用该接口的onStartup方法
        System.out.println("Main.onStartup");

        // ioc 容器初始化
        // dispatcherServlet
    }
}
