package br.com.ggdio.blackice.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import br.com.ggdio.blackice.request.RequestType;

/**
 * Tells BlackIce that a {@link Class} is an {@link Controller}
 * @author Guilherme Dio	
 */
@Retention(RetentionPolicy.RUNTIME)
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
