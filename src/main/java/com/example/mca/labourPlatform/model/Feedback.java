package com.example.mca.labourPlatform.model;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Feedback {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	
	@Column(name="feedback_date")
	private LocalDateTime feedbackDate;
	
	@Column(name="feedback_description")
	private String feedbackDescription;
	
	@Column(name="rating")
	private Integer rating;
	
	@ManyToOne
	@JoinColumn(name="user_id",referencedColumnName = "id",nullable=false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name="labour_id",referencedColumnName = "id",nullable=false)
	private Labour labour;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getFeedbackDate() {
		return feedbackDate;
	}

	public void setFeedbackDate(LocalDateTime feedbackDate) {
		this.feedbackDate = feedbackDate;
	}

	public String getFeedbackDescription() {
		return feedbackDescription;
	}

	public void setFeedbackDescription(String feedbackDescription) {
		this.feedbackDescription = feedbackDescription;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
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
		return Objects.hash(feedbackDate, feedbackDescription, id, rating);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Feedback other = (Feedback) obj;
		return Objects.equals(feedbackDate, other.feedbackDate)
				&& Objects.equals(feedbackDescription, other.feedbackDescription) && Objects.equals(id, other.id)
				&& Objects.equals(rating, other.rating);
	}

	@Override
	public String toString() {
		return "Feedback [Id=" + id + ", feedbackDate=" + feedbackDate + ", feedbackDescription="
				+ feedbackDescription + ", rating=" + rating + "]";
	}
	
	
	
	

}
