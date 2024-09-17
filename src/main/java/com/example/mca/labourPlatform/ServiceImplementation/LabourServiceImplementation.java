package com.example.mca.labourPlatform.ServiceImplementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mca.labourPlatform.dao.LabourRepository;
import com.example.mca.labourPlatform.dao.LabourSkillRepository;
import com.example.mca.labourPlatform.dao.SkillRepository;
import com.example.mca.labourPlatform.dto.LabourDto;
import com.example.mca.labourPlatform.dto.LabourSkillDto;
import com.example.mca.labourPlatform.dto.UserDto;
import com.example.mca.labourPlatform.exception.LabourHubException;
import com.example.mca.labourPlatform.model.Labour;
import com.example.mca.labourPlatform.model.LabourSkill;
import com.example.mca.labourPlatform.model.Skill;
import com.example.mca.labourPlatform.model.User;
import com.example.mca.labourPlatform.service.LabourService;
import com.example.mca.labourPlatform.service.UserService;
import com.example.mca.labourPlatform.util.LabourSkillUtil;
import com.example.mca.labourPlatform.util.LabourUtil;
import com.example.mca.labourPlatform.util.UserUtil;

import jakarta.transaction.Transactional;

@Service
public class LabourServiceImplementation implements LabourService {

	Logger logger = LoggerFactory.getLogger(LabourServiceImplementation.class);

	@Autowired
	private LabourRepository labourRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private LabourSkillRepository labourSkillRepository;

	@Autowired
	private SkillRepository skillRepository;

	public List<LabourDto> getLabours() throws LabourHubException {
		try {
			List<LabourDto> listOfDtos = labourRepository.findAll().stream().map(LabourUtil::convertLabourEntityToDto)
					.collect(Collectors.toList());
			return listOfDtos;
		} catch (Exception e) {
			throw new LabourHubException(e.getMessage());
		}
	}

	// Fetching Labours By filters
	public List<LabourDto> getLaboursByFilters(String skillName, String state, String city,  String area)
			throws LabourHubException {
		try {
			List<LabourDto> listOfDtos = labourRepository.findAllByFilters(skillName, state, city,  area)
                    .stream()
                    .map(LabourUtil::convertLabourEntityToDto)
                    .collect(Collectors.toList());
//            List<LabourDto> listOfDto=LabourUtil.convertLabourEntityToDto(labours);
			return listOfDtos;
		} catch (Exception e) {
			throw new LabourHubException(e.getMessage());
		}
	}

//	public List<LabourDto> findLaboursByFilter(String area, String city, String state, String country, String zipCode) throws LabourHubException {
//		try {
//        List<Labour> labours = labourRepository.findByAreaContainingIgnoreCaseORCityContainingIgnoreCaseORStateContainingIgnoreCaseORCountryContainingIgnoreCaseORZipCodeContainingIgnoreCase(
//            area, city, state, country, zipCode);
//        return labours.stream()
//                      .map(LabourUtil::convertLabourEntityToDto)
//                      .collect(Collectors.toList());
//		}catch (Exception e) {
//			throw new LabourHubException(e.getMessage());
//		}
//    }

	public String createLabour(LabourDto labourDto) throws LabourHubException {
		try {
			UserDto userDto = UserUtil.convertLabourDtoToUserDto(labourDto);

			UserDto newUserDto = userService.createUser(userDto);
			User user = UserUtil.convertUserDtoToEntity(newUserDto);

			Labour labourerProfile = LabourUtil.convertLabourDtoToEntity(labourDto);
			labourerProfile.setUser(user);

			Labour newLabour = labourRepository.save(labourerProfile);

			List<LabourSkill> listOfLabourSkills = new ArrayList<>();

			for (LabourSkillDto labourSkillDto : labourDto.getLabourSkillDtos()) {
				LabourSkill labourSkill = LabourSkillUtil.convertLabourSkillDtoToEntity(labourSkillDto);
				Optional<Skill> skill = skillRepository.findById(labourSkillDto.getSkillId());
				if (skill.isEmpty()) {
					throw new LabourHubException("Skill doesn't exist");
				}

				labourSkill.setSkill(skill.get());
				labourSkill.setLabour(newLabour);

				listOfLabourSkills.add(labourSkill);
			}

			labourSkillRepository.saveAll(listOfLabourSkills);
//			List<LabourSkillDto> savedLabourSkillDtos = listOfLabourSkills.stream()
//		            .map(labourSkill -> {
//		                LabourSkillDto dto = new LabourSkillDto();
//		                dto.setSkillId(labourSkill.getSkill().getId());
//		                 //dto.setSkillName(labourSkill.getSkill().getName());
//		                return dto;
//		            })
//		            .collect(Collectors.toList());
//
//		        labourDto.setLabourSkillDtos(savedLabourSkillDtos);

			return "labour data created successfully";
		} catch (Exception exception) {

			logger.error(exception.getLocalizedMessage());
			throw new LabourHubException("Labour data is already registered" + exception.getMessage());
		}

	}

	@Transactional
	public String updateLabour(Integer id, Labour labour) throws LabourHubException {
		// Labour labour = LabourUtil.convertLabourDtoToEntity(labourDto);
		Labour existingLabour = labourRepository.findById(id).get();

		if (existingLabour == null) {
			throw new LabourHubException("Labour not Found");
		}

		// Update Daily Wages
		if (labour.getDailyWages() > 0 && !Objects.equals(labour.getDailyWages(), existingLabour.getDailyWages())) {
			existingLabour.setDailyWages(labour.getDailyWages());
		}

		// Update Availability
		if ((labour.isAvailability() == true || labour.isAvailability() == false)
				&& !Objects.equals(labour.isAvailability(), existingLabour.isAvailability())) {
			existingLabour.setAvailability(labour.isAvailability());
		}
		return "labour data updated successfully";
	}

	public String deleteLabour(Integer id) throws LabourHubException {
		Optional<Labour> labour = labourRepository.findById(id);
		try {
			if (labour.isPresent()) {
				labourRepository.deleteById(id);
				Integer labourId = labour.get().getId();
				return "labour " + labourId + " is deleted successfully.";
			} else {
				throw new LabourHubException("labour doesn't exist.");
			}
		} catch (Exception e) {
			throw new LabourHubException(e.getMessage());
		}
	}

}
