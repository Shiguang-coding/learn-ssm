package com.shiguang.mapper;

import com.shiguang.pojo.Order;

/**
 * Created By Shiguang On 2024/9/5 14:15
 */
public interface OrderMapper {
    Order selectOrderWithCustomer(Integer orderId);
}
