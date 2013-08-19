package br.com.ggdio.blackice.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

import br.com.ggdio.blackice.enumeration.RequestType;

/**
 * Tells BlackIce that a {@link Method} is an {@link Action} of a {@link Controller}
 * @author Guilherme Dio	
 */
@Target(ElementType.METHOD)
public @interface Action {
	/**
	 * The path to call the specified action
	 * OBS: It will be completed with the {@link Controller} path
	 * Ex: Controller Path = /controller | Acton Path = /controller/myaction
	 */
	String value() default "";
	
	/**
	 * The {@link RequestType} this Action must receive
	 */
	RequestType type() default RequestType.ALL;
}
