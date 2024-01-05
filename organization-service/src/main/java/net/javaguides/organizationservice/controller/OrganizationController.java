package net.javaguides.organizationservice.controller;

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
import net.javaguides.organizationservice.dto.OrganizationDto;
import net.javaguides.organizationservice.service.OrganizationService;

@Tag(
		name = "Organization Service - OrganizationController",
		description = "OrganizationController Exposes REST APIs for Organization Service"
)
@RestController
@RequestMapping("api/organizations")
@AllArgsConstructor
public class OrganizationController {

	private OrganizationService organizationService;
	
	// Build save Organization REST API
	@Operation(
			summary = "Save Organization RESt API",
			description = "Save Organization RESt API is used to organization object in database"
	)
	@ApiResponse(
			responseCode = "201",
			description = "HTTP Status 201 CREATED"
	)
	@PostMapping
	public ResponseEntity<OrganizationDto> saveOrganization(@RequestBody OrganizationDto organizationDto) {
		OrganizationDto savedOrganizationDto = organizationService.saveOrganization(organizationDto);
		return new ResponseEntity<>(savedOrganizationDto, HttpStatus.CREATED);
	}
	
	// Build Get Organization by Code REST API
	@Operation(
			summary = "Get Organization RESt API",
			description = "Get Organization RESt API is used to get a single organization object from the database"
	)
	@ApiResponse(
			responseCode = "200",
			description = "HTTP Status 200 SUCCESS"
	)
	@GetMapping("{code}")
	public ResponseEntity<OrganizationDto> getOrganizationCode(@PathVariable("code") String organizationCode) {
		OrganizationDto organizationDto = organizationService.getOrganizationByCode(organizationCode);
		return new ResponseEntity<>(organizationDto, HttpStatus.OK);
	}
}
