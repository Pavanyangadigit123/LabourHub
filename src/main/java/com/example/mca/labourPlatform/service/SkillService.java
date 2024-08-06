package com.example.mca.labourPlatform.service;

import java.util.List;

import com.example.mca.labourPlatform.dto.SkillDto;
import com.example.mca.labourPlatform.exception.LabourHubException;


public interface SkillService {
	
public abstract List<SkillDto> getSkills() throws LabourHubException;
	
	public abstract SkillDto addSkills(SkillDto skillDto) throws LabourHubException;
	
	public abstract String deleteUser(Integer id) throws LabourHubException;
}
