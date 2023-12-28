package net.javaguides.employeeservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import net.javaguides.employeeservice.dto.APIResponseDto;
import net.javaguides.employeeservice.dto.DepartmentDto;
import net.javaguides.employeeservice.dto.EmployeeDto;
import net.javaguides.employeeservice.entity.Employee;
import net.javaguides.employeeservice.mapper.EmployeeMapper;
import net.javaguides.employeeservice.repository.EmployeeRepository;

@Service
public class EmployeeService implements IEmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

//	@Autowired
//	private RestTemplate restTemplate;

//	private  WebClient.Builder webClientBuilder;

//	@Autowired
//	public EmployeeService(WebClient.Builder webClientBuilder) {
//		this.webClientBuilder = webClientBuilder;
//	}
	
	private APIClient apiClient;
	
	@Autowired
	public EmployeeService(APIClient apiClient) {
		this.apiClient = apiClient;
	}

	@Override
	public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
		Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
		Employee result = employeeRepository.save(employee);
		return EmployeeMapper.mapToEmployeeDto(result);
	}

	@Override
	public APIResponseDto getEmployeeById(Long employeeId) {

		Employee employee = employeeRepository.findById(employeeId).get();

//USING RestTemplate		
//		ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity(
//				"http://localhost:8080/api/departments/" + employee.getDepartmentCode(), DepartmentDto.class);
//
//		DepartmentDto departmentDto = responseEntity.getBody();
		
		DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());
		EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);

		APIResponseDto apiResponseDto = new APIResponseDto();
		apiResponseDto.setEmployee(employeeDto);
		apiResponseDto.setDepartment(departmentDto);

		return apiResponseDto;
	}

}
