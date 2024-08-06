package com.example.mca.labourPlatform.service;

import java.util.List;

import com.example.mca.labourPlatform.dto.NotificationDto;
import com.example.mca.labourPlatform.exception.LabourHubException;
import com.example.mca.labourPlatform.model.Notification;

public interface NotificationService {

	public abstract String addNotification(Notification notification, Integer userId) throws LabourHubException;

	public abstract String deleteNotification(Integer id) throws LabourHubException;

	public abstract List<NotificationDto> getNotifications() throws LabourHubException;

}



