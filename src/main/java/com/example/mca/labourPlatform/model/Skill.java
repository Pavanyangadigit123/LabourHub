package com.example.mca.labourPlatform.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="skill")
public class Skill {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	
	@Column(name="skill_name",nullable=false,unique=true)
	private String skillName;
	
	@Column(name="skill_description")
	private String skillDescription;
	
	@OneToMany(mappedBy = "pk.skill")
	private Set<LabourSkill> labourSkills = new HashSet<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public String getSkillDescription() {
		return skillDescription;
	}

	public void setSkillDescription(String skillDescription) {
		this.skillDescription = skillDescription;
	}

	@Override
	public String toString() {
		return "Skill [id=" + id + ", skillName=" + skillName + ", skillDescription=" + skillDescription + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, skillDescription, skillName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Skill other = (Skill) obj;
		return Objects.equals(id, other.id) && Objects.equals(skillDescription, other.skillDescription)
				&& Objects.equals(skillName, other.skillName);
	}
	
	
}
