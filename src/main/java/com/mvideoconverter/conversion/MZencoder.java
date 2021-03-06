package com.mvideoconverter.conversion;

import com.mvideoconverter.conversion.zencoder.CreateJobResponse;
import com.mvideoconverter.conversion.zencoder.QueryJobResponse;
import com.mvideoconverter.conversion.zencoder.ZencoderApi;
import com.mvideoconverter.util.MException;
import com.mvideoconverter.util.Util;

public class MZencoder {
	
	private static final String API_KEY = "84f56563b42d1c55aafa7b4f6190d8b3";
	private static final String CREDENTIALS = "s3";
	
	public MZencoder() {
		
	}
	
	public ConversionInfo createConversion(String name) throws MException {
		
		try {
			
			ZencoderApi api = new ZencoderApi(API_KEY, CREDENTIALS);
			
			String inputUrl = Util.getInputUrl(name);
			String outputUrl = Util.getOuputUrl(name);
			
			CreateJobResponse response = api.createJob(inputUrl, outputUrl);
			
			return new ConversionInfo(response.getId(), response.getOutputs().get(0).getUrl());
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new MException("ZencoderClientException");
		}
		
	}
	
	public String queryConversion(String id) throws MException {
		try {
			ZencoderApi api = new ZencoderApi(API_KEY, CREDENTIALS);			
			QueryJobResponse response = api.queryJob(id);			
			return response.getJob().getOutputMediaFiles().get(0).getState();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new MException("ZencoderClientException");
		}
	}
	
}
