package com.example.demo.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "proposal_table")
public class Proposal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer proposalId;
	private String proposalName;
	private Integer proposalAge;
	private Long proposalMobileNumber;
	private Date proposalDateOfBirth;
	private Character status;

	@OneToMany(mappedBy = "proposal", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Nominee> nominees;

	public List<Nominee> getNominees() {
		return nominees;
	}

	public void setNominees(List<Nominee> nominees) {
		this.nominees = nominees;
	}

	public Integer getProposalId() {
		return proposalId;
	}

	public void setProposalId(Integer proposalId) {
		this.proposalId = proposalId;
	}

	public String getProposalName() {
		return proposalName;
	}

	public void setProposalName(String proposalName) {
		this.proposalName = proposalName;
	}

	public Integer getProposalAge() {
		return proposalAge;
	}

	public void setProposalAge(Integer proposalAge) {
		this.proposalAge = proposalAge;
	}

	public Long getProposalMobileNumber() {
		return proposalMobileNumber;
	}

	public void setProposalMobileNumber(Long proposalMobileNumber) {
		this.proposalMobileNumber = proposalMobileNumber;
	}

	public Date getProposalDateOfBirth() {
		return proposalDateOfBirth;
	}

	public void setProposalDateOfBirth(Date proposalDateOfBirth) {
		this.proposalDateOfBirth = proposalDateOfBirth;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

}
