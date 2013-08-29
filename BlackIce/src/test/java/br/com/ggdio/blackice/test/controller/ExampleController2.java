package br.com.ggdio.blackice.test.controller;

import br.com.ggdio.blackice.annotation.Action;
import br.com.ggdio.blackice.annotation.Controller;

/**
 * Controller for general tests
 * @author Guilherme Dio
 *
 */
@Controller("/controller2")
public class ExampleController2 {
	
	/**
	 * Action for general tests
	 */
	@Action("/action-a")
	public void actionA(){
		System.out.println("Im action C");
	}
	
	
	/**
	 * Action for general tests
	 */
	@Action("action-b")
	public void actionB(){
		System.out.println("Im action B");
	}
	
	/**
	 * Action for general tests
	 */
	@Action("action-c")
	public void actionC(){
		System.out.println("Im action C");
	}
	
	/**
	 * Just a method without annotation
	 */
	public void imNotAnAction(){
		System.out.println("Im not an action, dont map me and dont call me !");
	}
}
