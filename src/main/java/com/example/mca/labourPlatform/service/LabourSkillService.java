package com.example.mca.labourPlatform.service;

import java.util.List;

import com.example.mca.labourPlatform.dto.LabourSkillDto;
import com.example.mca.labourPlatform.exception.LabourHubException;
import com.example.mca.labourPlatform.model.LabourSkill;

public interface LabourSkillService {

	public abstract void assignSkillToLabour(LabourSkill labourskill, Integer labourId, Integer skillId) throws LabourHubException;

	public abstract List<LabourSkillDto> getLabourSkills() throws LabourHubException;

}
