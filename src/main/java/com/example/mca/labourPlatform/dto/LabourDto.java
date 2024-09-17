package com.example.mca.labourPlatform.dto;

import java.util.List;
import java.util.Set;

public class LabourDto {

	private Integer id;
	private String email;
	private String area;
	private String phoneNumber;
	private String country;
	private String state;
	private String city;
	private String zipCode;
	private String profilePic;
	private String firstName;
	private String lastName;
	private String Password;



	private Double dailyWages;
	private Boolean availability;

	private Set<LabourSkillDto> labourSkillDtos;

	public Set<LabourSkillDto> getLabourSkillDtos() {
		return labourSkillDtos;
	}

	public void setLabourSkillDtos(Set<LabourSkillDto> labourSkillDtos) {
		this.labourSkillDtos = labourSkillDtos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
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
	
	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	// @JsonInclude(Include.NON_EMPTY)
	private UserDto userDto;

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}

	public Boolean getAvailability() {
		return availability;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getDailyWages() {
		return dailyWages;
	}

	public void setDailyWages(Double dailyWages) {
		this.dailyWages = dailyWages;
	}

	public Boolean isAvailability() {
		return availability;
	}

	public void setAvailability(Boolean availability) {
		this.availability = availability;
	}

}
