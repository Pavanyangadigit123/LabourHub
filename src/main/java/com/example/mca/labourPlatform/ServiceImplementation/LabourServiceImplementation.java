package com.example.mca.labourPlatform.ServiceImplementation;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mca.labourPlatform.dao.LabourRepository;
import com.example.mca.labourPlatform.dto.LabourDto;
import com.example.mca.labourPlatform.dto.UserDto;
import com.example.mca.labourPlatform.exception.LabourHubException;
import com.example.mca.labourPlatform.model.Labour;
import com.example.mca.labourPlatform.model.User;
import com.example.mca.labourPlatform.service.LabourService;
import com.example.mca.labourPlatform.service.UserService;
import com.example.mca.labourPlatform.util.LabourUtil;
import com.example.mca.labourPlatform.util.UserUtil;

import jakarta.transaction.Transactional;

@Service
public class LabourServiceImplementation implements LabourService {

	@Autowired
	private LabourRepository labourRepository;

	@Autowired
	private UserService userService;

	public List<LabourDto> getLabours() throws LabourHubException {
		try {
			List<LabourDto> listOfDtos = labourRepository.findAll().stream().map(LabourUtil::convertLabourEntityToDto)
					.collect(Collectors.toList());
			return listOfDtos;
		} catch (Exception e) {
			throw new LabourHubException(e.getMessage());
		}
	}

	public String createLabour(UserDto userDto) throws LabourHubException {
		try {
			UserDto newUserDto = userService.createUser(userDto);
			User user = UserUtil.convertUserDtoToEntity(newUserDto);
			Labour labourerProfile = LabourUtil.convertLabourDtoToEntity(userDto.getLabourDto());
			labourerProfile.setUser(user);
			labourRepository.save(labourerProfile);
			return "labour data created successfully";
		} catch (Exception exception) {
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
			throw new LabourHubException(e.getMessage() );
		}
	}

}
