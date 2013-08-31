package br.com.ggdio.blackice.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import br.com.ggdio.blackice.config.exception.ConfigurationException;
import br.com.ggdio.blackice.config.exception.ConfigurationFileNotFoundException;
import br.com.ggdio.blackice.structure.Controller;
import br.com.ggdio.blackice.structure.WebElementMap;

/**
 * 
 * This class contains all the configuration of BlackIce framework
 * @author Guilherme Dio
 * @since 27/08/2013
 */
public final class BlackiceConfig {
	/**
	 * Singleton instance
	 */
	private static BlackiceConfig instance;
	
	/**
	 * Configuration fields
	 */
	private BlackiceParameters parameters = new BlackiceParameters();
	private WebElementMap<Controller> controllers = new WebElementMap<Controller>();
	private final String[] configDirs = {"", "WEB-INF"};
	
	private BlackiceConfig() {
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
	
	public void loadParameters() throws FileNotFoundException{
		final ClassLoader clsLoader = this.getClass().getClassLoader();
		try{
			for(File file : getConfigDirectory(clsLoader)){
				FileType type = FileType.valueOf(file);
				if(type != null){
					this.parameters = type.getResolver().resolveParameters(file);
					break;
				}
			}
		}
		catch(URISyntaxException e){
			throw new ConfigurationException("Error while searching the classpath for the blackice configuration file", e);
		}
		catch(IOException e){
			throw new ConfigurationException("Error while reading the blackice configuration file", e);
		}
	}
	
	private File[] getConfigDirectory(ClassLoader clsLoader) throws ConfigurationFileNotFoundException, URISyntaxException{
		for(String dir : configDirs){
			File[] dirFiles = listBlackiceFiles(clsLoader.getResource(dir));
			if(dirFiles.length > 0)
				return dirFiles;
		}
		throw new ConfigurationFileNotFoundException("The configuration file has not been defined on the classpath");
	}
	
	private File[] listBlackiceFiles(URL location) throws URISyntaxException{
		if(location == null) 
			return null;
		return new File(location.toURI()).listFiles(new BlackiceConfigFileFilter());
	}
	
	/**
	 * Gets the parameters defined on the blackice config file
	 */
	public BlackiceParameters getParameters(){
		return this.parameters;
	}
	
	/**
	 * Gets all the controllers mapped by BlackIce
	 */
	public WebElementMap<Controller> getControllers(){
		return this.controllers;
	}
}
