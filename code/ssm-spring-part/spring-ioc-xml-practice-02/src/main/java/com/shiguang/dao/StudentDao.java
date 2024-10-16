package com.shiguang.dao;

import com.shiguang.pojo.Student;

import java.util.List;

/**
 * Created By Shiguang On 2024/9/2 13:09
 * description: 数据层接口
 */
public interface StudentDao {


    /**
     * 数据库查询全部学生接口
     */

    List<Student> queryAll();
}
