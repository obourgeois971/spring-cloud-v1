package net.javaguides.springboot.service;

import java.util.List;

import net.javaguides.springboot.entity.User;

public interface UserService {
	User createUser(User user);
	
	User getUserById(Long userId);
	
	List<User> getAllUsers();
	
	User updateUser(User user);
	
	void deleteUser(Long userId);
}
