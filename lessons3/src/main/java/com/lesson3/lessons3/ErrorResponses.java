package com.lesson4.lessons4;

import java.time.LocalDateTime;

public class ErrorResponses {
	private LocalDateTime localDateTime;
	private Integer status;
	private String error;
	private String message;
	private String path;
	public ErrorResponses(LocalDateTime localDateTime, Integer status, String error, String message, String path) {
		this.localDateTime = localDateTime.now();
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}
	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}
	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
