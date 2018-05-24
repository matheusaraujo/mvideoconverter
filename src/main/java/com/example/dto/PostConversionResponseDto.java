package com.example.dto;

public class PostConversionResponseDto {

	private String jobId;
	
	public PostConversionResponseDto() {
		
	}
	
	public PostConversionResponseDto(String jobId) {
		this.jobId = jobId;
	}
	
	public String getJobId() {
		return jobId;
	}
}
