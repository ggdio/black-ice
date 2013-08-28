package br.com.ggdio.blackice.structure;

import java.lang.reflect.Method;

import br.com.ggdio.blackice.structure.exception.ElementIsNotActionException;

/**
 * The Action(method) of specific {@link Controller}
 * @author Guilherme Dio
 *
 */
public final class Action implements WebElement<Method>{

	/**
	 * The element definition must be a method(Action)
	 */
	private final Method elementDefinition;
	
	/**
	 * Specific Constructor
	 * @param method - The method wich represents the {@link Action}
	 * @throws ElementIsNotActionException if the method is not annotated with @Action
	 */
	public Action(Method method) {
		checkAnnotation(method);
		this.elementDefinition = method;
	}
	
	/**
	 * Assure that the method is annotated with @Action
	 * @param clazz
	 * @throws ElementIsNotActionException if the method is not accessible or is not annotated with @Action
	 */
	protected void checkAnnotation(Method method){
		if(!method.isAccessible())
			if(this.getAnnotation(method) == null)
				throw new ElementIsNotActionException("The method "+method.getName()+" is not a Action or is not annotated with @Action");
	}
	
	/**
	 * Returns a specific name for this element
	 */
	@Override
	public String getElementName() {
		String name = this.getAnnotation(getElementDefinition()).value();
		if(name == null)
			name = getElementDefinition().getName();
		if(!name.startsWith("/"))
			name = "/" + name;
		return name;
	}
	
	/**
	 * Returns the method wich defines the Action
	 */
	@Override
	public Method getElementDefinition() {
		return this.elementDefinition;
	}
	
	private br.com.ggdio.blackice.annotation.Action getAnnotation(Method method){
		return method.getAnnotation(br.com.ggdio.blackice.annotation.Action.class);
	}
	
}
