package com.shiguang.mapper;

import com.shiguang.pojo.User;

/**
* @author ShiGuang
* @description 针对表【user】的数据库操作Mapper
* @createDate 2024-09-05 22:04:54
* @Entity com.shiguang.pojo.User
*/
public interface UserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

}
