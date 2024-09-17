package com.example.mca.labourPlatform.service;

import java.util.List;

import com.example.mca.labourPlatform.dto.UserDto;
import com.example.mca.labourPlatform.exception.LabourHubException;
import com.example.mca.labourPlatform.model.User;

public interface UserService {

	public abstract List<UserDto> getUsers() throws LabourHubException;
	
	public abstract UserDto createUser(UserDto userDto) throws LabourHubException;
	
	public abstract String updateUser(Integer id, UserDto dto) throws LabourHubException;
	
	public abstract String deleteUser(Integer id) throws LabourHubException;

	String signIn(UserDto userDto);

// List<User> getLabourByFilter(String skill, String city, String area, String state) throws LabourHubException;

public abstract String signInWithGoogle(String accessToken) throws LabourHubException;

public abstract UserDto getUsersByEmail(String email) throws LabourHubException;

}
