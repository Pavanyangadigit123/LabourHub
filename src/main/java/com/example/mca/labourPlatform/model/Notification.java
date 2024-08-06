package com.example.mca.labourPlatform.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="notification")
public class Notification {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;

	@Column(name = "description")
	private String description;

	@Column(name = "is_read")
	private boolean isRead;
	
	@ManyToOne
	@JoinColumn(name="user_id",referencedColumnName = "id",nullable=false)
	private User user;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public void setIsRead(boolean isRead) {
		this.isRead = isRead;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public String toString() {
		return "Notifications [Id=" + id + ", description=" + description + ", isRead=" + isRead + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, id, isRead);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Notification other = (Notification) obj;
		return Objects.equals(description, other.description) && Objects.equals(id, other.id) && isRead == other.isRead;
	}

}
