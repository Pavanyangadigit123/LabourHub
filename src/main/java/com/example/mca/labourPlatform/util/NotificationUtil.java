package com.example.mca.labourPlatform.util;

import org.springframework.beans.BeanUtils;

import com.example.mca.labourPlatform.dto.NotificationDto;
import com.example.mca.labourPlatform.model.Notification;

public class NotificationUtil {
	public static NotificationDto convertNotificationEntityToDto(Notification notification) {
		NotificationDto dto = new NotificationDto();
		BeanUtils.copyProperties(notification, dto);
		return dto;
	}

	public static Notification convertNotificationDtoToEntity(NotificationDto dto) {
		Notification notification = new Notification();
		BeanUtils.copyProperties(dto, notification);
		return notification;
	}
}
