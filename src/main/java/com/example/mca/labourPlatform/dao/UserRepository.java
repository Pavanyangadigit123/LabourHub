package com.example.mca.labourPlatform.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mca.labourPlatform.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

//	Optional<Users> findUsersByEmail(String email);
	


}
