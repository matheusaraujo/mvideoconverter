package com.storage;

import java.net.URL;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;

public class MAmazonS3 {
	
	public String GeneratePreSignedUrl() {
		String clientRegion = "us-east-1";
        String bucketName = "mvideoconverter";
        String objectKey = "12345";
        
        try {            
        	
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .withRegion(clientRegion)
                    .withCredentials(new ProfileCredentialsProvider())
                    .build();

            java.util.Date expiration = new java.util.Date();
            long expTimeMillis = expiration.getTime();
            expTimeMillis += 1000 * 60 * 60;
            expiration.setTime(expTimeMillis);
            
            GeneratePresignedUrlRequest generatePresignedUrlRequest = 
                    new GeneratePresignedUrlRequest(bucketName, objectKey)
                    .withMethod(HttpMethod.GET)
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

}
