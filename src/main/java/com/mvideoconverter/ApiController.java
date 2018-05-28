package com.mvideoconverter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mvideoconverter.conversion.ConversionInfo;
import com.mvideoconverter.conversion.MZencoder;
import com.mvideoconverter.dto.GetConversionResponseDto;
import com.mvideoconverter.dto.PostConversionRequestDto;
import com.mvideoconverter.dto.PostConversionResponseDto;
import com.mvideoconverter.dto.PreSignedRequestDto;
import com.mvideoconverter.dto.PreSignedResponseDto;
import com.mvideoconverter.storage.MAmazonS3;
import com.mvideoconverter.util.MException;

@RestController
public class ApiController {

	@RequestMapping(value = "/api/presigned", method = { RequestMethod.POST })
	public ResponseEntity<Object> postPreSigned(@RequestBody PreSignedRequestDto request) {
		try {
			MAmazonS3 s3 = new MAmazonS3();
			String url = s3.generatePreSignedUrl(request.getName(), request.getType());
			return ResponseEntity.status(HttpStatus.CREATED).body(new PreSignedResponseDto(url));
		} catch (MException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@RequestMapping(value = "/api/conversion", method = { RequestMethod.POST })
	public ResponseEntity<Object> postConversion(@RequestBody PostConversionRequestDto request) {
		try {
			MZencoder zencoder = new MZencoder();
			ConversionInfo info = zencoder.createConversion(request.getName());
			return ResponseEntity.status(HttpStatus.CREATED).body(
				new PostConversionResponseDto(info.getId(), info.getOuputUrl()));
		} catch (MException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@RequestMapping(value = "/api/conversion/{id}", method = { RequestMethod.GET })
	public ResponseEntity<Object> getConversion(@PathVariable String id) {
		try {
			MZencoder zencoder = new MZencoder();
			String state = zencoder.queryConversion(id);
			return ResponseEntity.status(HttpStatus.CREATED).body(new GetConversionResponseDto(id, state));
		} catch (MException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}
