package com.example.mca.labourPlatform.util;

import org.springframework.beans.BeanUtils;

import com.example.mca.labourPlatform.dto.LabourSkillDto;
import com.example.mca.labourPlatform.model.LabourSkill;

public class LabourSkillUtil {

	public static LabourSkillDto convertLabourSkillEntityToDto(LabourSkill labourSkill) {
		LabourSkillDto dto = new LabourSkillDto();
		BeanUtils.copyProperties(labourSkill, dto);
		 dto.setLabourId(labourSkill.getLabour().getId());
		 dto.setSkillId(labourSkill.getSkill().getId());
		return dto;
	}

	public static LabourSkill convertLabourSkillDtoToEntity(LabourSkillDto dto) {
		LabourSkill labourSkill = new LabourSkill();
		BeanUtils.copyProperties(dto, labourSkill);
		return labourSkill;
	}
}
