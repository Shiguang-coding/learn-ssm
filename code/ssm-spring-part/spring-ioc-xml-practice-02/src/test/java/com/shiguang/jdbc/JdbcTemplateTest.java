package com.shiguang.jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import com.shiguang.controller.StudentController;
import com.shiguang.pojo.Student;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created By Shiguang On 2024/9/1 22:18
 * description: 测试jdbcTemplate如何使用
 */
public class JdbcTemplateTest {


    /**
     * 通过java代码实例化jdbcTemplate
     */
    private void testForJava() {

        /**
         *  JdbcTemplate 只是简化数据库CRUD,不提供连接池
         *  DruidDataSource 负责连接的创建和数据库驱动的注册等
         */


        // 0,创建一个连接池对象
        DruidDataSource dataSource = new DruidDataSource();
        // url地址
        // url=jdbc:mysql://IP地址:端口号/数据库名
        // 如果用的是本地的数据库，可以省略IP地址和端口号，比如这样：url=jdbc:mysql:///studb
        dataSource.setUrl("jdbc:mysql:///studb");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver"); //jdbc驱动
        dataSource.setUsername("root"); //账号
        dataSource.setPassword("shiguang"); // 密码

        // 1,实例化JdbcTemplate对象
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        jdbcTemplate.setDataSource(dataSource);

        // 2,直接调用方法
        // jdbcTemplate.update() DDL,DML,DCL(非查询方法)
        // jdbcTemplate.queryForObject() DQL 查询单个对象
        // jdbcTemplate.query() DQL 查询集合
    }

    /**
     * 通过IOC容器读取jdbcTemplage
     */

    @Test
    public void testForIoc() {
        // 1,创建IOC容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-01.xml");


        // 2,获取jdbcTemplate组件
        JdbcTemplate jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);


        // 3,进行数据库的CRUD操作
        // 3.1, 插入 删除 修改
        String sql = "insert into students(id,name,gender,age,class) values(?,?,?,?,?)";

        /**
         * 参数1: String sql ,可以带占位符?,?只能代替值,不能替代关键字和容器名
         * 参数2: Object ...param 传入占位符的值,按顺序从左往右
         */
//        int rows = jdbcTemplate.update(sql, 9,"二麻子", "男", 18, "高中一班");
//        System.out.println("rows = " + rows);

        // 3.2, 查询单条数据
        // 根据id查询学生数据,返回实体对象

        sql = "select * from students where id = ? ;";

        /**
         * 参数1：sql语句 可以用占位符?
         * 参数2：RowMapper 列名和属性名的映射器接口
         * 参数3：Object ...param 可变参数 占位符的值
         * 返回值 rowMapper 指定的对象
         */

//        jdbcTemplate.queryForObject(sql, new RowMapper<Student>() {
//            @Override
//            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
//                // rs: 结果集
//                // rowNum: 行数
//
//                // rs结果集中获取列的值 赋值给实体类对象即可
//                Student student = new Student();
//                student.setId(rs.getInt("id"));
//                student.setName(rs.getString("name"));
//                student.setAge(rs.getInt("age"));
//                student.setGender(rs.getString("gender"));
//                student.setClasses(rs.getString("class"));
//                return student;
//            }
//        }, 1);

        Student student1 = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            // rs: 结果集
            // rowNum: 行数
            // rs结果集中获取列的值 赋值给实体类对象即可
            Student student = new Student();
            student.setId(rs.getInt("id"));
            student.setName(rs.getString("name"));
            student.setAge(rs.getInt("age"));
            student.setGender(rs.getString("gender"));
            student.setClasses(rs.getString("class"));
            return student;
        }, 1);

        System.out.println("student1 = " + student1);


        // 3.3, 查询所有学生数据
        sql = "select * from students;";

        // TODO BeanPropertyRowMapper 可以自动映射列和属性值,要求列名与属性名一致,不一致需起别名
        List<Student> studentList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Student>(Student.class));

        System.out.println("studentList = "+ studentList);
    }


    /**
     * 从ioc容器中获取Controller并调用业务,内部都是ioc容器进行组装
     */
    @Test
    public void testQuery(){
        //1,创建ioc容器
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-02.xml");

        //2, 获取组件对象
        StudentController studentController = applicationContext.getBean(StudentController.class);

        //3, 使用组件

        studentController.findAll();

        //4, 关闭容器
        applicationContext.close();
    }

}
