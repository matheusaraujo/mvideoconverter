package com.mvideoconverter.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UtilTest {
	
	@Test
	public void testGetInputUrl() {
		String name = "test.mov";
		String inputUrl = Util.getInputUrl(name);
		assertEquals("s3://mvideoconverter/test.mov", inputUrl);
	}
	
	@Test
	public void testGetOutputUrl() {
		String name = "test.mov";
		String outputUrl = Util.getOuputUrl(name);
		assertEquals("s3://mvideoconverter/test.mp4", outputUrl);
	}
	
	@Test
	public void testRemoveExtensionOk() {
		String name = "test.mov";
		String nameWithoutExtension = Util.removeExtension(name);
		assertEquals("test", nameWithoutExtension);
	}
	
	@Test
	public void testRemoveExtensionWithoutDot() {
		String name = "test";
		String nameWithoutExtension = Util.removeExtension(name);
		assertEquals("test", nameWithoutExtension);
	}
	
	@Test
	public void testIsValidFormatTrue() {
		String type = "video/dv";
		Boolean isValid = Util.isValidFormat(type);
		assertTrue(isValid);
	}
	
	@Test
	public void testIsValidFormatFalse() {
		String type = "application/pdf";
		Boolean isValid = Util.isValidFormat(type);
		assertFalse(isValid);
	}

}
