package com.example.demo.dto;

import java.util.Date;

public class NomineeDto {

	private String nomineeName;
	private Long nomineeMobileNumber;
	private Integer nomineeAge;
	private Date nomineeDateOfBirth;

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

	public Date getNomineeDateOfBirth() {
		return nomineeDateOfBirth;
	}

	public void setNomineeDateOfBirth(Date nomineeDateOfBirth) {
		this.nomineeDateOfBirth = nomineeDateOfBirth;
	}

}
