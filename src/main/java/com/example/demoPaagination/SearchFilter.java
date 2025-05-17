package com.example.demoPaagination;

public class SearchFilter {
	private String userfullName;
	private String mobileNumber;
	private String email;
	private String aadharnumber;
	public String getAadharnumber() {
		return aadharnumber;
	}

	public void setAadharnumber(String aadharnumber) {
		this.aadharnumber = aadharnumber;
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

	private String usercity;
	private String userstate;

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

	public String getUserfullName() {
		return userfullName;
	}

	public void setUserfullName(String userfullName) {
		this.userfullName = userfullName;
	}

}
