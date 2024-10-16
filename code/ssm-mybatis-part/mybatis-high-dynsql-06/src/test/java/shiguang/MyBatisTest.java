package shiguang;


import com.shiguang.mapper.EmployeeMapper;
import com.shiguang.pojo.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
    public void query() {
        EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);

        List<Employee> employeeList = mapper.query("tom",188.00);

        for (Employee employee : employeeList) {
            System.out.println(employee);
        }

    }


    @Test
    public void queryTrim() {
        EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);

        List<Employee> employeeList = mapper.queryTrim("tom",188.00);

        for (Employee employee : employeeList) {
            System.out.println(employee);
        }

    }

    @Test
    public void queryChoose() {
        EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);

        List<Employee> employeeList = mapper.queryChoose(null,null);

        for (Employee employee : employeeList) {
            System.out.println(employee);
        }

    }

    @Test
    public void update(){
        EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
        Employee employee = new Employee();
        employee.setEmpId(1);
        employee.setEmpSalary(999.99);

        mapper.update(employee);
    }


    @Test
    public void updateTrim(){
        EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
        Employee employee = new Employee();
        employee.setEmpId(1);
        employee.setEmpSalary(888.00);

        mapper.updateTrim(employee);
    }

    @Test
    public void queryBatch(){
        EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
        List<Integer> ids = Arrays.asList(2,6,8);
        List<Employee> employees =  mapper.queryBatch(ids);
        employees.forEach(System.out::println);
    }

    @Test
    public void updateBatch(){
        EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(1,"xx",33.99));
        employees.add(new Employee(2,"jj",88.88));
        employees.add(new Employee(3,"yy",66.66));
        int rows =   mapper.updateBatch(employees);
    }

    @Test
    public void insertBatch(){
        EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(null,"ergouzi1",33.99));
        employees.add(new Employee(null,"ergouzi2",88.88));
        employees.add(new Employee(null,"ergouzi3",66.66));

        System.out.println(employees);
        int rows =   mapper.insertBatch(employees);
        System.out.println(employees);
    }

    @Test
    public void deleteBatch(){
        EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
        List<Integer> ids = Arrays.asList(9,10,11);
        mapper.deleteBatch(ids);
    }

    // junit会在每一个@Test方法后执行@@AfterEach方法
    @AfterEach
    public void clear() {
        session.close();
    }
}