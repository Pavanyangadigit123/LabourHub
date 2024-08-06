package com.example.mca.labourPlatform.ServiceImplementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mca.labourPlatform.dao.SkillRepository;
import com.example.mca.labourPlatform.dto.SkillDto;
import com.example.mca.labourPlatform.exception.LabourHubException;
import com.example.mca.labourPlatform.model.Skill;
import com.example.mca.labourPlatform.service.SkillService;
import com.example.mca.labourPlatform.util.SkillUtil;

@Service
public class SkillServiceImplementation implements SkillService {

	@Autowired
	private SkillRepository skillRepository;

	// getting list of skills
	public List<SkillDto> getSkills() throws LabourHubException {
		try {
			List<SkillDto> listOfDtos = skillRepository.findAll().stream().map(SkillUtil::convertSkillEntityToDto)
					.collect(Collectors.toList());
			return listOfDtos;
		} catch (Exception e) {
			throw new LabourHubException(e.getMessage());
		}
	}

	// adding new skills
	public SkillDto addSkills(SkillDto skillDto) throws LabourHubException {
		Skill skill = SkillUtil.convertSkillDtoToEntity(skillDto);
		try {
			skillRepository.save(skill);
			SkillDto dto = SkillUtil.convertSkillEntityToDto(skill);
			return dto;
		} catch (Exception exception) {
			throw new LabourHubException("Skill is already registered");
		}
	}

	// deleting users
	public String deleteUser(Integer id) throws LabourHubException {
		Optional<Skill> skill = skillRepository.findById(id);
		try {
			if (skill.isPresent()) {
				skillRepository.deleteById(id);
				String name = skill.get().getSkillName();
				return "Skill " + name + " is deleted successfully.";
			} else {
				throw new LabourHubException("Skill doesn't exist!!.");
			}
		} catch (Exception e) {
			throw new LabourHubException(e.getMessage());
		}
	}
}
