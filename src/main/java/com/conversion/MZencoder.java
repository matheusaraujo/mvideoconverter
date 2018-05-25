package com.conversion;

import java.util.ArrayList;
import java.util.List;

import com.brightcove.zencoder.client.ZencoderClient;
import com.brightcove.zencoder.client.ZencoderClientException;
import com.brightcove.zencoder.client.model.*;
import com.brightcove.zencoder.client.request.*;
import com.brightcove.zencoder.client.response.*;
import com.example.MException;

public class MZencoder {

	private ZencoderClient client;
	
	private final String API_KEY = "84f56563b42d1c55aafa7b4f6190d8b3";
	private final String CREDENTIAL = "s3";
	private final ContainerFormat DEFAULT_OUTPUT = ContainerFormat.MP4;
	private final String BUCKET = "mvideoconverter";
	
	public MZencoder() {
		client = new ZencoderClient(API_KEY);
	}
	
	public String CreateNewJob(String inputUrl, String outputFileName) throws MException {
		
		try {
			ZencoderCreateJobRequest job = new ZencoderCreateJobRequest();		
			job.setInput(inputUrl);
			
			List<ZencoderOutput> outputs = new ArrayList<ZencoderOutput>();
			ZencoderOutput output1 = new ZencoderOutput();
			output1.setFormat(DEFAULT_OUTPUT);
			String outputUrl = GetOuputUrl(outputFileName);
			output1.setUrl(outputUrl);
			output1.setCredentials(CREDENTIAL);
			outputs.add(output1);		
			job.setOutputs(outputs);
			
			ZencoderCreateJobResponse response = client.createZencoderJob(job);
			return response.getId();
		} catch (ZencoderClientException e) {
			e.printStackTrace();
			throw new MException("ZencoderClientException");
		}
		
	}
	
	public String QueryJob(String id) throws MException {
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
	
	
	public String GetOuputUrl(String fileName) {
		return String.format("s3://%s/%s.%s", BUCKET, fileName, DEFAULT_OUTPUT.toString());
	}
	
	public String GetPublicOutputUrl(String fileName) {
		return String.format("http://%s.s3.amazonaws.com/%s.%s", BUCKET, fileName, DEFAULT_OUTPUT.toString());
	}
	
}
