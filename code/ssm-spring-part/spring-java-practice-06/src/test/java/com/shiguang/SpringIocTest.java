package com.shiguang;

import com.shiguang.config.JavaConfig;
import com.shiguang.controller.StudentController;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created By Shiguang On 2024/9/3 12:20
 */
public class SpringIocTest {

    @Test
    public void test(){
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(JavaConfig.class);

        StudentController controller = annotationConfigApplicationContext.getBean(StudentController.class);
        controller.findAll();

        annotationConfigApplicationContext.close();
    }
}
