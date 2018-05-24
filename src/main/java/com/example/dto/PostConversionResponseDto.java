package com.example.dto;

public class PostConversionResponseDto {

	private String jobId;
	private String path;
	
	public PostConversionResponseDto() {
		
	}
	
	public PostConversionResponseDto(String jobId, String path) {
		this.jobId = jobId;
		this.path = path;
	}
	
	public String getJobId() {
		return jobId;
	}
	
	public String getPath() {
		return path;
	}
}
