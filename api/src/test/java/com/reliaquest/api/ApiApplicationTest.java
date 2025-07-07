package com.reliaquest.api;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import com.reliaquest.api.impl.IEmployeeImpl;

@SpringBootTest
class ApiApplicationTest {

    @Mock
    IEmployeeImpl iEmployeeImpl;

    @Test
    void getAllEmployeesTest() {
        iEmployeeImpl.getAllEmployees();
    }

    @Test
    void getEmployeesByNameSearchTest() {
        iEmployeeImpl.getEmployeesByNameSearch("Future");
    }

    @Test
    void getEmployeeByIdTest() {
        iEmployeeImpl.deleteEmployeeById("ed9cf61e-2e32-4036-8c8e-8fa351907758");
    }

    @Test
    void createEmployeeTest() {
        
    }

    @Test
    void deleteEmployeeByIdTest() {
        // do the thing here
    }

    @Test
    void getHighestSalaryOfEmployeesTest() {
        // do the thing here
    }

    @Test
    void getTopTenHighestEarningEmployeeNamesTest() {
        // do the thing here
    }

}
