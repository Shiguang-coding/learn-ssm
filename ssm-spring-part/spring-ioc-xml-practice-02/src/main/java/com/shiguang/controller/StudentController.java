package com.shiguang.controller;

import com.shiguang.pojo.Student;
import com.shiguang.service.StudentService;

import java.util.List;

/**
 * Created By Shiguang On 2024/9/2 13:24
 */
public class StudentController {
    private StudentService studentService;

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    public void findAll(){
        List<Student> students = studentService.findAll();
        System.out.println("最终学员数据为:"+students);
    }
}
