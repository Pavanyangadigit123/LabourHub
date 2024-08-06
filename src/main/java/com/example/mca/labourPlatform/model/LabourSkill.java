package com.example.mca.labourPlatform.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.AssociationOverride;
import jakarta.persistence.AssociationOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "labour_skill")
@AssociationOverrides({ @AssociationOverride(name = "pk.labour", joinColumns = @JoinColumn(name = "labour_id")),
		@AssociationOverride(name = "pk.skill", joinColumns = @JoinColumn(name = "skill_id")) })
public class LabourSkill implements Serializable {

	private static final long serialVersionUID = -5342762828776496118L;

	@EmbeddedId
	private LabourSkillId pk = new LabourSkillId();

	@Column(name = "years_of_experience",nullable=false)
	private Integer yearsOfExperience;

	@Column(name = "proficiency_level",nullable=false)
	private String proficiencyLevel;

	public LabourSkillId getPk() {
		return pk;
	}

	public void setPk(LabourSkillId pk) {
		this.pk = pk;
	}

	public Integer getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(Integer yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	public String getProficiencyLevel() {
		return proficiencyLevel;
	}

	public void setProficiencyLevel(String proficiencyLevel) {
		this.proficiencyLevel = proficiencyLevel;
	}

	@Transient
	public Labour getLabour() {
		return getPk().getLabour();
	}

	public void setLabour(Labour labour) {
		getPk().setLabour(labour);
	}

	@Transient
	public Skill getSkill() {
		return getPk().getSkill();
	}

	public void setSkill(Skill skill) {
		getPk().setSkill(skill);
	}

	@Override
	public int hashCode() {
		return Objects.hash(pk, proficiencyLevel, yearsOfExperience);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LabourSkill other = (LabourSkill) obj;
		return Objects.equals(pk, other.pk) && Objects.equals(proficiencyLevel, other.proficiencyLevel)
				&& Objects.equals(yearsOfExperience, other.yearsOfExperience);
	}

}
