package com.mvideoconverter.dto;

public class PostConversionResponseDto {

	private String id;
	private String path;
	
	public PostConversionResponseDto() {
		
	}
	
	public PostConversionResponseDto(String id, String path) {
		this.id = id;
		this.path = path;
	}
	
	public String getId() {
		return id;
	}
	
	public String getPath() {
		return path;
	}
}
