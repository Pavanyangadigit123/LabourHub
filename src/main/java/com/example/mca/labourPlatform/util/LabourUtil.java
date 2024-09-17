package com.example.mca.labourPlatform.util;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.example.mca.labourPlatform.dto.LabourDto;
import com.example.mca.labourPlatform.dto.LabourSkillDto;
import com.example.mca.labourPlatform.model.Labour;
import com.example.mca.labourPlatform.model.LabourSkill;

public class LabourUtil {
	public static LabourDto convertLabourEntityToDto(Labour labour)
	{
		LabourDto dto=new LabourDto();
		BeanUtils.copyProperties(labour.getUser(), dto);
		BeanUtils.copyProperties(labour,dto);
		Set<LabourSkillDto> labourSkillDtos = new HashSet();
		for (LabourSkill labourSkill : labour.getLabourSkills()) {
			LabourSkillDto labourSkillDto = LabourSkillUtil.convertLabourSkillEntityToDto(labourSkill);
			labourSkillDtos.add(labourSkillDto);
		}
		dto.setLabourSkillDtos(labourSkillDtos);
//		UserDto userDto = UserUtil.convertUserEntityToDto(labour.getUser());
//		dto.setUserDto(userDto);
		return dto;
	}
	
	public static Labour convertLabourDtoToEntity(LabourDto dto)
	{
		Labour labour=new Labour();
		BeanUtils.copyProperties(dto,labour);
		return labour;
	}
	
	

}
