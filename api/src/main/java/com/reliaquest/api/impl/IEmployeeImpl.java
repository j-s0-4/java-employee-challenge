package com.reliaquest.api.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.reliaquest.api.controller.IEmployeeController;
import com.reliaquest.api.model.Employee;
import com.reliaquest.api.model.EmployeeRequest;
import com.reliaquest.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;

/** @param <Entity> object representation of an Employee
 * @param <EmployeeRequest> object representation of a request body for creating Employee(s)
 */

public class IEmployeeImpl implements IEmployeeController<Employee, EmployeeRequest>{

    private EmployeeService employeeService;

    @Autowired
    public IEmployeeImpl(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @Override
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> listEmployee = new ArrayList<>();
        listEmployee = employeeService.getAllEmployees();
        return new ResponseEntity<List<Employee>>(listEmployee, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Employee>> getEmployeesByNameSearch(String searchString){
        List<Employee> listEmployee = new ArrayList<>();
        listEmployee = employeeService.getEmployeeByName(searchString);
        return new ResponseEntity<List<Employee>>(listEmployee, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Employee> getEmployeeById(String id){
        Employee employee = employeeService.getEmployeeById(id);
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Employee> createEmployee(EmployeeRequest employeeInput){
        Employee employee = employeeService.createEmployee(employeeInput);
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteEmployeeById(String id){
        String success = employeeService.deleteEmployee(id);
        return new ResponseEntity<String>(success, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> getHighestSalaryOfEmployees(){
        List<Employee> listEmployee = new ArrayList<>();
        listEmployee = employeeService.getAllEmployees();
        Collections.sort(listEmployee,(a,b) -> 
                Integer.compare(b.getEmployee_salary(),a.getEmployee_salary()));
        Integer highestSalary = listEmployee.get(0).getEmployee_salary();
        return new ResponseEntity<Integer>(highestSalary, HttpStatus.OK);        
    }

    @Override
    public ResponseEntity<List<String>> getTopTenHighestEarningEmployeeNames(){
        List<Employee> listEmployee = new ArrayList<>();
        listEmployee = employeeService.getAllEmployees();
        Collections.sort(listEmployee,(a,b) -> 
                Integer.compare(b.getEmployee_salary(),a.getEmployee_salary()));
        List<String> topEarners = new ArrayList<>();
        for(int i=0; i<10; i++){
            topEarners.add(listEmployee.get(i).getEmployee_name());
        }
        return new ResponseEntity<List<String>>(topEarners, HttpStatus.OK); 
    }

}