package com.example.mca.labourPlatform.ServiceImplementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mca.labourPlatform.dao.FeedbackRepository;
import com.example.mca.labourPlatform.dao.LabourRepository;
import com.example.mca.labourPlatform.dao.UserRepository;
import com.example.mca.labourPlatform.dto.FeedbackDto;
import com.example.mca.labourPlatform.exception.LabourHubException;
import com.example.mca.labourPlatform.model.Feedback;
import com.example.mca.labourPlatform.model.Labour;
import com.example.mca.labourPlatform.model.User;
import com.example.mca.labourPlatform.service.FeedbackService;
import com.example.mca.labourPlatform.util.FeedbackUtil;

@Service
public class FeedbackServiceImplementation implements FeedbackService {

	@Autowired
	private FeedbackRepository feedbackRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	LabourRepository labourRepository;

	public List<FeedbackDto> getFeedbacks() throws LabourHubException {
		try {
			List<FeedbackDto> listOfDtos = feedbackRepository.findAll().stream()
					.map(FeedbackUtil::convertFeedbackEntityToDto).collect(Collectors.toList());
			return listOfDtos;
		} catch (Exception e) {
			throw new LabourHubException(e.getMessage());
		}
	}

	public String createFeedback(Feedback feedback, Integer userId, Integer labourId) throws LabourHubException {
		try {
		User user = userRepository.findById(userId).orElse(null);
		Labour labour = labourRepository.findById(labourId).orElse(null);
		if (user == null || labour == null) {
			throw new LabourHubException("User Does't exist");
		}
		feedback.setUser(user);
		feedback.setLabour(labour);
		feedbackRepository.save(feedback);
		return "Feedback created successfully";
		}
		catch(Exception e)
		{
			throw new LabourHubException(e.getMessage());	
		}
	}
	
	public String deleteFeedback(Integer id) throws LabourHubException {
		Optional<Feedback> feedback = feedbackRepository.findById(id);
		try {
			if (feedback.isPresent()) {
				feedbackRepository.deleteById(id);
				Integer feedbackId = feedback.get().getId();
				return "labour " + feedbackId + " is deleted successfully.";
			} else {
				throw new LabourHubException("labour doesn't exist.");
			}
		} catch (Exception e) {
			throw new LabourHubException(e.getMessage());
		}
	}

}
