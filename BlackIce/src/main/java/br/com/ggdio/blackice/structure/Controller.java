package br.com.ggdio.blackice.structure;

import java.lang.reflect.Modifier;

import br.com.ggdio.blackice.structure.exception.ElementIsNotControllerException;

/**
 * Definition of a Controller, which execute actions
 * @author Guilherme Dio
 */
public final class Controller extends WebElementMap<Action> implements WebElement<Class<?>> {
	
	/**
	 * The element definition must be a class(Controller)
	 */
	private final Class<?> elementDefinition;
	
	/**
	 * Specific Constructor
	 * @param clazz - Class that represents a Controller
	 * @throws ElementIsNotControllerException if the clazz is not annotated with @Controller
	 */
	public Controller(Class<?> clazz) {
		checkAnnotation(clazz);
		this.elementDefinition = clazz;
	}
	
	/**
	 * Assure that the class is annotated with @Controller
	 * @param clazz - The class to be checked
	 */
	protected void checkAnnotation(Class<?> clazz){
		if(!Modifier.isPublic(clazz.getModifiers()))
			if(this.getAnnotation(clazz) == null)
				throw new ElementIsNotControllerException("The class "+clazz.getName()+" is not a Controller or is not annotated with @Controller");
	}
	
	/**
	 * Returns a specific name for this element
	 */
	@Override
	public String getElementName() {
		String name = this.getAnnotation(this.getElementDefinition()).value();
		if(name == null)
			name = getElementDefinition().getSimpleName();
		if(!name.startsWith("/"))
			name = "/" + name;
		if(name.endsWith("/"))
			name = name.substring(0, name.lastIndexOf("/"));
		return name;
	}
	
	/**
	 * Returns the class wich represents the Controller
	 */
	@Override
	public Class<?> getElementDefinition() {
		return this.elementDefinition;
	}
	
	private br.com.ggdio.blackice.annotation.Controller getAnnotation(Class<?> clazz) {
		return clazz.getAnnotation(br.com.ggdio.blackice.annotation.Controller.class);
	}
	
}
