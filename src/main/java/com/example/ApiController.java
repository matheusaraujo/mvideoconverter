package com.example;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.conversion.MZencoder;
import com.example.dto.GetConversionResponseDto;
import com.example.dto.PostConversionRequestDto;
import com.example.dto.PostConversionResponseDto;
import com.example.dto.PreSignedRequestDto;
import com.example.dto.PreSignedResponseDto;
import com.storage.MAmazonS3;

@RestController
public class ApiController {
	
	@RequestMapping(value = "/api/presigned", method = {RequestMethod.POST})
	public PreSignedResponseDto PostPreSigned(@RequestBody PreSignedRequestDto request) {
		MAmazonS3 s3 = new MAmazonS3();		
		String url = s3.GeneratePreSignedUrl(request.getName());
		return new PreSignedResponseDto(url);
	}
	
	@RequestMapping(value = "/api/conversion", method = {RequestMethod.POST})
	public PostConversionResponseDto PostConversion(@RequestBody PostConversionRequestDto request) {
		MZencoder zencoder = new MZencoder();
		MAmazonS3 s3 = new MAmazonS3();
		String inputUrl = s3.GetPublicLink(request.getName());
		String newName = s3.RemoveExtension(request.getName());
		String jobId = zencoder.CreateNewJob(inputUrl, newName);
		String outputUrl = zencoder.GetPublicOutputUrl(newName);
		return new PostConversionResponseDto(jobId, outputUrl);
	}
	
	@RequestMapping(value = "/api/conversion/{jobId}", method = {RequestMethod.GET})
	public GetConversionResponseDto GetConversion(@PathVariable String jobId) {
		MZencoder zencoder = new MZencoder();
		String state = zencoder.QueryJob(jobId);
		return new GetConversionResponseDto(jobId, state);
	}
}
