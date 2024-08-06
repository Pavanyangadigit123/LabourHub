package com.example.mca.labourPlatform.util;

import org.springframework.beans.BeanUtils;

import com.example.mca.labourPlatform.dto.LabourDto;
import com.example.mca.labourPlatform.dto.UserDto;
import com.example.mca.labourPlatform.model.Labour;

public class LabourUtil {
	public static LabourDto convertLabourEntityToDto(Labour labour)
	{
		LabourDto dto=new LabourDto();
		BeanUtils.copyProperties(labour, dto);
		UserDto userDto = UserUtil.convertUserEntityToDto(labour.getUser());
		dto.setUserDto(userDto);
		return dto;
	}
	
	public static Labour convertLabourDtoToEntity(LabourDto dto)
	{
		Labour labour=new Labour();
		BeanUtils.copyProperties(dto,labour);
		return labour;
	}

}
