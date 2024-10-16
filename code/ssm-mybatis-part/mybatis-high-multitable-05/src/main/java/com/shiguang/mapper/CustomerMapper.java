package com.shiguang.mapper;

import com.shiguang.pojo.Customer;

import java.util.List;

/**
 * Created By Shiguang On 2024/9/5 14:59
 */
public interface CustomerMapper {

    // 查询所有客户信息以及客户对应的订单信息
    List<Customer> queryList();
}
