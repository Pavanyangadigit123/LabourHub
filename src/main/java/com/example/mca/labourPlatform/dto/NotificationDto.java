package com.example.mca.labourPlatform.dto;

public class NotificationDto {
	private Integer notificationId;
	private String description;
	private boolean isRead;
	
	public Integer getNotificationId() {
		return notificationId;
	}
	public void setNotificationId(Integer notificationId) {
		this.notificationId = notificationId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isIsRead() {
		return isRead;
	}
	public void setIs_read(boolean isRead) {
		this.isRead = isRead;
	}

}



