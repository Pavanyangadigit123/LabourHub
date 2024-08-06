package com.example.mca.labourPlatform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mca.labourPlatform.dto.BookingDto;
import com.example.mca.labourPlatform.exception.LabourHubException;
import com.example.mca.labourPlatform.model.Booking;
import com.example.mca.labourPlatform.rest.LabourHubResponse;
import com.example.mca.labourPlatform.service.BookingService;

@RestController
@RequestMapping("/booking")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@GetMapping
	public LabourHubResponse getBookings() {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			List<BookingDto> listOfDto = bookingService.getBookings();
			status = HttpStatus.OK;
			return new LabourHubResponse(listOfDto, status);
		} catch (LabourHubException e) {
			message = "Failed to retrieve booking data; " + e.getMessage();
		} catch (Exception e) {
			message = "Internal server error" + e.getMessage();
		}

		return new LabourHubResponse(message, status);
	}

	@PostMapping("/user-id/{userId}/labour-id/{labourId}")
	public LabourHubResponse createNewBooking(@PathVariable(name = "userId") Integer userId,
			@PathVariable(name = "labourId") Integer labourId, @RequestBody Booking booking) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			message = bookingService.createNewBooking(booking,userId,labourId);
			status = HttpStatus.OK;
		} catch (LabourHubException e) {
			message = "Error while creating new booking; " ;
		} catch (Exception e) {
			message = /* "Internal Server Error." + */ e.getMessage() ;
		}

		return new LabourHubResponse(message, status);

	}

}
