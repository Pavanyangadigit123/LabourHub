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
	private LocalDateTime startTime= LocalDateTime.now();

	@Column(name = "end_time",nullable=false)
	private LocalDateTime endTime;

	@Column(name = "booking_status",nullable=false)
	private String bookingStatus;
	
	@Column(name="amount")
	private Double amount;
	
	@Column(name="order_status")
	private String orderStatus;
	
	@Column(name="razor_pay_order_id")
	private String razorPayOrderID;

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getRazorPayOrderID() {
		return razorPayOrderID;
	}

	public void setRazorPayOrderID(String razorPayOrderID) {
		this.razorPayOrderID = razorPayOrderID;
	}

	public PaymentTransaction getPaymentTransaction() {
		return paymentTransaction;
	}

	public void setPaymentTransaction(PaymentTransaction paymentTransaction) {
		this.paymentTransaction = paymentTransaction;
	}

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
		return Objects.hash(amount, bookingDate, bookingStatus, endTime, id, labour, orderStatus, paymentTransaction,
				razorPayOrderID, startTime, user);
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
		return Objects.equals(amount, other.amount) && Objects.equals(bookingDate, other.bookingDate)
				&& Objects.equals(bookingStatus, other.bookingStatus) && Objects.equals(endTime, other.endTime)
				&& Objects.equals(id, other.id) && Objects.equals(labour, other.labour)
				&& Objects.equals(orderStatus, other.orderStatus)
				&& Objects.equals(paymentTransaction, other.paymentTransaction)
				&& Objects.equals(razorPayOrderID, other.razorPayOrderID) && Objects.equals(startTime, other.startTime)
				&& Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "Booking [id=" + id + ", bookingDate=" + bookingDate + ", startTime=" + startTime + ", endTime="
				+ endTime + ", bookingStatus=" + bookingStatus + ", amount=" + amount + ", orderStatus=" + orderStatus
				+ ", razorPayOrderID=" + razorPayOrderID + ", user=" + user + ", labour=" + labour
				+ ", paymentTransaction=" + paymentTransaction + "]";
	}
	
	
}
