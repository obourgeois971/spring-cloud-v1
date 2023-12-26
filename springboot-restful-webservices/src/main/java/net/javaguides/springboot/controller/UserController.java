package net.javaguides.springboot.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.service.UserService;

@Tag(
		name = "CRUD RESt APIs for User Resource",
		description = "Create User APIs - Update User, Get User, Get All Users, Delete User"
)
@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

	private UserService userService;
	
	// Build create User REST API
	// http://localhost:8080/api/users
	@Operation(
			summary = "Create User REST API",
			description = "Create User REST API is used to save in a database"
	)
	@ApiResponse(
			responseCode = "201",
			description = "HTTP Status 201 CREATED"
	)
	@PostMapping
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user) {
		UserDto savedUser = userService.createUser(user);
		return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	}
	
	// Build get user by id REST API
	// http://localhost:8080/api/users/1
	@Operation(
			summary = "Get User By Id REST API",
			description = "Get User By Id REST API is used to get all users from the database"
	)
	@ApiResponse(
			responseCode = "200",
			description = "HTTP Status 200 SUCCESS"
	)
	@GetMapping("{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId) {
		UserDto user = userService.getUserById(userId);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	// Build Get All Users Rest Api
	// http://localhost:8080/api/users
	@Operation(
			summary = "Get All Users REST API",
			description = "Get All Users REST API is used to get a single user from the database"
	)
	@ApiResponse(
			responseCode = "200",
			description = "HTTP Status 200 SUCCESS"
	)
	@GetMapping
	public ResponseEntity<List<UserDto>> getAllUsers() {
		List<UserDto> users = userService.getAllUsers();
		return new ResponseEntity<>(users,HttpStatus.OK);
	}
	
	// Build Updated User REST API
	// http//localhost:8080/api/users/1
	@Operation(
			summary = "Update User REST API",
			description = "Update User REST API is used to update a particular user in the database"
	)
	@ApiResponse(
			responseCode = "200",
			description = "HTTP Status 200 SUCCESS"
	)
	@PutMapping("{id}")
	public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId, @RequestBody @Valid UserDto user) {
		user.setId(userId);
		UserDto updatedUser = userService.updateUser(user);
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}
	
	// Build Delete User REST API
	// http://localhost:8080/api/users/1
	@Operation(
			summary = "Delete User REST API",
			description = "Delete User REST API is used to delete a particular user from the database"
	)
	@ApiResponse(
			responseCode = "200",
			description = "HTTP Status 200 SUCCESS"
	)
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
		userService.deleteUser(userId);
		return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
	}
	
}
