package br.com.ggdio.blackice.structure;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import br.com.ggdio.blackice.controller.exception.ControllerConflictException;

/**
 * Class for mapping controllers
 * @author Guilherme Dio
 */
public class WebElementMap<E extends WebElement> {
	
	protected Map<String, E> elements = new HashMap<String, E>();
	
	/**
	 * Adds a controller to the map, identified by a unique key
	 * @param key - Key to identify the controller
	 * @param controller - Controller to be mapped
	 * @throws ControllerConflictException - If the controller has already been mapped
	 */
	public void put(String key, E element) throws ControllerConflictException{
		if(this.elements.containsKey(key)){
			throw new ControllerConflictException("The following element URI has been specified more than once: "+key);
		}
		this.elements.put(key, element);
	}
	
	/**
	 * Returns a mapped controller
	 * @param key - Key to the controller
	 * @return
	 */
	public E get(String key){
		return this.elements.get(key);
	}
	
	/**
	 * Returns all the mapped controllers
	 * @return
	 */
	public Collection<E> values(){
		return this.elements.values();
	}
}
