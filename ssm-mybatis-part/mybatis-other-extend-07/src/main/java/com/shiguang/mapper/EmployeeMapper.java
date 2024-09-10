package com.shiguang.mapper;

import com.shiguang.pojo.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created By Shiguang On 2024/9/5 16:02
 */
public interface EmployeeMapper {

    // 根据姓名和工资查询员工信息
    List<Employee> query(@Param("name") String name,@Param("salary") Double salary);
    List<Employee> queryTrim(@Param("name") String name,@Param("salary") Double salary);

    // 根据员工id更新员工数据,要求 name 和 salary 不为null 时才更新
    int update(Employee employee);

    int updateTrim(Employee employee);


    // 根据两个条件查询, 若姓名不为null使用姓名查询,
    // 若姓名为null,薪水部位null使用薪水查询
    // 若都为null,查询全部
    List<Employee> queryChoose(@Param("name") String name,@Param("salary") Double salary);



    List<Employee> queryBatch(@Param("ids") List<Integer> ids);
    int deleteBatch(@Param("ids") List<Integer> ids);

    int insertBatch(@Param("employees") List<Employee> employees);

    int updateBatch(@Param("employees") List<Employee> employees);

    List<Employee> queryAll();


}
