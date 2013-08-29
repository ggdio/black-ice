package br.com.ggdio.blackice.test.controller;

import br.com.ggdio.blackice.annotation.Action;
import br.com.ggdio.blackice.annotation.Controller;

/**
 * Controller for general tests
 * @author Guilherme Dio
 *
 */
@Controller("example-controller1")
public class ExampleController1 {
	
	/**
	 * Action for general tests
	 */
	@Action("action")
	public void exampleMethod(){
		System.out.println("Hello, I have been called by BlackIce !");
	}
}
