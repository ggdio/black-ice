package br.com.ggdio.blackice.scan.exception;

/**
 * Exception thrown when the project scan is called but it has not been initiated yet.
 * @author Guilherme Dio
 *
 */
public class ScanNotYetInitializedException extends RuntimeException {
	
	/**
	 * SerialUID
	 */
	private static final long serialVersionUID = 1288667197613258777L;

	public ScanNotYetInitializedException(String msg){
		super(msg);
	}

}
