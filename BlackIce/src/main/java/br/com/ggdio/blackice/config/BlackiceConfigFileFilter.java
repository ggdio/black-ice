package br.com.ggdio.blackice.config;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

/**
 * Filter for identifying the blackice configuration file
 * @author Guilherme Dio
 * @since 30/08/2013
 */
public class BlackiceConfigFileFilter implements FilenameFilter{
	
	/**
	 * Blackice configuration file pattern
	 * <p>Accepted Filename/Extension: 
	 * <p>blackice.properties
	 * <p>blackice.xml
	 */
	private static final String BLACKICE_PATTERN = "(blackice+(\\.(?i)(properties|xml))$)";
	private final Pattern pattern;
	
	public BlackiceConfigFileFilter() {
		pattern = Pattern.compile(BLACKICE_PATTERN);
	}
	
	/**
	 * Check if the 'File' is a blackice configuration file
	 */
	@Override
	public boolean accept(File dir, String name) {
		return pattern.matcher(name).matches();
	}
	
}
