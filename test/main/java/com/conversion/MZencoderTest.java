package com.conversion;

import static org.junit.Assert.*;

import org.junit.Test;

import com.conversion.MZencoder;

public class MZencoderTest {

	@Test
	public void testCreateAndQueryAJob() {
		
		MZencoder mz = new MZencoder();
		
		String input = "s3://zencodertesting/test.mov";
		String output = "test";
		
		String id = mz.CreateNewJob(input, output);
		
		assertNotNull(id);
		assertNotEquals("-1", id);
		
		int attempts = 0;
		String state = "none";
		
		while(attempts < 10) {
			state = mz.QueryJob(id);			
			if (state == "finished")
				break;		
			attempts++;
		}
		
		assertEquals("finished", state);
		assertTrue(attempts < 10);
		
	}
	
	@Test 
	public void testGetOuputUrl() {		
		MZencoder mz = new MZencoder();
		
		String outputUrl = mz.GetOuputUrl("aaa");
		
		assertTrue(outputUrl.contains("aaa"));
		assertTrue(outputUrl.startsWith("s3"));
		
	}
	
	@Test 
	public void testGetPublicOuputUrl() {		
		MZencoder mz = new MZencoder();
		
		String outputUrl = mz.GetPublicOutputUrl("aaa");
		
		assertTrue(outputUrl.contains("aaa"));
		assertTrue(outputUrl.startsWith("http"));
		
	}

}
