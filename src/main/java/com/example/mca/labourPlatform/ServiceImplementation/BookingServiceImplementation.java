package com.example.mca.labourPlatform.ServiceImplementation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mca.labourPlatform.dao.BookingRepository;
import com.example.mca.labourPlatform.dao.LabourRepository;
import com.example.mca.labourPlatform.dao.UserRepository;
import com.example.mca.labourPlatform.dto.BookingDto;
import com.example.mca.labourPlatform.exception.LabourHubException;
import com.example.mca.labourPlatform.model.Booking;
import com.example.mca.labourPlatform.model.Labour;
import com.example.mca.labourPlatform.model.User;
import com.example.mca.labourPlatform.service.BookingService;
import com.example.mca.labourPlatform.util.BookingUtil;

@Service
public class BookingServiceImplementation implements BookingService{

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LabourRepository labourRepository;

	public List<BookingDto> getBookings() throws LabourHubException {
		try {
			List<BookingDto> listOfDtos = bookingRepository.findAll().stream()
					.map(BookingUtil::convertBookingEntityToDto).collect(Collectors.toList());
			return listOfDtos;
		} catch (Exception e) {
			throw new LabourHubException(e.getMessage());
		}
	}

	public String createNewBooking(Booking booking, Integer userId, Integer labourId) throws LabourHubException {
		try {
			User user = userRepository.findById(userId).orElse(null);
			Labour labour = labourRepository.findById(labourId).orElse(null);
			if (user == null || labour == null) {
				throw new LabourHubException("User doesn't exist.");
			}
			booking.setUser(user);
			booking.setLabour(labour);
			booking.setBookingDate(LocalDateTime.now());
			booking.setStartTime(LocalDateTime.now());
			booking.setEndTime(LocalDateTime.now());
			bookingRepository.save(booking);
			return "Booking created successfully";
		} catch (Exception e) {
			throw new LabourHubException(e.getMessage());
		}
	}

}
