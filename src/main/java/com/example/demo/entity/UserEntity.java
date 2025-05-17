package com.example.demo.entity;

import java.util.Date;

import com.example.demo.enums.Gender;
import com.example.demo.enums.Title;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_table")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@Column(name = "user_dob")
	private Date userdob;
	
	@Column(name = "user_address")
	private String useraddress;  
	
	@Column(name = "user_address1")
	private String useraddress1;
	
	@Column(name = "user_address2")
	private String useraddress2;
	
	@Column(name = "user_address3")
	private String useraddress3;
	
	@Column(name = "user_city")
	private String usercity;
	
	@Column(name = "user_state")
	private String userstate;
	
	@Column(name = "user_full_name")
	private String userfullName;
	
	@Column(name = "user_email")
	private String email;
	
	@Column(name = "user_mobile_number")
	private String mobileNumber;
	
	@Column(name = "user_alternatemobileno")
	private String alternatemobileno;
	
	@Column(name = "user_annualincome")
	private Long annualincome;
	
	@Column(name = "user_status")
	private Character userstatus;
	
	@Column(name = "userPannumber")
	private String pannumber;
	
	@Column(name = "user_aadharnumber")
	private String aadharnumber;
	
	@Column(name = "gender")
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@Column(name = "title")
	@Enumerated(EnumType.STRING)
	private Title title; 
	
	
   

	public Date getUserdob() {
		return userdob;
	}

	public void setUserdob(Date userdob) {
		this.userdob = userdob;
	}

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

	public String getUsercity() {
		return usercity;
	}

	public void setUsercity(String usercity) {
		this.usercity = usercity;
	}

	public String getUserstate() {
		return userstate;
	}

	public void setUserstate(String userstate) {
		this.userstate = userstate;
	}

	public String getUserfullName() {
		return userfullName;
	}

	public void setUserfullName(String userfullName) {
		this.userfullName = userfullName;
	}

	public String getAlternatemobileno() {
		return alternatemobileno;
	}

	public void setAlternatemobileno(String alternatemobileno) {
		this.alternatemobileno = alternatemobileno;
	}

	public Long getAnnualincome() {
		return annualincome;
	}

	public void setAnnualincome(Long annualincome) {
		this.annualincome = annualincome;
	}

	
	

	public String getPannumber() {
		return pannumber;
	}

	public void setPannumber(String pannumber) {
		this.pannumber = pannumber;
	}

	public String getAadharnumber() {
		return aadharnumber;
	}

	public void setAadharnumber(String aadharnumber) {
		this.aadharnumber = aadharnumber;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	
	public Character getUserstatus() {
		return userstatus;
	}

	public void setUserstatus(Character userstatus) {
		this.userstatus = userstatus;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

		

	public Date getUserDob() {
		return userdob;
	}

	public void setUserDob(Date userDob) {
		this.userdob = userDob;
	}

	public String getUserAddress() {
		return useraddress;
	}

	public void setUserAddress(String userAddress) {
		this.useraddress = userAddress;
	}

}
