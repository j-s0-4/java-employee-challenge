package com.reliaquest.api.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EmployeeRequest {
    private String name;
    private Integer salary;
    private Integer age; 
    private String title;
}
