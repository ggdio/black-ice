package br.com.ggdio.blackice.scan.exception;

import br.com.ggdio.blackice.scan.ProjectScanner;

/**
 * Exception used for errors while scanning the project with {@link ProjectScanner}
 * @author Guilherme Dio
 *
 */
public class ScannerException extends Exception {
	
	/**
	 * SerialUID
	 */
	private static final long serialVersionUID = -4477910623192076424L;

	public ScannerException(String msg) {
		super(msg);
	}

	public ScannerException(String msg, Exception e) {
		super(msg,e);
	}
}
