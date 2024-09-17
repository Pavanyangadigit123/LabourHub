package com.example.mca.labourPlatform.ServiceImplementation;

import java.time.LocalDateTime;
import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

@Service
public class BookingServiceImplementation implements BookingService{

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LabourRepository labourRepository;
	
	@Value("${razorpay.key.id}")
	private String razorPayKey;

	@Value("${razorpay.secret.key}")
	private String razorPaySecret;

	// to communicate with razorPay- predefined
	private RazorpayClient client;


	public List<BookingDto> getBookings() throws LabourHubException {
		try {
			List<BookingDto> listOfDtos = bookingRepository.findAll().stream()
					.map(BookingUtil::convertBookingEntityToDto).collect(Collectors.toList());
			return listOfDtos;
		} catch (Exception e) {
			throw new LabourHubException(e.getMessage());
		}
	}

	public Booking createNewBooking(Booking booking, Integer userId, Integer labourId) throws LabourHubException {
		try {
			User user = userRepository.findById(userId).orElse(null);
			Labour labour = labourRepository.findById(labourId).orElse(null);
			if (user == null || labour == null) {
				throw new LabourHubException("User doesn't exist.");
			}
			JSONObject orderReq = new JSONObject();

			orderReq.put("amount", booking.getAmount() * 100); // amount in paisa
			orderReq.put("currency", "INR");
			orderReq.put("receipt", user.getEmail());

			this.client = new RazorpayClient(razorPayKey, razorPaySecret);

			// create order in razorpay
			Order razorpayOrder = client.orders.create(orderReq);
			
			booking.setUser(user);
			booking.setLabour(labour);
			booking.setBookingDate(LocalDateTime.now());
			booking.setStartTime(LocalDateTime.now());
			booking.setEndTime(LocalDateTime.now());
			
			booking.setRazorPayOrderID(razorpayOrder.get("id"));
			booking.setOrderStatus(razorpayOrder.get("status"));
			
			 booking=bookingRepository.save(booking);
			return booking;
		} catch (Exception e) {
			throw new LabourHubException(e.getMessage());
		}
	}
	
	public String updateOrder(String razorPayOrderID) throws LabourHubException {
		
		
		Booking booking=bookingRepository.findByRazorPayOrderID(razorPayOrderID);
		 if (booking == null) {
		        System.err.println("Booking not found for Razorpay Order ID: " + razorPayOrderID);
		        throw new IllegalArgumentException("Booking not found for Razorpay Order ID: " + razorPayOrderID);
		    }

		booking.setOrderStatus("PAYMENT_COMPLETED");
		return "Labour Booked successfully";
		}
	
	public String cancelBooking(Integer id) throws LabourHubException{
		Optional<Booking> booking = bookingRepository.findById(id);
		try {
			if (booking.isPresent()) {
				userRepository.deleteById(id);
				Integer bookingId = booking.get().getId();
				return "Booking " + bookingId + " is canceled successfully.";
			} else {
				throw new LabourHubException("Booking id doesn't exist!!.");
			}
		} catch (Exception e) {
			throw new LabourHubException(e.getMessage());
		}
	}

}
