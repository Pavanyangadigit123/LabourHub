package com.example.mca.labourPlatform.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mca.labourPlatform.model.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer>{

}
