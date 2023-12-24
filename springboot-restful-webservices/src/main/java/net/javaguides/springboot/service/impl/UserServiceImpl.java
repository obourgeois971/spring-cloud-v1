package net.javaguides.springboot.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.exception.EmailAlreadyExistsException;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.mapper.AutoUserMapper;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.service.UserService;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		
		Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
		if(optionalUser.isPresent()) {
			throw new EmailAlreadyExistsException("Email Already Exists for User");
		}
		
		// Convert UserDto into User JPA Entity
		// 1. User user = UserMapper.mapToUser(userDto);
		// 2. User user = modelMapper.map(userDto, User.class);
		User user = AutoUserMapper.MAPPER.mapToUser(userDto);

		User savedUser = userRepository.save(user);

		// Convert User JPA entity to UserDto
		// 1. UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);
		// 2. UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);
		UserDto savedUserDto = AutoUserMapper.MAPPER.mapToUserDto(savedUser);
		
		return savedUserDto;
	}

	@Override
	public UserDto getUserById(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("User", "id", userId)
		);
		// 1. return UserMapper.mapToUserDto(user);
		// 2. return modelMapper.map(user, UserDto.class);
		return AutoUserMapper.MAPPER.mapToUserDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = userRepository.findAll();
		/* 1. return users.stream()
					.map(UserMapper::mapToUserDto)
					.collect(Collectors.toList()); */
		/* 2. return users.stream()
					.map((user) -> modelMapper.map(user, UserDto.class))
					.collect(Collectors.toList()); */
		return users.stream()
				.map((user) -> AutoUserMapper.MAPPER.mapToUserDto(user))
				.collect(Collectors.toList());
	}

	@Override
	public UserDto updateUser(UserDto userDto) {
		User existingUser = userRepository.findById(userDto.getId()).orElseThrow(
				() -> new ResourceNotFoundException("User", "id", userDto.getId())
		);
		existingUser.setFirstName(userDto.getFirstName());
		existingUser.setLastName(userDto.getLastName());
		existingUser.setEmail(userDto.getEmail());
		User updatedUser = userRepository.save(existingUser);
		// 1. return UserMapper.mapToUserDto(updatedUser);
		// 2. return modelMapper.map(updatedUser, UserDto.class);
		return AutoUserMapper.MAPPER.mapToUserDto(updatedUser);
	}

	@Override
	public void deleteUser(Long userId) {
		User existingUser = userRepository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("User", "id", userId)
		);
		userRepository.deleteById(userId);
	}

}
