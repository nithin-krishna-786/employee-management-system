package net.javaguides.employeeservice.service;

import net.javaguides.employeeservice.dto.DepartmentDto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(url = "http://localhost:8080/api/departments/",value = "DEPARTMENT-SERVICE") //ORIG WITH 1 INSTANCE
@FeignClient(name = "DEPARTMENT-SERVICE") 
public interface APIClient {
    // Build get department rest api
    @GetMapping("api/departments/{department-code}")
    DepartmentDto getDepartment(@PathVariable("department-code") String departmentCode);
}
