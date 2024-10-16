package com.shiguang;

import com.shiguang.config.JavaConfiguration;
import com.shiguang.config.JavaConfigurationA;
import com.shiguang.config.JavaConfigurationB;
import com.shiguang.ioc_01.StudentController;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created By Shiguang On 2024/9/3 11:04
 */
public class SpringIocTest {
    @Test
    public void test1() {

        // 1. 创建ioc容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(JavaConfiguration.class);


        // 先声明空
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(JavaConfiguration.class);
        annotationConfigApplicationContext.refresh();

        annotationConfigApplicationContext.close();


        StudentController studentController = applicationContext.getBean(StudentController.class);

        System.out.println("studentController = " + studentController);

        applicationContext.close();

    }

    @Test
    public void test2() {
        // beanA 已经导入 beanB
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(JavaConfigurationA.class);

        JavaConfigurationA beanA = annotationConfigApplicationContext.getBean(JavaConfigurationA.class);
        JavaConfigurationB beanB = annotationConfigApplicationContext.getBean(JavaConfigurationB.class);

        System.out.println("beanA = " + beanA);
        System.out.println("beanB = " + beanB);
    }


}
