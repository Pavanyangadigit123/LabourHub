package com.example.mca.labourPlatform.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mca.labourPlatform.dto.UserDto;
import com.example.mca.labourPlatform.exception.LabourHubException;
import com.example.mca.labourPlatform.rest.LabourHubResponse;
import com.example.mca.labourPlatform.service.UserService;

@RestController
@CrossOrigin
@RequestMapping(path = "/user")
public class UserController {

	Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@GetMapping
	public LabourHubResponse getUsers() {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			List<UserDto> listOfDto = userService.getUsers();
			status = HttpStatus.OK;
			return new LabourHubResponse(listOfDto, status);
		} catch (LabourHubException e) {
			message = "Failed to retrieve user data; " + e.getMessage();
		} catch (Exception e) {
			message = "Internal server error" + e.getMessage();
		}

		return new LabourHubResponse(message, status);
	}

	@PostMapping
	public LabourHubResponse createUser(@RequestBody UserDto userDto) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			UserDto newDto = userService.createUser(userDto);
			if (newDto != null) {
				status = HttpStatus.OK;
				return new LabourHubResponse(newDto, status);
			}
		} catch (LabourHubException e) {
			message = "Error while creating user; " + e.getMessage();
		} catch (Exception e) {
			message = "Internal Server Error." + e.getMessage();
		}

		return new LabourHubResponse(message, status);

	}

	@PutMapping("/user-id/{id}")
	public LabourHubResponse updateUser(@PathVariable(name = "id") Integer id, @RequestBody UserDto userDto) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			message = userService.updateUser(id, userDto);
			status = HttpStatus.OK;
		} catch (LabourHubException exception) {
			message = "Failed to Update User !!" + exception.getMessage();
		} catch (Exception exception) {
			message = "Internal Server Error !!" + exception.getMessage();
		}
		return new LabourHubResponse(message, status);
	}

	@DeleteMapping("/user-id/{id}")
	public LabourHubResponse deleteUser(@PathVariable(name = "id") Integer id) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			message = userService.deleteUser(id);
			status = HttpStatus.OK;
		} catch (LabourHubException exception) {
			message = "Failed to delete User !!" + exception.getMessage();
		} catch (Exception exception) {
			message = "Internal Server Error !!" + exception.getMessage();
		}
		return new LabourHubResponse(message, status);

	}
}
