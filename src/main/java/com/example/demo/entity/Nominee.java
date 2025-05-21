package com.example.demo.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "nominee_table")
public class Nominee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer nomineeId;
	private String nomineeName;
	private Long nomineeMobileNumber;
	private Integer nomineeAge;
	private Character status;
	private Date nomineeDateOfBirth;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "proposal_id")
	@JsonBackReference
	private Proposal proposal;

	public Proposal getProposal() {
		return proposal;
	}

	public void setProposal(Proposal proposal) {
		this.proposal = proposal;
	}

	public Integer getNomineeId() {
		return nomineeId;
	}

	public void setNomineeId(Integer nomineeId) {
		this.nomineeId = nomineeId;
	}

	public String getNomineeName() {
		return nomineeName;
	}

	public void setNomineeName(String nomineeName) {
		this.nomineeName = nomineeName;
	}

	public Long getNomineeMobileNumber() {
		return nomineeMobileNumber;
	}

	public void setNomineeMobileNumber(Long nomineeMobileNumber) {
		this.nomineeMobileNumber = nomineeMobileNumber;
	}

	public Integer getNomineeAge() {
		return nomineeAge;
	}

	public void setNomineeAge(Integer nomineeAge) {
		this.nomineeAge = nomineeAge;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		status = status;
	}

	public Date getNomineeDateOfBirth() {
		return nomineeDateOfBirth;
	}

	public void setNomineeDateOfBirth(Date nomineeDateOfBirth) {
		this.nomineeDateOfBirth = nomineeDateOfBirth;
	}

}