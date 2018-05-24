package com.conversion;

import static org.junit.Assert.*;

import org.junit.Test;

import com.conversion.MZencoder;

public class MZencoderTest {

	@Test
	public void testCreateAndQueryAJob() {
		
		MZencoder en = new MZencoder();
		
		String input = "s3://zencodertesting/test.mov";
		String output = "test";
		
		String id = en.CreateNewJob(input, output);
		
		assertNotNull(id);
		assertNotEquals("-1", id);
		
		int attempts = 0;
		String state = "none";
		
		while(attempts < 10) {
			state = en.QueryJob(id);			
			if (state == "finished")
				break;		
			attempts++;
		}
		
		assertEquals("finished", state);
		assertTrue(attempts < 10);
		
	}

}
