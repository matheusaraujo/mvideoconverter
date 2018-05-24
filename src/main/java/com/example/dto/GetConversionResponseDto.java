package com.example.dto;

public class GetConversionResponseDto {

	private String jobId;
	private String state;
	
	public GetConversionResponseDto() {
		
	}
	
	public GetConversionResponseDto(String jobId, String state) {
		this.jobId = jobId;
		this.state = state;
	}
	
	public String getJobId() {
		return jobId;
	}
	
	public String getState() {
		return state;
	}
}
