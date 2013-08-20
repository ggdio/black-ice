package br.com.ggdio.blackice.scan.exception;

import java.io.FileNotFoundException;

/**
 * Exception thrown when the configuration has not been added on the project
 * Config File: blackice.properties or blackice.xml
 * @author Guilherm Dio
 *
 */
public class ConfigurationFileNotFoundException extends FileNotFoundException {
	
	/**
	 * SerialUID
	 */
	private static final long serialVersionUID = 8781137650013572626L;

	public ConfigurationFileNotFoundException(String msg) {
		super(msg);
	}
	
}
