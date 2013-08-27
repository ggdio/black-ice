package br.com.ggdio.blackice.scan.exception;

/**
 * Exception for identifying when a Controller has been already mapped by BlackIce
 * @author rm69232
 *
 */
public class WebElementConflict extends Exception {

	/**
	 * SerialUID
	 */
	private static final long serialVersionUID = 6998695494711394123L;
	
	public WebElementConflict(String msg) {
		super(msg);
	}
	
	public WebElementConflict(Exception e) {
		super(e);
	}
	
	public WebElementConflict(String msg,Exception e) {
		super(msg,e);
	}

}
