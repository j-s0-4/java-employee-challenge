package com.reliaquest.api.client;

import lombok.extern.log4j.Log4j2;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.reliaquest.api.model.Employee;
import com.reliaquest.api.model.EmployeeRequest;

@Log4j2
@Service
public class EmployeeApiClient {
    @Value("${api.employeeService.url}")
    private String urlApiEmployee;

    private final RestTemplate restTemplate;
    private final HttpHeaders headers = new HttpHeaders();

    @Autowired
    public EmployeeApiClient(final @Qualifier("restTemplate") RestTemplate restTemplate){
        this.restTemplate = restTemplate;
        this.headers.set("Content-Type","application/json");
    }

    public ResponseEntity<List<Employee>> getEmployees() {
        ResponseEntity<List<Employee>> result = null;
        final long time = System.currentTimeMillis();
        try {  
            result = restTemplate.exchange(
                urlApiEmployee, 
                HttpMethod.GET, 
                null, 
                new ParameterizedTypeReference<List<Employee>>() {});
            log.debug("Api response:"+result.getBody());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        finally {
            log.debug("TotalTimeTaken="+(System.currentTimeMillis()-time));
        }
        return result;
    }

    public ResponseEntity<Employee> getEmployeeById(String id) {
        ResponseEntity<Employee> result = null;
        final long time = System.currentTimeMillis();
        try {
            result = restTemplate.getForEntity((urlApiEmployee+"/"+id), Employee.class);    
            log.debug("Api response:"+result.getBody());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        finally {
            log.debug("TotalTimeTaken="+(System.currentTimeMillis()-time)+". TransactionId="+id);
        }
        return result;
    }

    public ResponseEntity<List<Employee>> getEmployeeByName(String name) {
        ResponseEntity<List<Employee>> result = null;
        final long time = System.currentTimeMillis();
        try {
            result = restTemplate.exchange(
                urlApiEmployee+"/"+name, 
                HttpMethod.GET, 
                null, 
                new ParameterizedTypeReference<List<Employee>>() {});
            log.debug("Api response:"+result.getBody());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        finally {
            log.debug("TotalTimeTaken="+(System.currentTimeMillis()-time)+". TransactionId="+name);
        }
        return result;
    }

    public ResponseEntity<Employee> createEmployee(final EmployeeRequest employee) {
        final long time = System.currentTimeMillis();
        ResponseEntity<Employee> result = null;
        try {
            result = restTemplate.postForEntity(urlApiEmployee, new HttpEntity<>(employee.toString(), headers), Employee.class);
            log.debug("Api response:"+result.getBody());
        } catch (Exception e) {
            if(e.getMessage().contains("400")) {
                final String msg = "Invalid Request. Employee=" + employee.getName() + ". " + e.getMessage();
                log.error(msg);
            }
            else {
                final String msg = "Unexpected Error. Employee=" + employee.getName() + ". " + e.getMessage();
                log.error(msg);
            }
        } finally {
            log.info("TotalTimeTaken="+(System.currentTimeMillis()-time)+". Employee="+employee.getName());
        }
        return result;
    }

    public ResponseEntity<String> deleteEmployee(String name) {
        final long time = System.currentTimeMillis();
        ResponseEntity<String> result = null;
        try {
            result = restTemplate.exchange(urlApiEmployee, HttpMethod.DELETE, new HttpEntity<>(name, headers), String.class);
            log.debug("Api response:"+result.getBody());
        } catch (Exception e) {
            if(e.getMessage().contains("400")) {
                final String msg = "Invalid Request. Employee=" + name + ". " + e.getMessage();
                log.error(msg);
            }
            else {
                final String msg = "Unexpected Error. Employee=" + name + ". " + e.getMessage();
                log.error(msg);
            }
        } finally {
            log.info("TotalTimeTaken="+(System.currentTimeMillis()-time)+". Employee="+name);
        }
        return result;
    }
}

