package com.mvideoconverter.util;

public class Util {
	
	public static String getInputUrl(String name) {
		return String.format("s3://%s/%s", Constants.BUCKET_NAME, name);
	}
	
	public static String getOuputUrl(String name) {
		return String.format("s3://%s/%s.%s", 
			Constants.BUCKET_NAME, 
			removeExtension(name), 
			Constants.DEFAULT_OUTPUT.toString());
	}
	
	public static String removeExtension(String name) {
		if (name.lastIndexOf('.') == -1)
			return name;
		return name.substring(0, name.lastIndexOf('.'));
	}
	
	public static Boolean isValidFormat(String type) {
		return type.indexOf("video") > -1;
	}	

}
