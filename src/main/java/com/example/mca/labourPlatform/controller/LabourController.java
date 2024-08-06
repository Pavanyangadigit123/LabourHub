package com.example.mca.labourPlatform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mca.labourPlatform.dto.LabourDto;
import com.example.mca.labourPlatform.dto.UserDto;
import com.example.mca.labourPlatform.exception.LabourHubException;
import com.example.mca.labourPlatform.model.Labour;
import com.example.mca.labourPlatform.rest.LabourHubResponse;
import com.example.mca.labourPlatform.service.LabourService;
import com.example.mca.labourPlatform.service.UserService;

@RestController
@RequestMapping("/labour")
public class LabourController {

	@Autowired
	private LabourService labourService;

	@Autowired
	private UserService userService;

	@GetMapping
	public LabourHubResponse getLabours() {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			List<LabourDto> listOfDto = labourService.getLabours();
			status = HttpStatus.OK;
			return new LabourHubResponse(listOfDto, status);
		} catch (LabourHubException e) {
			message = "Failed to retrieve Labour data; " + e.getMessage();
		} catch (Exception e) {
			message = "Internal server error" + e.getMessage();
		}

		return new LabourHubResponse(message, status);
	}

	@PostMapping
	public LabourHubResponse createLabour(@RequestBody UserDto userDto) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			message= labourService.createLabour(userDto);
			//if(newDto!=null) {
			status = HttpStatus.OK;
			//return new LabourHubResponse(newDto, status);
			//}
		} catch (LabourHubException e) {
			message = "Error while creating Labour; " ;
		} catch (Exception e) {
			message = "Internal Server Error." + e.getMessage();
		}

		return new LabourHubResponse(message, status);

	}

	@PutMapping("/labour-id/{id}")
	public LabourHubResponse updateLabour(@PathVariable(name = "id") Integer id, @RequestBody Labour labour) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			message= labourService.updateLabour(id, labour);
			status = HttpStatus.OK;
			
		} catch (LabourHubException exception) {
			message = "Failed to Update User !!" ;
		} catch (Exception exception) {
			message = "Internal Server Error !!" ;
		}
		return new LabourHubResponse(message, status);
	}

	@DeleteMapping("/labour-id/{id}")
	public LabourHubResponse deleteLabour(@PathVariable(name = "id") Integer id) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			message = labourService.deleteLabour(id);
			status = HttpStatus.OK;
		} catch (LabourHubException exception) {
			message = "Failed to delete User !!" + exception.getMessage();
		} catch (Exception exception) {
			message = "Internal Server Error !!" + exception.getMessage();
		}
		return new LabourHubResponse(message, status);

	}
}
