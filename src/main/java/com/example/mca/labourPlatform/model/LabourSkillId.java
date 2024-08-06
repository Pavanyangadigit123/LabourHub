package com.example.mca.labourPlatform.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

@Embeddable
public class LabourSkillId implements Serializable {
	
	private static final long serialVersionUID = 1164772701858212772L;

	@ManyToOne
	private Labour labour;
	
	@ManyToOne
	private Skill skill;

	public Labour getLabour() {
		return labour;
	}

	public void setLabour(Labour labour) {
		this.labour = labour;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	@Override
	public int hashCode() {
		return Objects.hash(labour, skill);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LabourSkillId other = (LabourSkillId) obj;
		return Objects.equals(labour, other.labour) && Objects.equals(skill, other.skill);
	}
	

}
