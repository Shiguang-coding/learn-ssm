package com.shiguang.mapper;

import com.shiguang.pojo.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * Created By Shiguang On 2024/9/4 14:59
 */

public interface EmployeeMapper {

    Employee queryById(Integer empId);

    Employee queryBySalary(Double salary);

    Employee queryByNameAndSalary(@Param("empName") String empName,@Param("empSalary") Double salary);


    // mapper接口中不允许重载!!
    int insertEmpFromMap(Map<String,Object> employee);

    int deleteById(Integer empId);


}
