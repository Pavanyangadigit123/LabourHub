package com.example.mca.labourPlatform.service;

import java.util.List;

import com.example.mca.labourPlatform.dto.UserDto;
import com.example.mca.labourPlatform.exception.LabourHubException;

public interface UserService {

	public abstract List<UserDto> getUsers() throws LabourHubException;
	
	public abstract UserDto createUser(UserDto userDto) throws LabourHubException;
	
	public abstract String updateUser(Integer id, UserDto dto) throws LabourHubException;
	
	public abstract String deleteUser(Integer id) throws LabourHubException;

}
