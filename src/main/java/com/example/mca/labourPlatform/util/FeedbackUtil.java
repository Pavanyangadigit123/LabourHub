package com.example.mca.labourPlatform.util;

import org.springframework.beans.BeanUtils;

import com.example.mca.labourPlatform.dto.FeedbackDto;
import com.example.mca.labourPlatform.model.Feedback;



public class FeedbackUtil {
	public static FeedbackDto convertFeedbackEntityToDto(Feedback feedback)
	{
		FeedbackDto dto=new FeedbackDto();
		BeanUtils.copyProperties(feedback, dto);
		dto.setUserId(feedback.getUser().getId());
		dto.setLabourerId(feedback.getLabour().getId());
		return dto;
	}
	
	public static Feedback convertUsersDtoToEntity(FeedbackDto dto)
	{
		Feedback feedback=new Feedback();
		BeanUtils.copyProperties(dto,feedback);
		return feedback;
	}

}
