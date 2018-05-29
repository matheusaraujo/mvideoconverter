package com.mvideoconverter.conversion.zencoder;

public class CreateJobOutputRequest {
	private String url;
	private String credentials;

	public CreateJobOutputRequest(String url, String credentials) {
		this.url = url;
		this.credentials = credentials;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCredentials() {
		return credentials;
	}

	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}
}
