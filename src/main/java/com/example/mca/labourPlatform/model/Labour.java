package com.example.mca.labourPlatform.model;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="labour")
public class Labour {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	
	@Column(name="daily_wages",nullable=false)
	private Double dailyWages;
	
	@Column(name="availability",nullable=false)
	private Boolean availability;
	
	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id",nullable=false)
	private User user;
	
	@OneToMany(mappedBy = "labour",cascade = CascadeType.PERSIST)
    private List<Booking> booking;
	
	@OneToMany(mappedBy="labour")
	private List<Feedback> feedback;
	
	@OneToMany(mappedBy = "pk.labour")
	private Set<LabourSkill> labourSkills = new HashSet<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer labourerId) {
		this.id = labourerId;
	}

	public double getDailyWages() {
		return dailyWages;
	}

	public void setDailyWages(double dailyWages) {
		this.dailyWages = dailyWages;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(availability, dailyWages, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Labour other = (Labour) obj;
		return Objects.equals(availability, other.availability) && Objects.equals(dailyWages, other.dailyWages)
				&& Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "LabourerProfile [Id=" + id + ", dailyWages=" + dailyWages + ", availability="
				+ availability + "]";
	}
	
	
	
	
	
	
	
	
	
	

}
