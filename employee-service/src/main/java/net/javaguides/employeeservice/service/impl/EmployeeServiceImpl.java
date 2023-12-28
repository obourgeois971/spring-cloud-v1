package net.javaguides.employeeservice.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.AllArgsConstructor;
import net.javaguides.employeeservice.dto.APIResponseDto;
import net.javaguides.employeeservice.dto.DepartmentDto;
import net.javaguides.employeeservice.dto.EmployeeDto;
import net.javaguides.employeeservice.entity.Employee;
import net.javaguides.employeeservice.repository.EmployeeRepository;
import net.javaguides.employeeservice.service.APIClient;
import net.javaguides.employeeservice.service.EmployeeService;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;
	
	// 1. private RestTemplate restTemplate;
	
	// 2. private WebClient webClient;
	
	// 3.
	private APIClient apiClient;

	@Override
	public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
		
		Employee employee = new Employee(
				employeeDto.getId(),
				employeeDto.getFirstName(),
				employeeDto.getLastName(),
				employeeDto.getEmail(),
				employeeDto.getDepartmentCode()
		);
		
		Employee savedEmployee = employeeRepository.save(employee);
		
		EmployeeDto savedEmployeeDto = new EmployeeDto(
				savedEmployee.getId(),
				savedEmployee.getFirstName(),
				savedEmployee.getLastName(),
				savedEmployee.getEmail(),
				savedEmployee.getDepartmentCode()
		);

		return savedEmployeeDto;
	}

	@Override
	// Test with : http://localhost:8081/api/employees/3 get
	public APIResponseDto getEmployeeById(Long employeeId) {
		Employee employee = employeeRepository.findById(employeeId).get();
		
		/* 1. ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity(
				"http://localhost:8080/api/departments/" + employee.getDepartmentCode(), 
				DepartmentDto.class
		);*/

		// 1. DepartmentDto departmentDto = responseEntity.getBody();

		/* 2. DepartmentDto departmentDto = webClient.get()
				 .uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
				 .retrieve()
				 .bodyToMono(DepartmentDto.class)
				 .block();*/
		
		// 3.
		DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());
		
		EmployeeDto employeeDto = new EmployeeDto(
				employee.getId(),
				employee.getFirstName(),
				employee.getLastName(),
				employee.getEmail(),
				employee.getDepartmentCode()
		);
		
		APIResponseDto apiResponseDto = new APIResponseDto();
		apiResponseDto.setEmployee(employeeDto);
		apiResponseDto.setDepartment(departmentDto);
		
		return apiResponseDto;
	}
}
