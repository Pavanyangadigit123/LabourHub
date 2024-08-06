package com.example.mca.labourPlatform.dto;

public class LabourSkillDto {
	
	private Integer yearsOfExperience;
	private String proficiencyLevel;
	
	private Integer labourId;
	private Integer skillId;
	
	public Integer getYearsOfExperience() {
		return yearsOfExperience;
	}
	public void setYearsOfExperience(Integer yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}
	public String getProficiencyLevel() {
		return proficiencyLevel;
	}
	public void setProficiencyLevel(String proficiencyLevel) {
		this.proficiencyLevel = proficiencyLevel;
	}
	public Integer getLabourId() {
		return labourId;
	}
	public void setLabourId(Integer labourId) {
		this.labourId = labourId;
	}
	public Integer getSkillId() {
		return skillId;
	}
	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}
	
	

}
