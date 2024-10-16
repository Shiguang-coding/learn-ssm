package com.shiguang;

import com.shiguang.mapper.EmployeeMapper;
import com.shiguang.pojo.Employee;
import com.shiguang.pojo.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created By Shiguang On 2024/9/4 10:00
 */
public class MybatisTest {
    @Test
    public void test01() throws IOException {
        // 1. 读取外部配置文件（mybatis-config.xml）
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

        // 2. 创建sqlSessionFactory
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);

        // 3. 根据sqlSessionFactory创建sqlSession(每次业务创建一个,用完即释放)
        SqlSession sqlSession = sessionFactory.openSession();

        // 4. 获取接口的代理对象（代理技术）,调用代理对象的方法,就会查找mapper接口的方法
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

        Employee employee = mapper.queryById(1);
        System.out.println("employee = " + employee);

        // 5. 提交事务(非DCL,即非查询) 和释放资源
//        sqlSession.commit();
        sqlSession.close();
    }


    @Test
    public void testIbatis() throws IOException {
        // 1. 读取外部配置文件（mybatis-config.xml）
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

        // 2. 创建sqlSessionFactory
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);

        // 3. 根据sqlSessionFactory创建sqlSession(每次业务创建一个,用完即释放)
        SqlSession sqlSession = sessionFactory.openSession();

        // 4. 使用 sqlSession 提供的crud方法进行数据库查询即可
        //    查询: selectOne selectList
        //         参数1:字符串 sql标签对应的标识 id | namespace.id, 参数2: Object 执行sql语句传入的参数
        //    缺点: 1. sql语句标签对应的字符串标识容易出错
        //         2. 参数只能传一个,需多个参数时需整合
        //         3. 返回值为Object类型
        //

//        int ruwNum = sqlSession.insert("xx.jj.xx", "张三");
//        System.out.println("ruwNum = " + ruwNum);

        Student student = sqlSession.selectOne("xx.jj.kk", 4);
        System.out.println("student = " + student);

        // 5. 提交事务(非DCL,即非查询) 和释放资源
        sqlSession.commit();
        sqlSession.close();
    }
}
