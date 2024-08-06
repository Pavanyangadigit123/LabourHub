package com.example.mca.labourPlatform.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mca.labourPlatform.dto.SkillDto;
import com.example.mca.labourPlatform.exception.LabourHubException;
import com.example.mca.labourPlatform.rest.LabourHubResponse;
import com.example.mca.labourPlatform.service.SkillService;


@RestController
@RequestMapping("/skill")
public class SkillController {

	Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private SkillService skillService;

	@GetMapping
	public LabourHubResponse getSkills() {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			List<SkillDto> listOfDto = skillService.getSkills();
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
	public LabourHubResponse addSkills(@RequestBody SkillDto skillDto) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			SkillDto newDto = skillService.addSkills(skillDto);
			if (newDto != null) {
				status = HttpStatus.OK;
				return new LabourHubResponse(newDto, status);
			}
		} catch (LabourHubException e) {
			message = "Error while creating skill; " + e.getMessage();
		} catch (Exception e) {
			message = "Internal Server Error." + e.getMessage();
		}

		return new LabourHubResponse(message, status);

	}

	@DeleteMapping("/skill-id/{id}")
	public LabourHubResponse deleteUser(@PathVariable(name = "id") Integer id) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			message = skillService.deleteUser(id);
			status = HttpStatus.OK;
		} catch (LabourHubException exception) {
			message = "Failed to delete Skill !!" + exception.getMessage();
		} catch (Exception exception) {
			message = "Internal Server Error !!" + exception.getMessage();
		}
		return new LabourHubResponse(message, status);

	}
}
