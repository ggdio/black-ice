package br.com.ggdio.blackice.scan.exception;

import br.com.ggdio.blackice.structure.exception.WebElementConflictException;

/**
 * Exception for identifying when an Action has been already mapped by BlackIce
 * @author Guilherme Dio
 *
 */
public class ActionConflict extends WebElementConflictException {


	/**
	 * Generated SerialUID
	 */
	private static final long serialVersionUID = -1844345064667515846L;

	public ActionConflict(String msg) {
		super(msg);
	}
	
	public ActionConflict(Exception e) {
		super(e);
	}
	
	public ActionConflict(String msg, Exception e) {
		super(msg,e);
	}
}
