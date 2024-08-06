package com.example.mca.labourPlatform.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.mca.labourPlatform.model.Labour;

public interface LabourRepository extends JpaRepository<Labour, Integer> {
	
	@Query(nativeQuery = true, value= "select * from labour lp where lp.id = ?1")
	Optional<Labour> findByUserId(Integer UserId);

}