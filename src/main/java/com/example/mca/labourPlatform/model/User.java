package com.example.mca.labourPlatform.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	
	@Column(name="email",nullable=false,unique=true)
	private String email;
	
	@Column(name="phone_number",nullable=false)
	private String phoneNumber;
	
	@Column(name="country",nullable=false)
	private String country;
	
	@Column(name="state",nullable=false)
	private String state;
	
	@Column(name="area",nullable=false)
	private String area;
	
	@Column(name="city",nullable=false)
	private String city;
	
	@Column(name="zip_code",nullable=false)
	private String zipCode;
	
	@Column(name="profile_pic")
	private String profilePic;
	
	@Column(name="first_name",nullable=false)
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@OneToOne(mappedBy = "user")
	private Labour labour;
	
	@OneToMany(mappedBy = "user")
    private List<Booking> booking;
	
	@OneToMany(mappedBy="user")
	private List<Notification> notifications;
	
	@OneToMany(mappedBy="user")
	private List<Feedback> feedback;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(area, city, country, email, firstName, id, lastName, phoneNumber, profilePic, state,
				zipCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(area, other.area) && Objects.equals(city, other.city)
				&& Objects.equals(country, other.country) && Objects.equals(email, other.email)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(id, other.id)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(phoneNumber, other.phoneNumber)
				&& Objects.equals(profilePic, other.profilePic) && Objects.equals(state, other.state)
				&& Objects.equals(zipCode, other.zipCode);
	}

	@Override
	public String toString() {
		return "Users [Id=" + id + ", email=" + email + ", phoneNumber=" + phoneNumber + ", country=" + country
				+ ", state=" + state + ", area=" + area + ", city=" + city + ", zipcode=" + zipCode + ", profilePic="
				+ profilePic + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	
	
	
	 
	 
	
}
