package com.example.mca.labourPlatform.util;

import org.springframework.beans.BeanUtils;

import com.example.mca.labourPlatform.dto.UserDto;
import com.example.mca.labourPlatform.model.User;

public class UserUtil {
	public static UserDto convertUserEntityToDto(User user)
	{
		UserDto dto=new UserDto();
		BeanUtils.copyProperties(user, dto);
		return dto;
	}
	
	public static User convertUserDtoToEntity(UserDto dto)
	{
		User user=new User();
		BeanUtils.copyProperties(dto,user);
		
		return user;
	}

}
