package com.encoding;

import static org.junit.Assert.*;

import org.junit.Test;

public class EncodingTest {

	@Test
	public void testCreateAndQueryAJob() {
		
		Encoding en = new Encoding();
		
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
