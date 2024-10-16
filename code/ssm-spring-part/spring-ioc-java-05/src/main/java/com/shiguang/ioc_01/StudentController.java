package com.shiguang.ioc_01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created By Shiguang On 2024/9/3 10:50
 */

@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;
}
