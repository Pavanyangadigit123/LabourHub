package com.example.mca.labourPlatform.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mca.labourPlatform.model.LabourSkill;
import com.example.mca.labourPlatform.model.LabourSkillId;

@Repository
public interface LabourSkillRepository extends JpaRepository<LabourSkill, LabourSkillId> {

}
