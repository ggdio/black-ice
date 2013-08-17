package br.com.ggdio.blackice.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

/**
 * Tells BlackIce that a {@link Method} is an {@link Action} of a {@link Controller}
 * @author Guilherme Dio	
 */
@Target(ElementType.METHOD)
public @interface Action {
	String value();
}
