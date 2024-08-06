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

import com.example.mca.labourPlatform.dto.NotificationDto;
import com.example.mca.labourPlatform.exception.LabourHubException;
import com.example.mca.labourPlatform.model.Notification;
import com.example.mca.labourPlatform.rest.LabourHubResponse;
import com.example.mca.labourPlatform.service.NotificationService;

@RestController
@RequestMapping(path = "/notification")
public class NotificationController {

	@Autowired
	private NotificationService notificationService;

	@GetMapping
	public LabourHubResponse getNotifications() {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			List<NotificationDto> listOfDto = notificationService.getNotifications();
			status = HttpStatus.OK;
			return new LabourHubResponse(listOfDto, status);
		} catch (LabourHubException e) {
			message = "Failed to retrieve notification data; " + e.getMessage();
		} catch (Exception e) {
			message = "Internal server error" + e.getMessage();
		}

		return new LabourHubResponse(message, status);
	}

	@PostMapping("user-id/{userId}")
	public LabourHubResponse addNotification(@PathVariable(name = "userId") Integer userId,
			@RequestBody Notification notification) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			message = notificationService.addNotification(notification, userId);
			status = HttpStatus.OK;
		} catch (LabourHubException e) {
			message = "Error while creating notification; " + e.getMessage();
		} catch (Exception e) {
			message = "Internal Server Error." + e.getMessage();
		}

		return new LabourHubResponse(message, status);

	}

	@DeleteMapping("/notification-id/{id}")
	public LabourHubResponse deleteNotification(@PathVariable(name = "id") Integer id) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			message = notificationService.deleteNotification(id);
			status = HttpStatus.OK;
		} catch (LabourHubException exception) {
			message = "Failed to notification feedback !!" + exception.getMessage();
		} catch (Exception exception) {
			message = "Internal Server Error !!" + exception.getMessage();
		}
		return new LabourHubResponse(message, status);

	}

}
