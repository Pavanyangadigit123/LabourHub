package com.example.mca.labourPlatform.ServiceImplementation;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mca.labourPlatform.dao.UserRepository;
import com.example.mca.labourPlatform.dto.UserDto;
import com.example.mca.labourPlatform.exception.LabourHubException;
import com.example.mca.labourPlatform.model.User;
import com.example.mca.labourPlatform.service.UserService;
import com.example.mca.labourPlatform.util.UserUtil;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImplementation implements UserService {

	Logger logger = LoggerFactory.getLogger(UserServiceImplementation.class);
	@Autowired
	private UserRepository userRepository;

	public List<UserDto> getUsers() throws LabourHubException {
		try {
			List<UserDto> listOfDtos = userRepository.findAll().stream().map(UserUtil::convertUserEntityToDto)
					.collect(Collectors.toList());
			return listOfDtos;
		} catch (Exception e) {
			throw new LabourHubException(e.getMessage());
		}
}

	public UserDto createUser(UserDto userDto) throws LabourHubException {
		User user = UserUtil.convertUserDtoToEntity(userDto);
		try {
			userRepository.save(user);
			UserDto dto=UserUtil.convertUserEntityToDto(user);
			return dto;
		} catch (Exception exception) {
			logger.error(exception.getMessage());
			throw new LabourHubException("User data is already registered");
		}
	}

	@Transactional
	public String updateUser(Integer userId, UserDto dto) throws LabourHubException {
		User user = UserUtil.convertUserDtoToEntity(dto);
		User existingUser = userRepository.findById(userId).get();

		if (existingUser == null) {
			throw new LabourHubException("User not Found");
		}

		// Update Email
		if (user.getEmail() != null && user.getEmail().length() > 0
				&& !Objects.equals(user.getEmail(), existingUser.getEmail())) {
			existingUser.setEmail(user.getEmail());
		}

		// Update PhoneNumber
		if (user.getPhoneNumber() != null && user.getPhoneNumber().length() > 0
				&& !Objects.equals(user.getPhoneNumber(), existingUser.getPhoneNumber())) {
			existingUser.setPhoneNumber(user.getPhoneNumber());
		}

		// Update Profile pic
		if (user.getProfilePic() != null && user.getProfilePic().length() > 0
				&& !Objects.equals(user.getProfilePic(), existingUser.getProfilePic())) {
			existingUser.setProfilePic(user.getProfilePic());
		}
		
		return "User"+existingUser.getFirstName()+" data updated successfully";

	}

	public String deleteUser(Integer id) throws LabourHubException {
		Optional<User> user = userRepository.findById(id);
		try {
			if (user.isPresent()) {
				userRepository.deleteById(id);
				String name = user.get().getFirstName();
				return "User " + name + " Has deleted successfully.";
			} else {
				throw new LabourHubException("User doesn't exist!!.");
			}
		} catch (Exception e) {
			throw new LabourHubException(e.getMessage());
		}
	}

}
