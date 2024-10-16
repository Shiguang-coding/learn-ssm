package com.shiguang.mapper;

import com.shiguang.pojo.Employee;

import java.util.List;
import java.util.Map;

/**
 * Created By Shiguang On 2024/9/4 14:59
 */

public interface EmployeeMapper {

    // DQL(查询) 返回指定类型
    // 根据ID获取员工姓名
    String getNameById(Integer id);
    // 根据ID获取工资
    Double getSalaryById();

    Employee queryById(Integer id);


    //查询最高工资的员工姓名及工资
    Map<String,Object> selectEmpNameAndMaxSalary();


    // 查询工资高于传入值的员工姓名
    List<String> queryNamesBySalary(Double salary);

    // 查询全部员工信息
    List<Employee> queryAll();

    int insertEmp(Employee employee);

    // 如果是DML (插入,修改,删除) int / long
    int delteById(Integer id);



}
