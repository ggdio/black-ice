package br.com.ggdio.blackice.config;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import br.com.ggdio.blackice.structure.Controller;
import br.com.ggdio.blackice.structure.WebElementMap;

/**
 * 
 * This class contains all the configuration of BlackIce framework
 * @author Guilherme Dio
 * @since 27/08/2013
 */
public class BlackiceConfig {
	/**
	 * Singleton instance
	 */
	private static BlackiceConfig instance;
	
	/**
	 * Configuration fields
	 */
	private BlackiceParameters parameters = new BlackiceParameters();
	private WebElementMap<Controller> controllers = new WebElementMap<Controller>();
	private final Map<FileType, URL> configFiles = new HashMap<FileType, URL>();
	
	public enum FileType{
		PROPERTIES,
		XML,
		//JSON - not yet implemented,
		//CSV - not yet implemented
	}
	
	private BlackiceConfig() {
		configFiles.put(FileType.PROPERTIES, BlackiceConfig.class.getResource("/blackice.properties"));
		configFiles.put(FileType.XML, BlackiceConfig.class.getResource("/blackice.xml"));
		//Not yet implemented types:
		//configFiles.put("json", BlackiceConfig.class.getResource("/blackice.json"));
		//configFiles.put("csv", BlackiceConfig.class.getResource("/blackice.csv"));
	}
	
	/**
	 * Gets an unique instance of the configuration
	 * @return
	 */
	public static BlackiceConfig getInstance(){
		if(instance == null)
			instance = new BlackiceConfig();
		return instance;
	}
	
	/**
	 * Gets the parameters defined on the blackice config file
	 */
	public BlackiceParameters getParameters(){
		return this.parameters;
	}
	
	/**
	 * Sets the parameters on BlackiceConfig
	 * @param basePackage - The base package to be scanned
	 * @param annotationBased - Is the framework annotation based ?
	 */
	public void setParameters(String basePackage, boolean annotationBased){
		this.getParameters().setBasePackage(basePackage);
		this.getParameters().setAnnotationBased(annotationBased);
	}
	
	/**
	 * Gets all the controllers mapped by BlackIce
	 */
	public WebElementMap<Controller> getControllers(){
		return this.controllers;
	}
	
	/**
	 * Gets a configuration file from classpath
	 * @param type - the type of file to get
	 */
	public URL getConfigurationFile(FileType type){
		return this.configFiles.get(type);
	}
}
