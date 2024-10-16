package com.shiguang.controller;

import com.shiguang.pojo.Student;
import com.shiguang.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Created By Shiguang On 2024/9/2 22:46
 */
@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    public void findAll(){
        List<Student> students = studentService.findAll();

        System.out.println("students = "+students);
    }


}
