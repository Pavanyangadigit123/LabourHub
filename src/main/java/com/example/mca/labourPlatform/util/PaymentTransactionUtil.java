package com.example.mca.labourPlatform.util;

import org.springframework.beans.BeanUtils;

import com.example.mca.labourPlatform.dto.PaymentTransactionDto;
import com.example.mca.labourPlatform.model.PaymentTransaction;

public class PaymentTransactionUtil {
	public static PaymentTransactionDto convertPaymentTransactionEntityToDto(PaymentTransaction paymentTransaction)
	{
		PaymentTransactionDto dto=new PaymentTransactionDto();
		BeanUtils.copyProperties(paymentTransaction, dto);
		dto.setBookingId(paymentTransaction.getBooking().getId());
		return dto;
	}
	
	public static PaymentTransaction convertPaymentTransactionToEntity(PaymentTransactionDto dto)
	{
		PaymentTransaction paymentTransaction=new PaymentTransaction();
		BeanUtils.copyProperties(dto,paymentTransaction);
		return paymentTransaction;
	}
}
