package com.example.demo.dto;

import java.util.Date;
import java.util.List;

public class ProposalDto {

	private String proposalName;
	private Integer proposalAge;
	private Long proposalMobileNumber;
	private Date proposalDateOfBirth;

	private List<NomineeDto> nomineeDtos;

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

	public List<NomineeDto> getNomineeDtos() {
		return nomineeDtos;
	}

	public void setNomineeDtos(List<NomineeDto> nomineeDtos) {
		this.nomineeDtos = nomineeDtos;
	}

}
