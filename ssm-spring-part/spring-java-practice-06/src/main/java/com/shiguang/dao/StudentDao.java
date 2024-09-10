package com.shiguang.dao;


import com.shiguang.pojo.Student;

import java.util.List;

/**
 * Created By Shiguang On 2024/9/2 22:35
 */
public interface StudentDao {

    List<Student> queryAll();
}
