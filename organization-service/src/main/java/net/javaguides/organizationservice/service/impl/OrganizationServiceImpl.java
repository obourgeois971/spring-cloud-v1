package net.javaguides.organizationservice.service.impl;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.javaguides.organizationservice.dto.OrganizationDto;
import net.javaguides.organizationservice.entity.Organization;
import net.javaguides.organizationservice.mapper.OrganizationMapper;
import net.javaguides.organizationservice.reporsitory.OrganizationRepository;
import net.javaguides.organizationservice.service.OrganizationService;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

	OrganizationRepository organizationRepository;
	
	@Override
	public OrganizationDto saveOrganization(OrganizationDto organizationDto) {
		
		// Convert OrganizationDto into Organization jpa entity
		Organization organization = OrganizationMapper.mapToOrganization(organizationDto);
		
		Organization savedOrganization = organizationRepository.save(organization);
		
		OrganizationDto savedOrganizationDto = OrganizationMapper.mapToOrganizationDto(savedOrganization);
		
		return savedOrganizationDto;
	}

	@Override
	public OrganizationDto getOrganizationByCode(String organizationCode) {
		Organization organization = organizationRepository.findByorganizationCode(organizationCode);
		return OrganizationMapper.mapToOrganizationDto(organization);
	}

}
