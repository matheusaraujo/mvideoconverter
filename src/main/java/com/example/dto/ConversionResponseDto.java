package com.example.dto;

public class ConversionResponseDto {

	private String jobId;
	
	public ConversionResponseDto() {
		
	}
	
	public ConversionResponseDto(String jobId) {
		this.jobId = jobId;
	}
	
	public String getName() {
		return jobId;
	}
}
