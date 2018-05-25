package com.conversion;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.conversion.MZencoder;
import com.example.MException;

public class MZencoderTest {

	@Test
	public void testCreateAndQueryAJob() {
		
		try {
			MZencoder mz = new MZencoder();
			
			String input = "s3://zencodertesting/test.mov";
			String output = "test";
			
			String id = mz.createNewJob(input, output);
			
			assertNotNull(id);
			assertNotEquals("-1", id);
			
			int attempts = 0;
			String state = "none";
			
			while(attempts++ < 30) {
				state = mz.queryJob(id);			
				
				if (state == "finished")
					break;		
				
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			assertEquals("finished", state);
			assertTrue(attempts < 30);
		}
		catch(MException e) {
			fail();
		}
		
	}
	
	@Test 
	public void testGetOuputUrl() {		
		MZencoder mz = new MZencoder();
		
		String outputUrl = mz.getOuputUrl("aaa");
		
		assertTrue(outputUrl.contains("aaa"));
		assertTrue(outputUrl.startsWith("s3"));
		
	}
	
	@Test 
	public void testGetPublicOuputUrl() {		
		MZencoder mz = new MZencoder();
		
		String outputUrl = mz.getPublicOutputUrl("aaa");
		
		assertTrue(outputUrl.contains("aaa"));
		assertTrue(outputUrl.startsWith("http"));
		
	}

}
