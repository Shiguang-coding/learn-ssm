package com.shiguang;

import com.shiguang.mapper.CustomerMapper;
import com.shiguang.mapper.OrderMapper;
import com.shiguang.pojo.Customer;
import com.shiguang.pojo.Order;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;


public class MyBatisTest {

    private SqlSession session;

    @BeforeEach
    public void init() throws IOException {
        session = new SqlSessionFactoryBuilder()
                .build(Resources.getResourceAsStream("mybatis-config.xml"))
                .openSession(true);
    }

    @Test
    public void testRelationshipToOne() {

        OrderMapper orderMapper = session.getMapper(OrderMapper.class);
        // 查询Order对象，检查是否同时查询了关联的Customer对象
        Order order = orderMapper.selectOrderWithCustomer(2);
        System.out.println("order = " + order);

    }

    @Test
    public void getCustomerList() {
        CustomerMapper mapper = session.getMapper(CustomerMapper.class);
        List<Customer> customers = mapper.queryList();
        for (Customer customer : customers) {
            Integer customerId = customer.getCustomerId();
            String customerName = customer.getCustomerName();
            System.out.println("customerId = " + customerId + ",customerName = " + customerName);
            List<Order> orderList = customer.getOrderList();

            for (Order order : orderList) {
                System.out.println(order);
            }

        }

    }



    // junit会在每一个@Test方法后执行@@AfterEach方法
    @AfterEach
    public void clear() {
        session.close();
    }
}