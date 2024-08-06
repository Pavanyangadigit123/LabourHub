package com.example.mca.labourPlatform.dto;

import java.time.LocalDateTime;

public class FeedbackDto {
	private Integer id;
	private LocalDateTime feedbackDate;
	private String feedbackDescription;
	private Integer rating;
	
	private Integer userId;
	private Integer labourerId;
	
	
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
	public Integer getLabourerId() {
		return labourerId;
	}
	public void setLabourerId(Integer labourerId) {
		this.labourerId = labourerId;
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

}
