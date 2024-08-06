package com.example.mca.labourPlatform.util;

import org.springframework.beans.BeanUtils;

import com.example.mca.labourPlatform.dto.BookingDto;
import com.example.mca.labourPlatform.model.Booking;

public class BookingUtil {
	public static BookingDto convertBookingEntityToDto(Booking booking)
	{
		BookingDto dto=new BookingDto();
		BeanUtils.copyProperties(booking, dto);
		dto.setUserId(booking.getUser().getId());
		dto.setLaborerId(booking.getLabour().getId());
		return dto;
	}
	
	public static Booking convertBookingDtoToEntity(BookingDto dto)
	{
		Booking booking=new Booking();
		BeanUtils.copyProperties(dto,booking);
		return booking;
	}
}
