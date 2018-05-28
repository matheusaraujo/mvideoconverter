package com.mvideoconverter.storage;

import static org.junit.Assert.*;

import org.junit.Test;

import com.mvideoconverter.storage.MAmazonS3;
import com.mvideoconverter.util.MException;

public class MAmazonS3Test {

	@Test
	public void testGeneratePreSignedUrl() {
		try {
			MAmazonS3 s3 = new MAmazonS3();
			String url = s3.generatePreSignedUrl("testfile", "video/dv");
			
			assertNotNull(url);
			assertTrue(url.startsWith("http"));
			
		} catch (MException e) {
			fail();
		}		
	}
	
	@Test
	public void testGeneratePreSignedUrlFail() {
		try {
			MAmazonS3 s3 = new MAmazonS3();
			s3.generatePreSignedUrl("testfile", "application/pdf");
			
		} catch (MException e) {
			assertNotNull(e);
		}		
	}

}
