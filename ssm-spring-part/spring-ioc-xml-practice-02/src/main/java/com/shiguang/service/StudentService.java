package com.shiguang.service;

import com.shiguang.pojo.Student;

import java.util.List;

/**
 * Created By Shiguang On 2024/9/2 13:18
 * description: 学生业务接口
 */
public interface StudentService {

    /**
     * 查询所有学员数据业务
     * @return List<Student>
     */
    List<Student> findAll();
}
