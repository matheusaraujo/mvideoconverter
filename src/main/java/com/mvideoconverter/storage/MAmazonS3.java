package com.mvideoconverter.storage;

import java.net.URL;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.mvideoconverter.util.Constants;
import com.mvideoconverter.util.MException;
import com.mvideoconverter.util.Util;

public class MAmazonS3 {
	
	public String generatePreSignedUrl(String fileName, String fileType) throws MException {
		
        try {            
        	
        	if (!Util.isValidFormat(fileType))
        		throw new MException("Invalid file type!");
        	
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .withRegion(Constants.CLIENT_REGION)
                    .withCredentials(new EnvironmentVariableCredentialsProvider())
                    .build();

            java.util.Date expiration = new java.util.Date();
            long expTimeMillis = expiration.getTime();
            expTimeMillis += 1000 * 60 * Constants.MINUTES_TO_EXPIRE;
            expiration.setTime(expTimeMillis);
            
            GeneratePresignedUrlRequest generatePresignedUrlRequest = 
                    new GeneratePresignedUrlRequest(Constants.BUCKET_NAME, fileName)
                    .withMethod(HttpMethod.PUT)
                    .withExpiration(expiration);
            
            URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);
    
            return url.toString();
        }
        catch(AmazonServiceException e) {
            e.printStackTrace();
            throw new MException("AmazonServiceException");
        }
        catch(SdkClientException e) {
            e.printStackTrace();
            throw new MException("SdkClientException");
        }
	}
	
	

}
