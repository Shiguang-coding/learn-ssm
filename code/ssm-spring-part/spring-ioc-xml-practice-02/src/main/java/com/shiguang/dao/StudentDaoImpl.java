package com.shiguang.dao;

import com.shiguang.pojo.Student;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Created By Shiguang On 2024/9/2 13:11
 */
public class StudentDaoImpl implements StudentDao{
    private JdbcTemplate   jdbcTemplate;

    // 注入JdbcTemplate对象
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Student> queryAll() {

        // jdbcTemplate 进行数据库查询,IOC容器进行装配,不要自己实例化
        String sql = "select id,name,gender,age,class as classes from students";
        List<Student> students = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Student>(Student.class));

        System.out.println("Dao students = "+students);


        return students;
    }
}
