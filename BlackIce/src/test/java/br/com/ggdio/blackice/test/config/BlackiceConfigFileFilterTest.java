package br.com.ggdio.blackice.test.config;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.ggdio.blackice.config.BlackiceConfigFileFilter;

public class BlackiceConfigFileFilterTest extends BlackiceConfigFileFilter {

	@Test
	public void testAcceptCorrectPropertiesFileName() {
		assertEquals(true, super.accept(null, "blackice.properties"));
	}
	
	@Test
	public void testAcceptCorrectXMLFileName() {
		assertEquals(true, super.accept(null, "blackice.xml"));
	}
	
	@Test
	public void testDontAcceptInvalidFileExtension() {
		assertEquals(false, super.accept(null, "blackice.invalid"));
	}
	
	@Test
	public void testDontAcceptInvalidFileName() {
		assertEquals(false, super.accept(null, "invalid.properties"));
	}
	
	@Test
	public void testDontAcceptInvalidFileNameAndExtension() {
		assertEquals(false, super.accept(null, "invalid.invalid"));
	}

}
