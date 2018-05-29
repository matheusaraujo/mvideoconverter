package com.mvideoconverter.conversion.zencoder;

import java.util.HashMap;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

public class ZencoderApi {
	
	public static final String API_URL = "https://app.zencoder.com/api/v2";

	private String apiKey = null;
	private String credentials = null;
	
	public ZencoderApi(String apiKey, String credentials) {
		this.apiKey = apiKey;
		this.credentials = credentials;
	}
	
	public CreateJobResponse createJob(String inputUrl, String outputUrl) throws ZencoderApiException {
		CreateJobRequest request = new CreateJobRequest();
		request.setInput(inputUrl);
		request.addOutput(outputUrl, credentials);
		return createJob(request);
	}
	
	public CreateJobResponse createJob(CreateJobRequest request) throws ZencoderApiException {
		
        try {
        	
        	String url = API_URL + "/jobs";

        	Gson gson = new Gson();
        	String body = gson.toJson(request);
            HttpHeaders headers = getHeaders();
            
            HttpEntity<String> entity = new HttpEntity<String>(body, headers);
        
            RestTemplate rt = new RestTemplate();
            
            ResponseEntity<String> response = rt.exchange(
                    url,
                    HttpMethod.POST,
                    entity,
                    String.class,
                    new HashMap<String, Object>());
            
            CreateJobResponse createJobResponse = 
            		gson.fromJson(response.getBody(), CreateJobResponse.class);
            
            return createJobResponse;
            
        } catch (Exception e) {
            throw new ZencoderApiException(e.getMessage());
        }

	}
	
	public QueryJobResponse queryJob(String id) throws ZencoderApiException {
		try {
			String url = API_URL + "/jobs/" + id + ".json";
			
			Gson gson = new Gson();
			
			HttpHeaders headers = getHeaders();
			HttpEntity<String> entity = new HttpEntity<String>("", headers);
			
			RestTemplate rt = new RestTemplate();
			
			ResponseEntity<String> response = rt.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    String.class,
                    new HashMap<String, Object>());
			
			QueryJobResponse queryJobResponse =
					gson.fromJson(response.getBody(), QueryJobResponse.class);
			
			return queryJobResponse;
			
		} catch(Exception e) {
			throw new ZencoderApiException(e.getMessage());
		}
	}
	
	private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        headers.add("Zencoder-Api-Key", apiKey);
        return headers;
    }
	
}
