package br.com.ggdio.blackice.structure.exception;

/**
 * 
 * This runtime-exception defines a ElementIsNotActionException
 * @author Guilherme Dio
 * @since 27/08/2013
 */
public class ElementIsNotActionException extends WebElementException {

	/**
	 * SerialUID
	 */
	private static final long serialVersionUID = -8296333491731464444L;
	
	public ElementIsNotActionException(String msg) {
		super(msg);
	}

	public ElementIsNotActionException(String msg, Exception e) {
		super(msg,e);
	}
}
