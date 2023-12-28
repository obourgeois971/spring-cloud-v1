package net.javaguides.employeeservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import net.javaguides.employeeservice.dto.APIResponseDto;
import net.javaguides.employeeservice.dto.EmployeeDto;
import net.javaguides.employeeservice.service.EmployeeService;

@RestController
@AllArgsConstructor
@RequestMapping("api/employees")
public class EmployeeController {

	private EmployeeService employeeService;
	
	// Build save Employee REST API
	@PostMapping
	private ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto) {
		EmployeeDto savedEmployeeDto = employeeService.saveEmployee(employeeDto);
		return new ResponseEntity<>(savedEmployeeDto, HttpStatus.CREATED);
	}
	
	// Build Get Employee REST API
	@GetMapping("{id}")
	private ResponseEntity<APIResponseDto> getEmployeeById(@PathVariable("id") Long employeeId) {
		APIResponseDto apiResponseDto = employeeService.getEmployeeById(employeeId);
		return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
	}
}
