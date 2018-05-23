package com.encoding;

import static org.junit.Assert.*;

import org.junit.Test;

public class EncodingTest {

	@Test
	public void testCreateNewJob() {
		
		Encoding en = new Encoding();
		
		String id = en.CreateNewJob();
		
		assertNotNull(id);
		assertNotEquals("-1", id);
		
	}

}
