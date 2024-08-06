package com.example.mca.labourPlatform.model;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Booking {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;

	@Column(name = "booking_date",nullable=false)
	private LocalDateTime bookingDate;

	@Column(name = "start_time",nullable=false)
	private LocalDateTime startTime;

	@Column(name = "end_time",nullable=false)
	private LocalDateTime endTime;

	@Column(name = "booking_status",nullable=false)
	private String bookingStatus;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "labour_id", referencedColumnName = "id", nullable = false)
	private Labour labour;

	@OneToOne(mappedBy = "booking")
	private PaymentTransaction paymentTransaction;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Labour getLabour() {
		return labour;
	}

	public void setLabour(Labour labour) {
		this.labour = labour;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(bookingDate, bookingStatus, endTime, id, startTime);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Booking other = (Booking) obj;
		return Objects.equals(bookingDate, other.bookingDate) && Objects.equals(bookingStatus, other.bookingStatus)
				&& Objects.equals(endTime, other.endTime) && Objects.equals(id, other.id)
				&& Objects.equals(startTime, other.startTime);
	}

	@Override
	public String toString() {
		return "Bookings [id=" + id + ", bookingDate=" + bookingDate + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", bookingStatus=" + bookingStatus + "]";
	}

}
