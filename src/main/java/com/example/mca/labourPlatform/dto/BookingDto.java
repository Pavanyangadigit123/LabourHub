package com.example.mca.labourPlatform.dto;

import java.time.LocalDateTime;

public class BookingDto {
	
	private Integer id;
	private LocalDateTime bookingDate;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private String bookingStatus;
	
	private Integer userId;
	private Integer laborerId;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getLaborerId() {
		return laborerId;
	}
	public void setLaborerId(Integer laborerId) {
		this.laborerId = laborerId;
	}

	public LocalDateTime getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(LocalDateTime bookingDate) {
		this.bookingDate = bookingDate;
	}
	public LocalDateTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
	public LocalDateTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}
	public String getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	
}
