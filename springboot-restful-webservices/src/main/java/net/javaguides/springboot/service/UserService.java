package net.javaguides.springboot.service;

import java.util.List;

import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;

public interface UserService {
	UserDto createUser(UserDto userDto);
	
	UserDto getUserById(Long userId);
	
	List<UserDto> getAllUsers();
	
	UserDto updateUser(UserDto userDto);
	
	void deleteUser(Long userId);
}
