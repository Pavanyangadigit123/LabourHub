package com.example.mca.labourPlatform.ServiceImplementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mca.labourPlatform.dao.LabourRepository;
import com.example.mca.labourPlatform.dao.LabourSkillRepository;
import com.example.mca.labourPlatform.dao.SkillRepository;
import com.example.mca.labourPlatform.dto.LabourSkillDto;
import com.example.mca.labourPlatform.exception.LabourHubException;
import com.example.mca.labourPlatform.model.Labour;
import com.example.mca.labourPlatform.model.LabourSkill;
import com.example.mca.labourPlatform.model.Skill;
import com.example.mca.labourPlatform.service.LabourSkillService;
import com.example.mca.labourPlatform.util.LabourSkillUtil;

@Service
public class LabourSkillServiceImplementation implements LabourSkillService {

	@Autowired
	private LabourSkillRepository labourSkillRepository;

	@Autowired
	private LabourRepository labourRepository;

	@Autowired
	private SkillRepository skillRepository;

	private Logger logger = LoggerFactory.getLogger(LabourSkillServiceImplementation.class);

	// retrieving labour skill data
	public List<LabourSkillDto> getLabourSkills() throws LabourHubException {
		try {
			List<LabourSkillDto> listOfDtos = labourSkillRepository.findAll().stream()
					.map(LabourSkillUtil::convertLabourSkillEntityToDto).collect(Collectors.toList());
			return listOfDtos;
		} catch (Exception e) {
			throw new LabourHubException(e.getMessage());
		}

	}

	// Assigning skills to labour
	public void assignSkillToLabour(LabourSkill labourskill, Integer labourId, Integer skillId)
			throws LabourHubException {
		Optional<Labour> labour = labourRepository.findById(labourId);
		if (labour.isPresent()) {
			Optional<Skill> skill = skillRepository.findById(skillId);
			if (skill.isPresent()) {
				logger.info("Labour with id: " + labourId + " and Skill with id: " + skillId + " found.");
				// LabourSkill labourSkill = new LabourSkill();
				labourskill.setLabour(labour.get());
				labourskill.setSkill(skill.get());
				// labourskill.setEnrollmentDate(OffsetDateTime.now());
				try {
					labourSkillRepository.save(labourskill);
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				throw new LabourHubException("Course not found");
			}
		} else {
			throw new LabourHubException("Student not found");
		}

	}

}
