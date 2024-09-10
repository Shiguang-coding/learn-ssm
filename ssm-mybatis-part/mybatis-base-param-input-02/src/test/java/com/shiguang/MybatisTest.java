package com.shiguang;

import com.shiguang.mapper.EmployeeMapper;
import com.shiguang.pojo.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * Created By Shiguang On 2024/9/4 15:17
 */
public class MybatisTest {
    @Test
    public void test() throws IOException {
        InputStream rs = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(rs);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

//        Employee employee = mapper.queryById(1);
//        System.out.println("employee = " + employee);


//        Employee employee = mapper.queryByNameAndSalary("tom",200.33000);
//
//        System.out.println("employee = " + employee);


        HashMap<String, Object> emp = new HashMap<>();
        emp.put("empName","cat");
        emp.put("empSalary",88.88);
        mapper.insertEmpFromMap(emp);

        sqlSession.commit();

        sqlSession.close();

    }
}
