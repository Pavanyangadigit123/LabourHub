package com.example.mca.labourPlatform.service;

import java.util.List;

import com.example.mca.labourPlatform.dto.PaymentTransactionDto;
import com.example.mca.labourPlatform.exception.LabourHubException;
import com.example.mca.labourPlatform.model.PaymentTransaction;

public interface PaymentTransactionService {

	public abstract String createTransaction(PaymentTransaction paymentTransaction, Integer bookingId) throws LabourHubException;

	public abstract List<PaymentTransactionDto> getTransactions() throws LabourHubException;

}
