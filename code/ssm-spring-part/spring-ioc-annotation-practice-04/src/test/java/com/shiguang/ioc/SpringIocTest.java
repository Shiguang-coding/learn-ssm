package com.shiguang.ioc;

import com.shiguang.controller.StudentController;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created By Shiguang On 2024/9/2 23:03
 */
public class SpringIocTest {

    @Test
    public void  test(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-01.xml");

        StudentController studentController = applicationContext.getBean(StudentController.class);

        studentController.findAll();

        applicationContext.close();

    }
}
