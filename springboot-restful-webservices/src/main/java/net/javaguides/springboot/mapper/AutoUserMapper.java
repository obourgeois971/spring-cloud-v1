package net.javaguides.springboot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;

@Mapper
public interface AutoUserMapper {
	
	AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);
	
	UserDto mapToUserDto(User user);

	User mapToUser(UserDto userDto);
}
