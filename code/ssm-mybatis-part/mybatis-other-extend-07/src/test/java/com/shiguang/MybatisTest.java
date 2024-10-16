package com.shiguang;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shiguang.mapper.EmployeeMapper;
import com.shiguang.pojo.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

/**
 * Created By Shiguang On 2024/9/5 20:56
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
    public void queryAll(){
        EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
        //在调用之前,先设置分页数据(当前是第几页,每页显示多少个)
        // TODO: 注意不能将两条查询装到一个分页区
        PageHelper.startPage(1,10);
        List<Employee> employees =  mapper.queryAll();

        // 将查询数据封装到一个PageInfo的分页实体(一共多少页,一共有多少条等等)
        PageInfo<Employee> pageInfo = new PageInfo<>(employees);

        // PageInfo获取分页数据
        // 当前页的数据
        List<Employee> list = pageInfo.getList();
        System.out.println("list = " + list);
        // 获取总页数
        int pages = pageInfo.getPages();
        System.out.println("pages = " + pages);
        // 获取总条数
        long total = pageInfo.getTotal();
        System.out.println("total = " + total);

    }
}
