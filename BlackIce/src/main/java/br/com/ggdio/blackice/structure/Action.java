package br.com.ggdio.blackice.structure;

import java.lang.reflect.Method;

/**
 * The Action(method) of specific {@link Controller}
 * @author Guilherme Dio
 *
 */
public final class Action extends WebElement{

	/**
	 * Specific Constructor
	 * @param method - The method wich represents the {@link Action}
	 */
	public Action(Method method) {
		super(method);
	}
	
}
