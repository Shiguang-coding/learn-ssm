package com.atguigu.mapper;

import com.atguigu.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * projectName: com.atguigu.mapper
 *
 * @author: 赵伟风
 * description:
 */
@Repository
public interface UserMapper {

    List<User> queryAll();

    int delete(int i);
}
