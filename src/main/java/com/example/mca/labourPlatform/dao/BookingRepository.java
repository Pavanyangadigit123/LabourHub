package com.example.mca.labourPlatform.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mca.labourPlatform.model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
//	List<Bookings> findByUserId(Integer userId);
	
	public Booking findByRazorPayOrderID(String orderId);
}
