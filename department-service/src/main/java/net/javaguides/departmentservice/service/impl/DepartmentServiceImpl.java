package net.javaguides.departmentservice.service.impl;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.javaguides.departmentservice.dto.DepartmentDto;
import net.javaguides.departmentservice.entity.Department;
import net.javaguides.departmentservice.mapper.DepartmentMapper;
import net.javaguides.departmentservice.repository.DepartmentRepository;
import net.javaguides.departmentservice.service.DepartmentService;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

	private DepartmentRepository departmentRepository;
	
	@Override
	public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
		
		// Convert department dto to department Jpa entity
		/* 1. Department department = new Department(
				departmentDto.getId(),
				departmentDto.getDepartmentName(),
				departmentDto.getDepartmentDescription(),
				departmentDto.getDepartmentCode()
		);*/
		
		Department department = DepartmentMapper.mapToDepartment(departmentDto);
		
		Department savedDepartment = departmentRepository.save(department);
		
		/* 1. DepartmentDto sevedDepartmentDto = new DepartmentDto(
				savedDepartment.getId(),
				savedDepartment.getDepartmentName(),
				savedDepartment.getDepartmentDescription(),
				savedDepartment.getDepartmentCode()
		);*/
		
		DepartmentDto sevedDepartmentDto = DepartmentMapper.mapToDepartmentDto(savedDepartment);
		
		return sevedDepartmentDto;
	}

	@Override
	public DepartmentDto getDepartmentByCode(String departmentCode) {
		Department department = departmentRepository.findByDepartmentCode(departmentCode);
		
		/*DepartmentDto departmentDto = new DepartmentDto(
				department.getId(),
				department.getDepartmentName(),
				department.getDepartmentDescription(),
				department.getDepartmentCode()
		);*/
		
		DepartmentDto departmentDto = DepartmentMapper.mapToDepartmentDto(department);
		
		return departmentDto;
	}

}
