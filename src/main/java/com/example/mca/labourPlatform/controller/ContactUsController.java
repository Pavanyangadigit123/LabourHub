package com.example.mca.labourPlatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mca.labourPlatform.dto.ContactUsDto;
import com.example.mca.labourPlatform.rest.LabourHubResponse;
import com.example.mca.labourPlatform.service.ContactUsService;
import com.example.mca.labourPlatform.service.GoogleCaptchaVerificationService;

@RestController
@RequestMapping("/contact")
public class ContactUsController {

	@Autowired
	private GoogleCaptchaVerificationService googleCaptchaVerificationService;

	@Autowired
	private ContactUsService contactUsService;

	@PostMapping
	public LabourHubResponse addContactForm(@RequestBody ContactUsDto contactUsDto) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			if (googleCaptchaVerificationService.isvalidCaptcha(contactUsDto.getToken())) {
				contactUsService.saveContactDetails(contactUsDto);
			}
			status = HttpStatus.OK;
		} catch (Exception exception) {
			message = "Failed to send contact data !!" + exception.getLocalizedMessage();
		}
		return new LabourHubResponse(message, status);
	}
}
