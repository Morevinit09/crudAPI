package com.example.demo.Pagination;

public class CustomResponse {
	private String userfullName;
	private String mobileNumber;
	private String email;
	private Long userId;
	
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserfullName() {
		return userfullName;
		
	}
	public void setUserfullName(String userfullName) {
		this.userfullName = userfullName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAadharnumber() {
		return aadharnumber;
	}
	public void setAadharnumber(String aadharnumber) {
		this.aadharnumber = aadharnumber;
	}
	private String aadharnumber;
}
