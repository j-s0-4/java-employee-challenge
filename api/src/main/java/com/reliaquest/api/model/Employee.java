package com.reliaquest.api.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Employee {
    private String id;
    private String employee_name;
    private Integer employee_salary;
    private Integer employee_age; 
    private String employee_title;
    private String employee_email;
}
