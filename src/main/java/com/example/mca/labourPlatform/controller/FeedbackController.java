package com.example.mca.labourPlatform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mca.labourPlatform.dto.FeedbackDto;
import com.example.mca.labourPlatform.exception.LabourHubException;
import com.example.mca.labourPlatform.model.Feedback;
import com.example.mca.labourPlatform.rest.LabourHubResponse;
import com.example.mca.labourPlatform.service.FeedbackService;

@RestController
@RequestMapping(path = "/feedback")
public class FeedbackController {
	@Autowired
	private FeedbackService feedbackService;

	@GetMapping
	public LabourHubResponse getFeedbacks() {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			List<FeedbackDto> listOfDto = feedbackService.getFeedbacks();
			status = HttpStatus.OK;
			return new LabourHubResponse(listOfDto, status);
		} catch (LabourHubException e) {
			message = "Failed to retrieve feedbcak data; " + e.getMessage();
		} catch (Exception e) {
			message = "Internal server error" + e.getMessage();
		}

		return new LabourHubResponse(message, status);
	}

	@PostMapping("user-id/{userId}/labour-id/{labourId}")
	public LabourHubResponse createFeedback(@PathVariable(name = "userId") Integer userId,
			@PathVariable(name = "labourId") Integer labourId, @RequestBody Feedback feedback) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			message = feedbackService.createFeedback(feedback, userId, labourId);
			status = HttpStatus.OK;
		} catch (LabourHubException e) {
			message = "Error while creating user; " + e.getMessage();
		} catch (Exception e) {
			message = "Internal Server Error." + e.getMessage();
		}

		return new LabourHubResponse(message, status);

	}
	
	@DeleteMapping("/feedback-id/{id}")
	public LabourHubResponse deleteFeedback(@PathVariable(name = "id") Integer id) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			message = feedbackService.deleteFeedback(id);
			status = HttpStatus.OK;
		} catch (LabourHubException exception) {
			message = "Failed to delete feedback !!" + exception.getMessage();
		} catch (Exception exception) {
			message = "Internal Server Error !!" + exception.getMessage();
		}
		return new LabourHubResponse(message, status);

	}

}
