package com.storage;

import static org.junit.Assert.*;

import org.junit.Test;

public class MAmazonS3Test {

	@Test
	public void testGeneratePreSignedUrl() {
		
		MAmazonS3 s3 = new MAmazonS3();
		
		String url = s3.GeneratePreSignedUrl("testfile");
		
		assertNotNull(url);
		assertNotEquals("-1", url);
		assertTrue(url.startsWith("http"));
		
	}

}
