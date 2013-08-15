package br.com.ggdio.blackice.controller.exception;

/**
 * Exception for identifying when a Controller has been already mapped by BlackIce
 * @author rm69232
 *
 */
public class ControllerConflictException extends Exception {

	/**
	 * SerialUID
	 */
	private static final long serialVersionUID = 6998695494711394123L;
	
	public ControllerConflictException(String msg) {
		super(msg);
	}

}
