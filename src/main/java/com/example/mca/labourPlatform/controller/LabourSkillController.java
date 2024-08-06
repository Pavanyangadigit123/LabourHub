package com.example.mca.labourPlatform.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mca.labourPlatform.dto.LabourSkillDto;
import com.example.mca.labourPlatform.exception.LabourHubException;
import com.example.mca.labourPlatform.model.LabourSkill;
import com.example.mca.labourPlatform.rest.LabourHubResponse;
import com.example.mca.labourPlatform.service.LabourSkillService;

@RestController
@RequestMapping("/labourskill")
public class LabourSkillController {

	Logger logger = LoggerFactory.getLogger(LabourSkillController.class);

	@Autowired
	private LabourSkillService labourSkillService;

	@GetMapping
	public LabourHubResponse getLabourSkills() {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			List<LabourSkillDto> listOfDto = labourSkillService.getLabourSkills();
			status = HttpStatus.OK;
			return new LabourHubResponse(listOfDto, status);
		} catch (LabourHubException e) {
			message = "Failed to retrieve labourSkills data; " + e.getMessage();
		} catch (Exception e) {
			message = "Internal server error" + e.getMessage();
		}

		return new LabourHubResponse(message, status);
	}

	@PostMapping("/assign-labourskill/labour-id/{labourId}/skill-id/{skillId}")
	public LabourHubResponse assignSkillToLabour(@PathVariable(name = "labourId") Integer labourId,
			@PathVariable(name = "skillId") Integer skillId, @RequestBody LabourSkill labourskill) {
		logger.info("Labour Id: " + labourId + "\n  Id: " + skillId);

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String statusMessage = "";
		try {
			labourSkillService.assignSkillToLabour(labourskill, labourId, skillId);
			status = HttpStatus.OK;
			statusMessage = "Assigned skill to Labour successfuly.";
		} catch (LabourHubException e) {
			statusMessage = "Error while assigning skill to Labour; " + e.getMessage();
		} catch (Exception e) {
			statusMessage = "Error while assigning skill to Labour.";
		}

		return new LabourHubResponse(statusMessage, status);
	}

}
