package com.example.mca.labourPlatform.ServiceImplementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mca.labourPlatform.dao.NotificationRepository;
import com.example.mca.labourPlatform.dao.UserRepository;
import com.example.mca.labourPlatform.dto.NotificationDto;
import com.example.mca.labourPlatform.exception.LabourHubException;
import com.example.mca.labourPlatform.model.Notification;
import com.example.mca.labourPlatform.model.User;
import com.example.mca.labourPlatform.service.NotificationService;
import com.example.mca.labourPlatform.util.NotificationUtil;


@Service
public class NotificationServiceImplementation implements NotificationService  {
	
	@Autowired
	private NotificationRepository notificationRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<NotificationDto> getNotifications() throws LabourHubException {
		try {
			List<NotificationDto> listOfDtos = notificationRepository.findAll().stream()
					.map(NotificationUtil::convertNotificationEntityToDto).collect(Collectors.toList());
			return listOfDtos;
		} catch (Exception e) {
			throw new LabourHubException(e.getMessage());
		}
	}

	public String addNotification(Notification notification, Integer userId) throws LabourHubException {
		try {
		User user = userRepository.findById(userId).orElse(null);
		if (user == null ) {
			throw new LabourHubException("User Does't exist");
		}
		notification.setUser(user);
		notificationRepository.save(notification);
		return "Feedback created successfully";
		}
		catch(Exception e)
		{
			throw new LabourHubException(e.getMessage());	
		}
	}
	
	public String deleteNotification(Integer id) throws LabourHubException {
		Optional<Notification> notification = notificationRepository.findById(id);
		try {
			if (notification.isPresent()) {
				notificationRepository.deleteById(id);
				Integer feedbackId = notification.get().getId();
				return "labour " + feedbackId + " is deleted successfully.";
			} else {
				throw new LabourHubException("labour doesn't exist.");
			}
		} catch (Exception e) {
			throw new LabourHubException(e.getMessage());
		}
	}


}
