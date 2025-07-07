package com.reliaquest.api;

import java.util.List;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import com.reliaquest.api.impl.IEmployeeImpl;
import com.reliaquest.api.model.Employee;
import com.reliaquest.api.model.EmployeeRequest;

@SpringBootTest
class ApiApplicationTest {

    @Mock
    IEmployeeImpl iEmployeeImpl;

    @Test
    void getAllEmployeesTest() {
        ResponseEntity<List<Employee>> response = iEmployeeImpl.getAllEmployees();
        List<Employee> listEmployees = (ArrayList<Employee>)response.getBody();
        Assert.notEmpty(listEmployees, "getAllEmployeesTest returned empty.");
    }

    @Test
    void getEmployeesByNameSearchTest() {
        ResponseEntity<List<Employee>> response = iEmployeeImpl.getEmployeesByNameSearch("Future");
        List<Employee> listEmployees = (ArrayList<Employee>)response.getBody();
        Assert.notEmpty(listEmployees, "getEmployeesByNameSearchTest returned empty.");
    }

    @Test
    void getEmployeeByIdTest() {
        ResponseEntity<Employee> response = iEmployeeImpl.getEmployeeById("ed9cf61e-2e32-4036-8c8e-8fa351907758");
        Employee employee = (Employee)response.getBody(); 
        Assert.notNull(employee, "getEmployeeByIdTest returned null.");
    }
    
    @Test
    void createEmployeeTest() {
        EmployeeRequest employeeRequest = EmployeeRequest.builder()
        .name("John Cadet")
        .age(30)
        .title("Software Engineer")
        .salary(90000)
        .build();
        ResponseEntity<Employee> response = iEmployeeImpl.createEmployee(employeeRequest);
        Employee employee = (Employee)response.getBody(); 
        Assert.notNull(employee, "createEmployeeTest returned null.");
    }

    @Test
    void deleteEmployeeByIdTest() {
        ResponseEntity<String> response = iEmployeeImpl.deleteEmployeeById("b8ac8af6-870b-4f34-80d8-4376e7905103");
        String employeeName = response.getBody();
        Assert.notNull(employeeName, "deleteEmployeeByIdTest returned null.");
    }

    @Test
    void getHighestSalaryOfEmployeesTest() {
        ResponseEntity<Integer> response = iEmployeeImpl.getHighestSalaryOfEmployees();
        Integer salary = response.getBody();
        Assert.notNull(salary, "getHighestSalaryOfEmployeesTest returned null.");
    }

    @Test
    void getTopTenHighestEarningEmployeeNamesTest() {
        ResponseEntity<List<String>> response = iEmployeeImpl.getTopTenHighestEarningEmployeeNames();
        List<String> toptenSalary = (List<String>)response.getBody();
        Assert.notEmpty(toptenSalary, "getTopTenHighestEarningEmployeeNamesTest returned empty.");
    }
}
