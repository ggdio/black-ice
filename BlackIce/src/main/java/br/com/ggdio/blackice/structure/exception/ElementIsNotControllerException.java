package br.com.ggdio.blackice.structure.exception;

/**
 * 
 * This runtime-exception defines a ElementIsNotControllerException
 * @author Guilherme Dio
 * @since 27/08/2013
 */
public class ElementIsNotControllerException extends WebElementException {

	/**
	 * SerialUID
	 */
	private static final long serialVersionUID = -8296333491731464444L;
	
	public ElementIsNotControllerException(String msg) {
		super(msg);
	}

	public ElementIsNotControllerException(String msg, Exception e) {
		super(msg,e);
	}
}
