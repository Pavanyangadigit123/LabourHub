package com.example.mca.labourPlatform.model;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class PaymentTransaction {
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	
	@Column(name="amount",nullable=false)
	private Double amount;
	
	@Column(name="payment_method")
	private String paymentMethod;
	
	@Column(name="transaction_status",nullable=false)
	private String transactionStatus;
	
	@Column(name="transaction_date",nullable=false)
	private LocalDateTime transactionDate;
	
	 @OneToOne
	 @JoinColumn(name = "booking_id",referencedColumnName = "id", nullable = false)
	 private Booking booking;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, id, paymentMethod, transactionDate, transactionStatus);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaymentTransaction other = (PaymentTransaction) obj;
		return Objects.equals(amount, other.amount) && Objects.equals(id, other.id)
				&& Objects.equals(paymentMethod, other.paymentMethod)
				&& Objects.equals(transactionDate, other.transactionDate)
				&& Objects.equals(transactionStatus, other.transactionStatus);
	}

	@Override
	public String toString() {
		return "PaymentTransaction [id=" + id  + ", amount=" + amount + ", paymentMethod="
				+ paymentMethod + ", transactionStatus=" + transactionStatus + ", transactionDate=" + transactionDate
				+ "]";
	}
	
	
			

}
