package com.mvideoconverter.conversion.zencoder;

import java.util.ArrayList;
import java.util.List;

public class CreateJobResponse {

	private String id;
	private List<CreateJobOutputResponse> outputs = new ArrayList<CreateJobOutputResponse>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<CreateJobOutputResponse> getOutputs() {
		return outputs;
	}

	public void setOutputs(List<CreateJobOutputResponse> outputs) {
		this.outputs = outputs;
	}
	
	
	
}
