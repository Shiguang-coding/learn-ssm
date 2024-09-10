package com.shiguang.mapper;

import com.shiguang.pojo.Teacher;

/**
* @author ShiGuang
* @description 针对表【teacher】的数据库操作Mapper
* @createDate 2024-09-05 22:04:54
* @Entity com.shiguang.pojo.Teacher
*/
public interface TeacherMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);

}
