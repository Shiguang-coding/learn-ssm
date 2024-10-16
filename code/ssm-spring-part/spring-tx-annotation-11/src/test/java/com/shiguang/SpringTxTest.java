package com.shiguang;

import com.shiguang.config.javaConfig;
import com.shiguang.service.StudentService;
import com.shiguang.service.TopService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.io.FileNotFoundException;

/**
 * Created By Shiguang On 2024/9/3 22:46
 */

@SpringJUnitConfig(javaConfig.class)
public class SpringTxTest {
    @Autowired
    private StudentService studentService;

    @Autowired
    private TopService topService;
    
    @Test
    public void test() throws FileNotFoundException {
        studentService.changeInfo();
    }

    @Test
    public void test1(){
        topService.topService();
    }
}
