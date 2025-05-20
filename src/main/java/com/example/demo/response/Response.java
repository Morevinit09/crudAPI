 package com.example.demo.response;

public class Response {
	private Object Data;

	private Integer totalRecords;

	private String message;

	private String error;    
	private boolean status;
	public Object getData() {
		return Data;
	}
	public void setData(Object data) {
		Data = data;
	}
	public Integer getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
		
	

}
