package com.example.mca.labourPlatform.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.mca.labourPlatform.dto.LabourDto;
import com.example.mca.labourPlatform.dto.UserDto;
import com.example.mca.labourPlatform.exception.LabourHubException;
import com.example.mca.labourPlatform.model.Labour;

public interface LabourService {

	public abstract List<LabourDto> getLabours() throws LabourHubException;

	public abstract String createLabour(LabourDto labourDto) throws LabourHubException;

	public abstract String updateLabour(Integer id, Labour labour) throws LabourHubException;

	public abstract String deleteLabour(Integer id) throws LabourHubException;

	public abstract List<LabourDto> getLaboursByFilters(String skillName, String state, String city, 
			String area) throws LabourHubException;

//	public abstract List<LabourDto> findLaboursByFilter(String area, String city, String state, String country,
//			String zipCode) throws LabourHubException;

}
