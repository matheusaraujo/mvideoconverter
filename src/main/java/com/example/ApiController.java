package com.example;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Object> PostPreSigned(@RequestBody PreSignedRequestDto request) {
		try {
			MAmazonS3 s3 = new MAmazonS3();		
			String url = s3.GeneratePreSignedUrl(request.getName(), request.getType());
		
			return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(new PreSignedResponseDto(url));
		}
		catch(MException e) {
			return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/api/conversion", method = {RequestMethod.POST})
	public ResponseEntity<Object> PostConversion(@RequestBody PostConversionRequestDto request) {
		try {
			MZencoder zencoder = new MZencoder();
			MAmazonS3 s3 = new MAmazonS3();
			String inputUrl = s3.GetPublicLink(request.getName());
			String newName = s3.RemoveExtension(request.getName());
			String jobId = zencoder.CreateNewJob(inputUrl, newName);
			String outputUrl = zencoder.GetPublicOutputUrl(newName);
			return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(new PostConversionResponseDto(jobId, outputUrl));
		}
		catch(MException e) {
			return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/api/conversion/{jobId}", method = {RequestMethod.GET})
	public ResponseEntity<Object> GetConversion(@PathVariable String jobId) {
		try {
			MZencoder zencoder = new MZencoder();
			String state = zencoder.QueryJob(jobId);
			return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(new GetConversionResponseDto(jobId, state));
		}
		catch(MException e) {
			return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(e.getMessage());
		}
	}
}
