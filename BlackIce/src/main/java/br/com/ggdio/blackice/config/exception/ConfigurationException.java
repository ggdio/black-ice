package br.com.ggdio.blackice.config.exception;

/**
 * 
 * This exception defines a ConfigurationException type
 * @author Guilherme Dio
 * @since 28/08/2013
 */
public class ConfigurationException extends RuntimeException {

	/**
	 * SerialUID
	 */
	private static final long serialVersionUID = 1260193612385456628L;
	
	public ConfigurationException(String msg) {
		super(msg);
	}
	
	public ConfigurationException(Exception e) {
		super(e);
	}
	
	public ConfigurationException(String msg, Exception e) {
		super(msg,e);
	}
}
