package com.shiguang.service.impl;

import com.shiguang.dao.StudentDao;
import com.shiguang.pojo.Student;
import com.shiguang.service.StudentService;

import java.util.List;

/**
 * Created By Shiguang On 2024/9/2 13:20
 */
public class StudentServiceImpl implements StudentService {
    private StudentDao studentDao;

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    /**
     * 查询所有学员数据业务
     * @return List<Student>
     */
    @Override
    public List<Student> findAll() {
        List<Student> students = studentDao.queryAll();
        System.out.println("Service students = "+students);
        return students;
    }
}
