package com.example.mca.labourPlatform.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mca.labourPlatform.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

	Optional<User> findByEmail(String value);

//	Optional<Users> findUsersByEmail(String email);
	
//	 @Query("SELECT u FROM User u " +
//	           "LEFT JOIN Labour l ON l.user.id = u.id " +
//	           "LEFT JOIN LabourSkill ls ON ls.labour.id = l.id " +
//	           "LEFT JOIN Skill s ON s.id = ls.skill.id " +
//	           "WHERE LOWER(s.skillName) LIKE LOWER(CONCAT('%', :skill, '%')) " +
//	           "AND LOWER(u.city) LIKE LOWER(CONCAT('%', :city, '%')) " +
//	           "AND LOWER(u.area) LIKE LOWER(CONCAT('%', :area, '%')) " +
//	           "AND LOWER(u.state) LIKE LOWER(CONCAT('%', :state, '%'))")
//	    List<User> findLabourBySkillAndLocationIgnoreCase(String skill, String city, String area, String state);
//	

	 @Query("SELECT u FROM User u " +
	           "LEFT JOIN Labour l ON u = l.user " +
	           "LEFT JOIN LabourSkill ls ON ls.pk.labour = l " +
	           "LEFT JOIN Skill s ON s = ls.pk.skill " +
	           "WHERE LOWER(s.skillName) LIKE LOWER(CONCAT('%', :skill, '%')) " +
	           "AND LOWER(u.city) LIKE LOWER(CONCAT('%', :city, '%')) " +
	           "AND LOWER(u.area) LIKE LOWER(CONCAT('%', :area, '%')) " +
	           "AND LOWER(u.state) LIKE LOWER(CONCAT('%', :state, '%'))")
	    List<User> findLabourBySkillAndLocationIgnoreCase(String skill, String city, String area, String state);

}
