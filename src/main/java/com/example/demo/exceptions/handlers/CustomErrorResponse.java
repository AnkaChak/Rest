package com.example.demo.exceptions.handlers;

import java.time.LocalDateTime;

public class CustomErrorResponse {
	
	LocalDateTime timeStamp;
	int status;
	String errorCode;
	String errorMessage;
	public CustomErrorResponse() {
		
	}
	public CustomErrorResponse(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

}
