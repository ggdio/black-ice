package br.com.ggdio.blackice.structure;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import br.com.ggdio.blackice.controller.exception.ControllerConflictException;

/**
 * Class for mapping controllers
 * @author Guilherme Dio
 */
public class ControllerMap {
	
	//Instance
	private static ControllerMap instance;
	protected Map<String, Class<?>> controllers = new HashMap<String, Class<?>>();
	
	/**
	 * Private constructor for sinleton pattern
	 */
	private ControllerMap() {
		
	}
	
	/**
	 * Default getInstance 
	 * @return The instance of {@link ControllerMap}
	 */
	public static ControllerMap getInstance(){
		if(instance == null)
			instance = new ControllerMap();
		return instance;
	}
	
	/**
	 * Adds a controller to the map, identified by a unique key
	 * @param key - Key to identify the controller
	 * @param controller - Controller to be mapped
	 * @throws ControllerConflictException - If the controller has already been mapped
	 */
	public void put(String key, Class<?> controller) throws ControllerConflictException{
		if(this.controllers.containsKey(key)){
			throw new ControllerConflictException("The following controller URI has been specified more than once: "+key);
		}
		this.controllers.put(key, controller);
	}
	
	/**
	 * Returns a mapped controller
	 * @param key - Key to the controller
	 * @return
	 */
	public Class<?> get(String key){
		return this.controllers.get(key);
	}
	
	/**
	 * Returns all the mapped controllers
	 * @return
	 */
	public Collection<Class<?>> values(){
		return this.controllers.values();
	}
}
