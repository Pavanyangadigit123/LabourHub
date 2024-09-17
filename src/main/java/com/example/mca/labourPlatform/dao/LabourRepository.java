package com.example.mca.labourPlatform.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mca.labourPlatform.dto.LabourDto;
import com.example.mca.labourPlatform.model.Labour;

public interface LabourRepository extends JpaRepository<Labour, Integer> {
	
	@Query(nativeQuery = true, value= "select * from labour lp where lp.id = ?1")
	Optional<Labour> findByUserId(Integer UserId);
	
	@Query(value = "SELECT l.id, l.availability, l.daily_wages, l.user_id " +
			"FROM labour l " +
            "LEFT JOIN users u ON l.user_id = u.id " +
            "LEFT JOIN labour_skill ls ON ls.labour_id = l.id " +
            "LEFT JOIN skill s ON s.id = ls.skill_id " +
            "WHERE LOWER(s.skill_name) LIKE LOWER(CONCAT('%', :skillName, '%')) " +
            "AND LOWER(u.city) LIKE LOWER(CONCAT('%', :city, '%')) " +
            "AND LOWER(u.area) LIKE LOWER(CONCAT('%', :area, '%')) " +
            "AND LOWER(u.state) LIKE LOWER(CONCAT('%', :state, '%'))", nativeQuery = true)
	List<Labour> findAllByFilters(@Param("skillName") String skillName,
            @Param("state") String state,
            @Param("city") String city,
            @Param("area") String area);
	
//	List<Labour> findByAreaContainingIgnoreCaseORCityContainingIgnoreCaseORStateContainingIgnoreCaseORCountryContainingIgnoreCaseORZipCodeContainingIgnoreCase(
//	        String area, String city, String state, String country, String zipCode);
	
	

}