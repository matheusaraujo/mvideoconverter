package com.encoding;

import java.util.ArrayList;
import java.util.List;

import com.brightcove.zencoder.client.ZencoderClient;
import com.brightcove.zencoder.client.ZencoderClientException;
import com.brightcove.zencoder.client.model.*;
import com.brightcove.zencoder.client.request.*;
import com.brightcove.zencoder.client.response.*;

public class Encoding {

	private ZencoderClient client;
	
	private final String API_KEY = "84f56563b42d1c55aafa7b4f6190d8b3";
	private final String CREDENTIAL = "s3";
	private final ContainerFormat DEFAULT_OUTPUT = ContainerFormat.MP4;
	private final String OUTPUT_URL = "s3://mvideoconverter";
	
	public Encoding() {
		client = new ZencoderClient(API_KEY);
	}
	
	/**
	 * Create a Job to encoding and returns its id
	 * @param inputUrl, video input url
	 * @param outputFileName, name of the converted file
	 * @return id of the job
	 */
	public String CreateNewJob(String inputUrl, String outputFileName) {
		
		ZencoderCreateJobRequest job = new ZencoderCreateJobRequest();
		
		job.setInput(inputUrl);
		
		List<ZencoderOutput> outputs = new ArrayList<ZencoderOutput>();
		ZencoderOutput output1 = new ZencoderOutput();
		output1.setFormat(DEFAULT_OUTPUT);
		String outputUrl = String.format("%s/%s.%s", OUTPUT_URL, outputFileName, DEFAULT_OUTPUT.toString());
		output1.setUrl(outputUrl);
		output1.setCredentials(CREDENTIAL);
		outputs.add(output1);		
		job.setOutputs(outputs);
		
		ZencoderCreateJobResponse response;
		
		try {
			response = client.createZencoderJob(job);
			return response.getId();
		} catch (ZencoderClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "-1";
		}
		
	}
	
	/***
	 * Query the state of a Job
	 * @param id Job's id
	 * @return Job's state
	 */
	public String QueryJob(String id) {
		
		ZencoderJobDetail details;
		
		try {
			details = client.getZencoderJob(id);
			
			ZencoderMediaFile output1 = details.getOutputMediaFiles().get(0);
			
			State st = output1.getState();
			
			return st.toString();
			
		} catch (ZencoderClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "none";
		}

	}
	
}
