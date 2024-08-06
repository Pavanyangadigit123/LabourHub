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
import com.example.mca.labourPlatform.dto.PaymentTransactionDto;
import com.example.mca.labourPlatform.exception.LabourHubException;
import com.example.mca.labourPlatform.model.PaymentTransaction;
import com.example.mca.labourPlatform.rest.LabourHubResponse;
import com.example.mca.labourPlatform.service.PaymentTransactionService;

@RestController
@RequestMapping(path = "/payment")
public class PaymentTransactionController {

	@Autowired
	private PaymentTransactionService paymentTransactionService;
	
	@GetMapping
	public LabourHubResponse getTransactions()
	{
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			List<PaymentTransactionDto> listOfDto = paymentTransactionService.getTransactions();
			status = HttpStatus.OK;
			return new LabourHubResponse(listOfDto, status);
		} catch (LabourHubException e) {
			message = "Failed to transcation data; "  ;
		} catch (Exception e) {
			message = "Internal server error" + e.getMessage();
		}

		return new LabourHubResponse(message, status);
	}
	

	@PostMapping("/booking-id/{bookingId}")
	public LabourHubResponse createTransaction(@PathVariable(name = "bookingId") Integer bookingId,
			@RequestBody PaymentTransaction paymentTransaction) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			message = paymentTransactionService.createTransaction(paymentTransaction, bookingId);
			status = HttpStatus.OK;
		} catch (LabourHubException e) {
			message = "Error while creating user; " + e.getMessage();
		} catch (Exception e) {
			message = "Internal Server Error." + e.getMessage();
		}

		return new LabourHubResponse(message, status);

	}

}
