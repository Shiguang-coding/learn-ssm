package com.shiguang;

import com.shiguang.mapper.EmployeeMapper;
import com.shiguang.mapper.TeacherMapper;
import com.shiguang.pojo.Employee;
import com.shiguang.pojo.Teacher;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * Created By Shiguang On 2024/9/4 22:05
 */
public class MybatisTest {
    @Test
    public void test() throws IOException {
        // 1. 读取外部配置文件（mybatis-config.xml）
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

        // 2. 创建sqlSessionFactory
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);

        // 3. 根据sqlSessionFactory创建sqlSession(每次业务创建一个,用完即释放)
        //  openSession() 自动开启事务,不会自动提交事务
        //  openSession(true) 自定开启事务,并自动提交
        SqlSession sqlSession = sessionFactory.openSession(true);


        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

        Employee employee = new Employee();
        employee.setEmpName("wangwu");
        employee.setEmpSalary(88.66);


        System.out.println(employee.getEmpId());
        System.out.println("#########");

        int rows = mapper.insertEmp(employee);
        System.out.println(employee.getEmpId());
        System.out.println("rows = " + rows);


        // 5. 提交事务(非DCL,即非查询) 和释放资源
//        sqlSession.commit();
        sqlSession.close();
    }


    @Test
    public void test02() throws IOException {
        // 1. 读取外部配置文件（mybatis-config.xml）
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

        // 2. 创建sqlSessionFactory
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);

        // 3. 根据sqlSessionFactory创建sqlSession(每次业务创建一个,用完即释放)
        //  openSession() 自动开启事务,不会自动提交事务
        //  openSession(true) 自定开启事务,并自动提交
        SqlSession sqlSession = sessionFactory.openSession(true);


        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);


        // 自己维护
//        String tid = UUID.randomUUID().toString().replaceAll("-", "");
        Teacher teacher = new Teacher();
        teacher.settName("ergouzi");
//        teacher.settId(tid);

        System.out.println("tId before: " + teacher.gettId());
        int nums = mapper.insertTeacher(teacher);
        System.out.println("tId after: " + teacher.gettId());
        System.out.println("nums = " + nums);


        // 5. 提交事务(非DCL,即非查询) 和释放资源
//        sqlSession.commit();
        sqlSession.close();

    }

}
