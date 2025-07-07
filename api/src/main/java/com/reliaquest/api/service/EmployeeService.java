package com.reliaquest.api.service;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.reliaquest.api.model.Employee;
import com.reliaquest.api.model.EmployeeRequest;
import com.reliaquest.api.client.EmployeeApiClient;

public class EmployeeService {
  private static final Logger LOG = LogManager.getLogger(EmployeeService.class);
  private EmployeeApiClient employeeApiClient;

  @Autowired
  public EmployeeService(EmployeeApiClient employeeApiClient){
      this.employeeApiClient = employeeApiClient;
  }


  public List<Employee> getAllEmployees(){
    List<Employee> listEmployee = new ArrayList<>();
    ResponseEntity<List<Employee>> response = employeeApiClient.getAllEmployees(null);
    listEmployee = (List<Employee>) response.getBody(); //cast
    LOG.info("Employee List fetched: "+listEmployee);
    return listEmployee;
  }

  public Employee getEmployeeById(String id){
    ResponseEntity<Employee> response = employeeApiClient.getAllEmployees(id); //pass id
    Employee employee = (Employee) response.getBody();
    LOG.info("Employee fetched: "+employee.getId());
    return employee;
  }

  public List<Employee> getEmployeeByName(String name){
    ResponseEntity<List<Employee>> response = employeeApiClient.getEmployeeByName(name);
    List<Employee> listEmployee = new ArrayList<>();
    listEmployee = (List<Employee>) response.getBody();
    LOG.info("Employee name matched list fetched: "+listEmployee);
    return listEmployee;
  }

  public Employee createEmployee(EmployeeRequest employeeInput){
    ResponseEntity<Employee> response = employeeApiClient.createEmployee(employeeInput);
    Employee employee = (Employee) response.getBody();
    LOG.info("Employee created: "+employee);
    return employee;
  }

  public String deleteEmployee(String id){
    ResponseEntity<String> response = employeeApiClient.deleteEmployee(id);
    String success = response.getBody();
    LOG.info("Employee deleted: "+id);
    return success;
  }

  // public List<String> getTopTenHighestEarningEmployeeNames(){

  // }

  // public Integer getHighestEarningEmployees(){

  // }

}