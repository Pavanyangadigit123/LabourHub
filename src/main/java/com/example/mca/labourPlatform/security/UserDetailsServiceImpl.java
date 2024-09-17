package com.example.mca.labourPlatform.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.mca.labourPlatform.dao.UserRepository;
import com.example.mca.labourPlatform.model.User;

public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String value) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByEmail(value);
		
		if(user.isEmpty()) {
			throw new UsernameNotFoundException("Could not find user");
		}
		return new LabourHubUserDetails(user.get());
	}

}

