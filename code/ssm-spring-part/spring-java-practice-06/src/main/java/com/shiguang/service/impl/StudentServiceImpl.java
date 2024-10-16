package com.shiguang.service.impl;

import com.shiguang.dao.StudentDao;
import com.shiguang.pojo.Student;
import com.shiguang.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created By Shiguang On 2024/9/2 22:42
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;
    @Override
    public List<Student> findAll() {
        return studentDao.queryAll();
    }
}
