package br.com.ggdio.blackice.structure;

import java.util.Collection;

import br.com.ggdio.blackice.scan.exception.ActionConflict;
import br.com.ggdio.blackice.scan.exception.WebElementConflict;


/**
 * Definition of a Controller, which execute actions
 * @author Guilherme Dio
 * TODO: Criar interface de mapa para definir um tipos especifico para o {@link Controller} e o {@link WebElementMap}
 */
public final class Controller extends WebElement {
	
	/**
	 * The {@link Action} 's of the {@link Controller}
	 */
	private WebElementMap<Action> actions = new WebElementMap<Action>();
	
	/**
	 * Specific Constructor
	 * @param method - The method wich represents the {@link Action}
	 */
	public Controller(Class<?> clazz) {
		super(clazz);
	}
	
	@Override
	public Class<?> getPiece() {
		// TODO Auto-generated method stub
		return (Class<?>)super.getPiece();
	}

	/**
	 * Get the actions of this {@link Controller}
	 * @return {@link WebElementMap}
	 */
	protected WebElementMap<Action> getActions() {
		return actions;
	}
	
	/**
	 * Adds a action to the map, identified by a unique key
	 * @param key - Key to identify the action
	 * @param controller - Action to be mapped
	 * @throws ActionConflict - If the action has already been mapped
	 */
	public void putAction(String key,Action action) throws ActionConflict{
		try {
			getActions().put(key, action);
		} 
		catch (WebElementConflict e) {
			throw new ActionConflict(e);
		}
	}
	
	/**
	 * Returns a mapped controller
	 * @param key - Key to the controller
	 * @return
	 */
	public Action get(String key){
		return getActions().get(key);
	}
	
	/**
	 * Returns all the mapped controllers
	 * @return
	 */
	public Collection<Action> values(){
		return getActions().values();
	}
	
}
