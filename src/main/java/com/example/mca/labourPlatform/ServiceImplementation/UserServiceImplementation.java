package com.example.mca.labourPlatform.ServiceImplementation;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mca.labourPlatform.dao.RoleRepository;
import com.example.mca.labourPlatform.dao.UserRepository;
import com.example.mca.labourPlatform.dto.UserDto;
import com.example.mca.labourPlatform.exception.LabourHubException;
import com.example.mca.labourPlatform.model.Role;
import com.example.mca.labourPlatform.model.User;
import com.example.mca.labourPlatform.security.SecurityConstants;
import com.example.mca.labourPlatform.service.UserService;
import com.example.mca.labourPlatform.util.GoogleOAuthUtil;
import com.example.mca.labourPlatform.util.UserUtil;
import com.nimbusds.jose.shaded.gson.JsonObject;
import com.nimbusds.jose.shaded.gson.JsonParser;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserServiceImplementation implements UserService {

	Logger logger = LoggerFactory.getLogger(UserServiceImplementation.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private RoleRepository roleRepository;

	public List<UserDto> getUsers() throws LabourHubException {
		try {
			List<UserDto> listOfDtos = userRepository.findAll().stream().map(UserUtil::convertUserEntityToDto)
					.collect(Collectors.toList());
			return listOfDtos;
		} catch (Exception e) {
			throw new LabourHubException(e.getMessage());
		}
	}
	
//	public List<User> getLabourByFilter(String skill, String city, String area, String state) throws LabourHubException {
//		try {
//		List<User> listOfUsers=  userRepository.findLabourBySkillAndLocationIgnoreCase(skill, city, area, state);
//		return listOfUsers;
//		} catch (Exception e) {
//			throw new LabourHubException(e.getMessage());
//		}
//	}
//	
	public UserDto getUsersByEmail(String email) throws LabourHubException {
		try {
			Optional<User> user = userRepository.findByEmail(email);

			if (user.isPresent()) {
				UserDto userDto = UserUtil.convertUserEntityToDto(user.get());
				return userDto;
			} else {
				throw new LabourHubException("User not found for the given email.");
			}

		} catch (Exception exception) {
			throw new LabourHubException("Error while retrieving user data: " + exception.getMessage());
		}
	}

	public UserDto createUser(UserDto userDto) throws LabourHubException {
		User user = UserUtil.convertUserDtoToEntity(userDto);
		try {
			user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
			user.setCreatedAt(LocalDateTime.now());
			user.setLastModifiedAt(LocalDateTime.now());
			user.setIsEmailVerified(Boolean.FALSE);
			user.setIsPhoneNumberVerified(Boolean.FALSE);
//			user.setIsGoogleUser(Boolean.FALSE);
			user.setEnabled(Boolean.TRUE);

			Role userRole = roleRepository.findByName("USER");
			user.getRoles().add(userRole);

			user = userRepository.save(user);
			userRepository.save(user);
			UserDto dto = UserUtil.convertUserEntityToDto(user);
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

		return "User" + existingUser.getFirstName() + " data updated successfully";

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

	@Override
	public String signIn(UserDto userDto) {
		String jwt = null;
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));
		if (null != authentication) {
			User user = userRepository.findByEmail(userDto.getEmail()).get();
			SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));
			jwt = Jwts.builder().setIssuer("LABOUR_HUB").setSubject("JWT Token")
					.claim("userId", user.getId())
					.claim("username", authentication.getName()).claim("email", userDto.getEmail())
					.claim("authorities", populateAuthorities(authentication.getAuthorities())).setIssuedAt(new Date())
					.setExpiration(new Date((new Date()).getTime() + 30000000)).signWith(key).compact();
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return jwt;
	}

	private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
		Set<String> authoritiesSet = new HashSet<>();
		for (GrantedAuthority authority : collection) {
			authoritiesSet.add(authority.getAuthority());
		}
		return String.join(",", authoritiesSet);
	}

	
	@Override
	public String signInWithGoogle(String accessToken) {
		
		UserDto userDto = getUserDtoFromGoogleAccessToken(accessToken);
		Optional<User> userFromDB =userRepository.findByEmail(userDto.getEmail());
		if(userFromDB.isEmpty()) {
			try {
				this.createUser(userDto);
			}catch (Exception  e) {
				e.printStackTrace();
			}
		}
		
		return signIn(userDto);
		
	}

	private UserDto getUserDtoFromGoogleAccessToken(String accessToken) {
		UserDto usertDto = new UserDto();
		JsonObject jsonObject = GoogleOAuthUtil.getProfileDetailsGoogle(accessToken);
		return populateUserDetailsFromGoogleResPonse(usertDto, jsonObject);

	}
	
	private UserDto populateUserDetailsFromGoogleResPonse(UserDto userDto, JsonObject jsonObject){
		userDto.setFirstName(jsonObject.get("given_name").toString().replaceAll("\"", ""));
		userDto.setLastName(jsonObject.get("family_name").toString().replaceAll("\"", ""));
		Double phone = Math.random();
		userDto.setPhoneNumber(phone.toString().substring(2,12) +"_RANDOM");
		userDto.setEmail(jsonObject.get("email").toString().replaceAll("\"", ""));
		userDto.setPassword(userDto.getEmail());
		userDto.setIsGoogleUser(Boolean.TRUE);
		//"picture" to get profile picture
		return userDto;
		
	}
}
