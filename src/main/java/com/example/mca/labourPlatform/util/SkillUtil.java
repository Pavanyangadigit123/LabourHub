package com.example.mca.labourPlatform.util;

import org.springframework.beans.BeanUtils;

import com.example.mca.labourPlatform.dto.SkillDto;
import com.example.mca.labourPlatform.model.Skill;

public class SkillUtil {
	public static SkillDto convertSkillEntityToDto(Skill skill) {
		SkillDto dto = new SkillDto();
		BeanUtils.copyProperties(skill, dto);
		// dto.setUserId(booking.getUser().getId());
		// dto.setLaborerId(booking.getLabour().getId());
		return dto;
	}

	public static Skill convertSkillDtoToEntity(SkillDto dto) {
		Skill skill = new Skill();
		BeanUtils.copyProperties(dto, skill);
		return skill;
	}

}
