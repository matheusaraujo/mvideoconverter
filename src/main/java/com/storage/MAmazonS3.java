package com.storage;

import java.net.URL;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;

public class MAmazonS3 {
	
	private final String CLIENT_REGION = "us-east-1";
	private final String BUCKET_NAME = "mvideoconverter";
	private final int MINUTES_TO_EXPIRE = 60;
	
	public String GeneratePreSignedUrl(String fileName) {
		
        try {            
        	
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .withRegion(CLIENT_REGION)
                    .withCredentials(new EnvironmentVariableCredentialsProvider())
                    .build();

            java.util.Date expiration = new java.util.Date();
            long expTimeMillis = expiration.getTime();
            expTimeMillis += 1000 * 60 * MINUTES_TO_EXPIRE;
            expiration.setTime(expTimeMillis);
            
            GeneratePresignedUrlRequest generatePresignedUrlRequest = 
                    new GeneratePresignedUrlRequest(BUCKET_NAME, fileName)
                    .withMethod(HttpMethod.PUT)
                    .withExpiration(expiration);
            
            URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);
    
            return url.toString();
        }
        catch(AmazonServiceException e) {
            e.printStackTrace();
            return "-1";
        }
        catch(SdkClientException e) {
            e.printStackTrace();
            return "-1";
        }
	}
	
	public String GetPublicLink(String fileName) {
		return String.format("s3://%s/%s", BUCKET_NAME, fileName);
	}
	
	public String RemoveExtension(String fileName) {
		return fileName.substring(0, fileName.lastIndexOf('.'));
	}

}
