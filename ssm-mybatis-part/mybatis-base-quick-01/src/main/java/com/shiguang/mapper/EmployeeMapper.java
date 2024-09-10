package com.shiguang.mapper;

import com.shiguang.pojo.Employee;

/**
 * Created By Shiguang On 2024/9/4 9:43
 */
public interface EmployeeMapper {

    Employee queryById(Integer id);


    int deleteById(Integer id);

}
