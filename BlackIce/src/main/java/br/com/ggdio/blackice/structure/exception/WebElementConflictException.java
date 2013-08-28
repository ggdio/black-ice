package br.com.ggdio.blackice.structure.exception;

/**
 * Exception for identifying when a Controller has been already mapped by BlackIce
 * @author Guilherme Dio
 */
public class WebElementConflictException extends Exception {

	/**
	 * SerialUID
	 */
	private static final long serialVersionUID = 6998695494711394123L;
	
	public WebElementConflictException(String msg) {
		super(msg);
	}
	
	public WebElementConflictException(Exception e) {
		super(e);
	}
	
	public WebElementConflictException(String msg,Exception e) {
		super(msg,e);
	}

}
