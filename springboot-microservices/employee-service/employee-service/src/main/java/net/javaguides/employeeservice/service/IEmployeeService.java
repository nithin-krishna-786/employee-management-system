package net.javaguides.employeeservice.service;

import org.springframework.stereotype.Service;

import net.javaguides.employeeservice.dto.APIResponseDto;
import net.javaguides.employeeservice.dto.EmployeeDto;

@Service
public interface IEmployeeService {
	   EmployeeDto saveEmployee(EmployeeDto employeeDto);
	   APIResponseDto getEmployeeById(Long employeeId);
}
