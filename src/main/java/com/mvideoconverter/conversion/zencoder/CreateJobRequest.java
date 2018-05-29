package com.mvideoconverter.conversion.zencoder;

import java.util.ArrayList;
import java.util.List;

public class CreateJobRequest {

	private String input;
	private List<CreateJobOutputRequest> outputs = new ArrayList<CreateJobOutputRequest>();
	
	public CreateJobRequest() {
		
	}
	
	public String getInput() {
		return input;
	}
	
	public void setInput(String input) {
		this.input = input;
	}
	
	public List<CreateJobOutputRequest> getOuputs(){
		return outputs;
	}
	
	public void setOutputs(List<CreateJobOutputRequest> outputs) {
		this.outputs = outputs;
	}
	
	public void addOutput(String output, String credentials) {
		outputs.add(new CreateJobOutputRequest(output, credentials));
	}
	
	public void addOutput(CreateJobOutputRequest output) {
		outputs.add(output);
	}
	
}
