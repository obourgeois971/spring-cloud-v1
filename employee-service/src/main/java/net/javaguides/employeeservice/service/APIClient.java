package net.javaguides.employeeservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import net.javaguides.employeeservice.dto.DepartmentDto;

// Test Get: http://localhost:8081/api/employees/3
// 1. @FeignClient(url = "http://localhost:8080", value = "DEPARTMENT-SERVICE")
// 2.
@FeignClient(name = "DEPARTMENT-SERVICE")
public interface APIClient {
	// Build get depratment rest api
	@GetMapping("api/departments/{department-code}")
	DepartmentDto getDepartment(@PathVariable("department-code") String departmentCode);
}
