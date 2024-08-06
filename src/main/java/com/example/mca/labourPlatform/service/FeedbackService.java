package com.example.mca.labourPlatform.service;

import java.util.List;

import com.example.mca.labourPlatform.dto.FeedbackDto;
import com.example.mca.labourPlatform.exception.LabourHubException;
import com.example.mca.labourPlatform.model.Feedback;

public interface FeedbackService {

	public abstract String createFeedback(Feedback feedback, Integer userId, Integer labourId)
			throws LabourHubException;

	public abstract List<FeedbackDto> getFeedbacks() throws LabourHubException;

	public abstract String deleteFeedback(Integer id) throws LabourHubException;
}
