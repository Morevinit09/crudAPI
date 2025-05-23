package com.example.demo.dto;

import java.util.Date;

import com.example.demo.enums.Gender;
import com.example.demo.enums.Title;


public class UserDto {

	private Date userDob;

	private String useraddress;

	private String useraddress1;

	private String useraddress2;

	private String useraddress3;

	private String userCity;

	private String userState;

	private String userFullName;

	private String email;

	private Long mobileNumber;

	private Gender gender;

	private Title title;
	
	
	public String getUseraddress() {
		return useraddress;
	}

	public void setUseraddress(String useraddress) {
		this.useraddress = useraddress;
	}

	public String getUseraddress1() {
		return useraddress1;
	}

	public void setUseraddress1(String useraddress1) {
		this.useraddress1 = useraddress1;
	}

	public String getUseraddress2() {
		return useraddress2;
	}

	public void setUseraddress2(String useraddress2) {
		this.useraddress2 = useraddress2;
	}

	public String getUseraddress3() {
		return useraddress3;
	}

	public void setUseraddress3(String useraddress3) {
		this.useraddress3 = useraddress3;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Long getAlternatemobileno() {
		return alternatemobileno;
	}

	public void setAlternatemobileno(Long alternatemobileno) {
		this.alternatemobileno = alternatemobileno;
	}

	private Long alternatemobileno;

	private Long annualincome;

	public Long getAnnualincome() {
		return annualincome;
	}

	public void setAnnualincome(Long annualincome) {
		this.annualincome = annualincome;
	}

	private String pannumber;

	private String aadharnumber;

	public String getAadharnumber() {
		return aadharnumber;
	}

	public void setAadharnumber(String aadharnumber) {
		this.aadharnumber = aadharnumber;
	}

	public Date getUserDob() {
		return userDob;
	}

	public void setUserDob(Date userDob) {
		this.userDob = userDob;
	}


	public String getUserCity() {
		return userCity;
	}

	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}

	public String getUserState() {
		return userState;
	}

	public void setUserState(String userState) {
		this.userState = userState;
	}

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getMobileNumber() {
		return mobileNumber;
	}

	
	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPannumber() {
		return pannumber;
	}

	public void setPannumber(String pannumber) {
		this.pannumber = pannumber;
	}

	public Gender getGender() {
		return gender;
	}

	public com.example.demo.enums.Title getTitle() {
		return title;
	}

	public void setTitle(com.example.demo.enums.Title title) {
		this.title = title;
	}

	
	
}
