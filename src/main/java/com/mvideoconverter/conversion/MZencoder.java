package com.mvideoconverter.conversion;

import java.util.ArrayList;
import java.util.List;

import com.brightcove.zencoder.client.ZencoderClient;
import com.brightcove.zencoder.client.ZencoderClientException;
import com.brightcove.zencoder.client.model.*;
import com.brightcove.zencoder.client.request.*;
import com.brightcove.zencoder.client.response.*;
import com.mvideoconverter.util.Constants;
import com.mvideoconverter.util.MException;
import com.mvideoconverter.util.Util;

public class MZencoder {

	private ZencoderClient client;
	
	private static final String API_KEY = "84f56563b42d1c55aafa7b4f6190d8b3";
	private static final String CREDENTIAL = "s3";
	
	
	public MZencoder() {
		client = new ZencoderClient(API_KEY);
	}
	
	public ConversionInfo createConversion(String name) throws MException {
		
		try {
			ZencoderCreateJobRequest job = new ZencoderCreateJobRequest();		
			
			String inputUrl = Util.getInputUrl(name);
			String outputUrl = Util.getOuputUrl(name);
			
			job.setInput(inputUrl);
			job.setOutputs(createOutput(outputUrl));
			
			ZencoderCreateJobResponse response = client.createZencoderJob(job);
			return new ConversionInfo(response.getId(), response.getOutputs().get(0).getUrl());
			
		} catch (ZencoderClientException e) {
			e.printStackTrace();
			throw new MException("ZencoderClientException");
		}
		
	}
	
	private List<ZencoderOutput> createOutput(String outputUrl) {
		List<ZencoderOutput> outputs = new ArrayList<>();
		ZencoderOutput output1 = new ZencoderOutput();
		output1.setUrl(outputUrl);
		output1.setFormat(Constants.DEFAULT_OUTPUT);
		output1.setCredentials(CREDENTIAL);
		outputs.add(output1);
		return outputs;
		
	}
	
	public String queryConversion(String id) throws MException {
		try {
			ZencoderJobDetail details = client.getZencoderJob(id);			
			ZencoderMediaFile output1 = details.getOutputMediaFiles().get(0);			
			State st = output1.getState();			
			return st.toString();
		} catch (ZencoderClientException e) {
			e.printStackTrace();
			throw new MException("ZencoderClientException");
		}
	}
	
}
