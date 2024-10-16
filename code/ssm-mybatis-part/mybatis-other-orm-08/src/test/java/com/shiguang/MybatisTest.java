package com.shiguang;

import com.shiguang.mapper.UserMapper;
import com.shiguang.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Created By Shiguang On 2024/9/5 21:51
 */
public class MybatisTest {
    private SqlSession session;

    @BeforeEach
    public void init() throws IOException {
        session = new SqlSessionFactoryBuilder()
                .build(Resources.getResourceAsStream("mybatis-config.xml"))
                .openSession(true);
    }

    // junit会在每一个@Test方法后执行@@AfterEach方法
    @AfterEach
    public void clear() {
        session.close();
    }


    @Test
    public void test(){
        UserMapper mapper = session.getMapper(UserMapper.class);
        User user =  mapper.selectByPrimaryKey(Long.parseLong("4"));
        System.out.println(user);
    }
}
