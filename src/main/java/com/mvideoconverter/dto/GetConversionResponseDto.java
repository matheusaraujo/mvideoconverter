package com.mvideoconverter.dto;

public class GetConversionResponseDto {

	private String id;
	private String state;
	
	public GetConversionResponseDto() {
		
	}
	
	public GetConversionResponseDto(String id, String state) {
		this.id = id;
		this.state = state;
	}
	
	public String getId() {
		return id;
	}
	
	public String getState() {
		return state;
	}
}
