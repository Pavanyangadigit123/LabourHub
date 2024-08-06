package com.example.mca.labourPlatform.service;

import java.util.List;

import com.example.mca.labourPlatform.dto.BookingDto;
import com.example.mca.labourPlatform.exception.LabourHubException;
import com.example.mca.labourPlatform.model.Booking;

public interface BookingService {

	public abstract List<BookingDto> getBookings() throws LabourHubException;

	public abstract String createNewBooking(Booking booking, Integer userId, Integer labourId)
			throws LabourHubException;

}
