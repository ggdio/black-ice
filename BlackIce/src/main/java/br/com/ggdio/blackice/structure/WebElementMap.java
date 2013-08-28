package br.com.ggdio.blackice.structure;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import br.com.ggdio.blackice.structure.exception.WebElementConflictException;

/**
 * Class for mapping controllers
 * @author Guilherme Dio
 */
public class WebElementMap<E extends WebElement<?>> {
	
	protected Map<String, E> elements = new HashMap<String, E>();
	
	/**
	 * Adds a webElement to the map, identified by a unique key
	 * @param key - Key to identify the webElement
	 * @param element - webElement to be mapped
	 * @throws WebElementConflictException - If the webElement has already been mapped
	 */
	public void put(String key, E element) throws WebElementConflictException{
		if(this.elements.containsKey(key)){
			throw new WebElementConflictException("The following element URI has been specified more than once: "+key);
		}
		this.elements.put(key, element);
	}
	
	/**
	 * Adds a webElement to the map.
	 * The key will be defined by the element name.
	 * @param controller - webElement to be mapped
	 * @throws WebElementConflictException - If the webElement has already been mapped
	 */
	public void put(E element) throws WebElementConflictException{
		this.put(element.getElementName(), element);
	}
	
	/**
	 * Returns a mapped webElement
	 * @param key - Key to the webElement
	 * @return The value of the specific key
	 */
	public E get(String key){
		return this.elements.get(key);
	}
	
	/**
	 * Returns all the mapped webElements
	 * @return a Collection with the mapped webElements
	 */
	public Collection<E> values(){
		return this.elements.values();
	}
}
