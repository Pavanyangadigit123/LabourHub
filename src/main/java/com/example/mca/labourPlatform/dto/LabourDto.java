package com.example.mca.labourPlatform.dto;

public class LabourDto {
	
	private Integer id;
	private Double dailyWages;
	private Boolean availability;
	
	//@JsonInclude(Include.NON_EMPTY)
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
