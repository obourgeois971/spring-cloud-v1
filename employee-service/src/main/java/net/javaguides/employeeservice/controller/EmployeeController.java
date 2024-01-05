package net.javaguides.employeeservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.javaguides.employeeservice.dto.APIResponseDto;
import net.javaguides.employeeservice.dto.EmployeeDto;
import net.javaguides.employeeservice.service.EmployeeService;

@Tag(
		name = "Employee Service -  EmployeeController",
		description = "Employee Controller Exposes REST APIs for Employee-Service"
)
@RestController
@AllArgsConstructor
@RequestMapping("api/employees")
public class EmployeeController {

	private EmployeeService employeeService;
	
	// Build save Employee REST API
	@Operation(
			summary = "Save Employee REST API",
			description = "Save Employee REST API is used to save Employee object in a database"
	)
	@ApiResponse(
			responseCode = "201",
			description = "HTTP Status 201 Created"
	)
	@PostMapping
	private ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto) {
		EmployeeDto savedEmployeeDto = employeeService.saveEmployee(employeeDto);
		return new ResponseEntity<>(savedEmployeeDto, HttpStatus.CREATED);
	}
	
	// Build Get Employee REST API
	@Operation(
			summary = "Get Employee REST API",
			description = "Get Employee REST API is used to get Employee object from the database"
	)
	@ApiResponse(
			responseCode = "200",
			description = "HTTP Status 200 SUCCESS"
	)
	@GetMapping("{id}")
	private ResponseEntity<APIResponseDto> getEmployeeById(@PathVariable("id") Long employeeId) {
		APIResponseDto apiResponseDto = employeeService.getEmployeeById(employeeId);
		return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
	}
}
