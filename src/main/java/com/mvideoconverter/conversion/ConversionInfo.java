package com.mvideoconverter.conversion;

public class ConversionInfo {

	private String id;
	private String outputUrl;
	
	public ConversionInfo() {
		
	}
	
	public ConversionInfo(String id, String outputUrl) {
		this.id = id;
		this.outputUrl = outputUrl;
	}
	
	public String getId() {
		return id;
	}
	
	public String getOuputUrl() {
		return outputUrl;
	}
}
