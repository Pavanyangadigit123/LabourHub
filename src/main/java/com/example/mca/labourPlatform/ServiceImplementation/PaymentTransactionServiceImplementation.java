package com.example.mca.labourPlatform.ServiceImplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mca.labourPlatform.dao.BookingRepository;
import com.example.mca.labourPlatform.dao.PaymentTransactionRepository;
import com.example.mca.labourPlatform.dto.PaymentTransactionDto;
import com.example.mca.labourPlatform.exception.LabourHubException;
import com.example.mca.labourPlatform.model.Booking;
import com.example.mca.labourPlatform.model.PaymentTransaction;
import com.example.mca.labourPlatform.service.PaymentTransactionService;
import com.example.mca.labourPlatform.util.PaymentTransactionUtil;

@Service
public class PaymentTransactionServiceImplementation implements PaymentTransactionService {

	@Autowired
	private PaymentTransactionRepository paymentTransactionRepository;

	@Autowired
	private BookingRepository bookingRepository;
	
	public List<PaymentTransactionDto> getTransactions() throws LabourHubException{
	try {
		List<PaymentTransactionDto> listOfDtos = paymentTransactionRepository.findAll().stream()
				.map(PaymentTransactionUtil::convertPaymentTransactionEntityToDto).collect(Collectors.toList());
		return listOfDtos;
	} catch (Exception e) {
		throw new LabourHubException(e.getMessage());
	}
}

	public String createTransaction(PaymentTransaction paymentTransaction, Integer bookingId) throws LabourHubException {
		try {
			Booking booking = bookingRepository.findById(bookingId).orElse(null);
			if (booking == null) {
				throw new LabourHubException("BookingId doesn't exist");
			}
			paymentTransaction.setBooking(booking);
			paymentTransactionRepository.save(paymentTransaction);
			return "Transaction completed Successfully";
		} catch (Exception e) {
			throw new LabourHubException(e.getMessage());
		}

	}
}
