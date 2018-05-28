package com.mvideoconverter.dto;

public class PreSignedResponseDto {

	private String url;
	
	public PreSignedResponseDto() {
		
	}
	
	public PreSignedResponseDto(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}
	
}
