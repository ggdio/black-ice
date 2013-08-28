package br.com.ggdio.blackice.structure.exception;

/**
 * 
 * This exception defines a WebElementException
 * @author Guilherme Dio
 * @since 27/08/2013
 */
public class WebElementException extends RuntimeException {
	
	/**
	 * SerialUID
	 */
	private static final long serialVersionUID = 8767980792073058526L;

	public WebElementException(String msg) {
		super(msg);
	}

	public WebElementException(String msg, Exception e) {
		super(msg,e);
	}
}
