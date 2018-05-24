package com.example;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.conversion.MZencoder;
import com.example.dto.ConversionRequestDto;
import com.example.dto.ConversionResponseDto;
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
	public ConversionResponseDto PostConversion(@RequestBody ConversionRequestDto request) {
		MZencoder zencoder = new MZencoder();
		MAmazonS3 s3 = new MAmazonS3();
		String inputUrl = s3.GetPublicLink(request.getName());
		String jobId = zencoder.CreateNewJob(inputUrl, "test");
		return new ConversionResponseDto(jobId);
	}
}
