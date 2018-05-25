package com.storage;

import static org.junit.Assert.*;

import org.junit.Test;

import com.example.MException;

public class MAmazonS3Test {

	@Test
	public void testGeneratePreSignedUrl() {
		try {
			MAmazonS3 s3 = new MAmazonS3();
			String url = s3.GeneratePreSignedUrl("testfile", "video/dv");
			
			assertNotNull(url);
			assertNotEquals("-1", url);
			assertTrue(url.startsWith("http"));
			
		} catch (MException e) {
			fail();
		}		
	}
	
	@Test
	public void testGeneratePreSignedUrlFail() {
		try {
			MAmazonS3 s3 = new MAmazonS3();
			s3.GeneratePreSignedUrl("testfile", "application/pdf");
			
		} catch (MException e) {
			assertNotNull(e);
		}		
	}
	
	@Test
	public void testGetPublicLink() {
		
		MAmazonS3 s3 = new MAmazonS3();
		
		String fileName = "aaa";
		
		String publicLink = s3.GetPublicLink(fileName);
		
		assertNotNull(publicLink);
		assertTrue(publicLink.contains(fileName));
		assertTrue(publicLink.startsWith("s3"));		
	}
	
	@Test
	public void testRemoveExtension() {
		
		MAmazonS3 s3 = new MAmazonS3();
		
		String fullName = "aaa.mp3";
		
		String name = s3.RemoveExtension(fullName);
		
		assertEquals("aaa", name);
		assertTrue(fullName.contains(name));
		
	}
	
	@Test
	public void testeIsValidFormatTrue() {
		MAmazonS3 s3 = new MAmazonS3();
		
		Boolean valid = s3.IsValidFormat("video/dv");
		assertTrue(valid);
	}
	
	@Test
	public void testeIsValidFormatFalse() {
		MAmazonS3 s3 = new MAmazonS3();
		
		Boolean valid = s3.IsValidFormat("application/pdf");
		assertFalse(valid);
	}

}
