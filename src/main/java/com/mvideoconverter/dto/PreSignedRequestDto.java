package com.mvideoconverter.dto;

public class PreSignedRequestDto {

	private String name;
	private String type;
	
	public PreSignedRequestDto() {
		
	}
	
	public PreSignedRequestDto(String name, String type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}
	
}
