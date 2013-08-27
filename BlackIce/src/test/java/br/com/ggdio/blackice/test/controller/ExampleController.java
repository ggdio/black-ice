package br.com.ggdio.blackice.test.controller;

import br.com.ggdio.blackice.annotation.Action;
import br.com.ggdio.blackice.annotation.Controller;

/**
 * Controller for general tests
 * @author Guilherme Dio
 *
 */
@Controller("controller")
public class ExampleController {
	
	/**
	 * Action for general tests
	 */
	@Action("action")
	public void exampleMethod(){
		System.out.println("Hello, I have been called by BlackIce !");
	}
}
