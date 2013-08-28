package br.com.ggdio.blackice.annotation;

import br.com.ggdio.blackice.request.RequestType;

/**
 * Tells BlackIce that a {@link Class} is an {@link Controller}
 * @author Guilherme Dio	
 */
public @interface Controller {
	/**
	 * The path to call the specified controller
	 */
	String value() default "";
	
	/**
	 * The {@link RequestType} this Controller must receive
	 */
	RequestType type() default RequestType.ALL;
}
