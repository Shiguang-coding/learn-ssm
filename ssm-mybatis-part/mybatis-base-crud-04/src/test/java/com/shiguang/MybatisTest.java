package com.shiguang;

import com.shiguang.mapper.UserMapper;
import com.shiguang.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created By Shiguang On 2024/9/5 11:45
 */
public class MybatisTest {

    private SqlSession sqlSession;
    private UserMapper userMapper;

    @BeforeEach // 每次执行测试方法前都先执行的初始化方法
    public void init() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        sqlSession = sessionFactory.openSession(true);
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @AfterEach // 每次执行测试方法之后调用
    public void close() {
        sqlSession.close();
    }

    @Test
    public void insert() {
        User insertUser = new User();
        insertUser.setUsername("二狗子");
        insertUser.setPassword("12345");

        System.out.println("insertUser before id : " + insertUser.getId());
        int rows = userMapper.insert(insertUser);
        System.out.println("rows = " + rows);

        System.out.println("insertUser after id : " + insertUser.getId());
    }

    @Test
    public void update() {
        User updateUser = new User();
        updateUser.setId(3);
        updateUser.setUsername("王铁锤");
        updateUser.setPassword("88888");

        int rows = userMapper.update(updateUser);
        System.out.println("rows = " + rows);

    }

    @Test
    public void selectOne(){
        User selectOneUser = userMapper.selectById(2);
        System.out.println("selectOneUser = " + selectOneUser);
    }

    @Test
    public void selectAll(){

        List<User> users = userMapper.selectAll();

        System.out.println("users = " + users);
    }

    @Test
    public void delete(){
        userMapper.delete(5);
    }
}
