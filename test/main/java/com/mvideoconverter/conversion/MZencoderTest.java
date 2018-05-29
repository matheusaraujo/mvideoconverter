package com.mvideoconverter.conversion;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.mvideoconverter.conversion.MZencoder;
import com.mvideoconverter.util.MException;

public class MZencoderTest {

	@Test
	public void testCreateAndQueryConversion() {
		
		try {
			MZencoder mz = new MZencoder();
		
			String name = "test.mov";
			
			ConversionInfo info = mz.createConversion(name);
			
			assertNotNull(info);			
			assertTrue(StringUtils.isNumeric(info.getId()));
			assertTrue(info.getOuputUrl().startsWith("http"));
			
			int attempts = 0;
			String state = "none";
			
			while(attempts++ < 30) {
				state = mz.queryConversion(info.getId());			
				
				if (state.equals("finished"))
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
}
