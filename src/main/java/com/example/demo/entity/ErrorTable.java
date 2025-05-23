package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Error_Table")
public class ErrorTable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long errorId;
	private String errorMessage;
	private String errorField;
	private Character errorStatus;
	private String errorRow;
	
	
	
	
	public String getErrorRow() {
		return errorRow;
	}
	public void setErrorRow(String errorRow) {
		this.errorRow = errorRow;
	}
	public Long getErrorId() {
		return errorId;
	}
	public void setErrorId(Long errorId) {
		this.errorId = errorId;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getErrorField() {
		return errorField;
	}
	public void setErrorField(String errorField) {
		this.errorField = errorField;
	}
	public Character getErrorStatus() {
		return errorStatus;
	}
	public void setErrorStatus(Character errorStatus) {
		this.errorStatus = errorStatus;
	}
	
	
	
	
	
	
	
	
}
