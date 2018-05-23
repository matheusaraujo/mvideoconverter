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
	
	public Encoding() {
		client = new ZencoderClient(API_KEY);
	}
	
	public String CreateNewJob() {
		
		ZencoderCreateJobRequest job = new ZencoderCreateJobRequest();
		job.setInput("s3://zencodertesting/test.mov");
		List<ZencoderOutput> outputs = new ArrayList<ZencoderOutput>();
		
		ZencoderOutput output1 = new ZencoderOutput();
		output1.setFormat(ContainerFormat.MP4);
		outputs.add(output1);
		
		ZencoderOutput output2 = new ZencoderOutput();
		output2.setFormat(ContainerFormat.WEBM);
		outputs.add(output2);		
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
	
	public void QueryJob() throws Exception {
		throw new Exception();
	}
	
	public void CancelJob() throws Exception {
		throw new Exception();
	}
	
	public void ResubmitJob() throws Exception {
		throw new Exception();
	}
	
}
