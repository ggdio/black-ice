package br.com.ggdio.blackice.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.ggdio.blackice.config.BlackiceConfig;
import br.com.ggdio.blackice.config.BlackiceParameters;
import br.com.ggdio.blackice.scan.ProjectScanner;
import br.com.ggdio.blackice.scan.exception.ScannerException;
import br.com.ggdio.blackice.structure.Controller;
import br.com.ggdio.blackice.structure.exception.WebElementConflictException;

/**
 * 
 * Test case for the controllers
 * @author Guilherme Dio
 * @since 28/08/2013
 */
public class ControllerTestCase {
	
	//Configuration reference
	private BlackiceConfig config = BlackiceConfig.getInstance();
	
	@BeforeClass
	public static void initializeTestCase() throws WebElementConflictException, ScannerException {
		ProjectScanner scanner = ProjectScanner.getInstance();
		scanner.init();
		scanner.scan();
	}

	@Test
	public void testParameters() {
		BlackiceParameters params = config.getParameters();
		assertEquals("br.com.ggdio.blackice.test.controller", params.getBasePackage());
		assertTrue(params.isAnnotationBased());
	}
	
	@Test
	public void testController1(){
		Controller controller = config.getControllers().get("example-controller1");
		assertNotNull(controller);
		assertEquals(1, controller.values().size());
		assertEquals("/example-controller1/action", controller.getActionPath("action"));
	}
	
	@Test
	public void testController2(){
		Controller controller = config.getControllers().get("controller2");
		assertNotNull(controller);
		assertEquals(3, controller.values().size());
		assertEquals("/controller2/action-a", controller.getActionPath("action-a"));
		assertEquals("/controller2/action-b", controller.getActionPath("action-b"));
		assertEquals("/controller2/action-c", controller.getActionPath("action-c"));
	}
}
