package br.com.ggdio.blackice.structure;

/**
 * Definition of a Controller, which execute actions
 * @author Guilherme Dio
 *
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

	/**
	 * Get the actions of this {@link Controller}
	 * @return {@link WebElementMap}
	 */
	public WebElementMap<Action> getActions() {
		return actions;
	}
	
}
