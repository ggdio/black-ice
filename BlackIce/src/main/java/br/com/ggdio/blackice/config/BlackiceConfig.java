package br.com.ggdio.blackice.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
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
	
	private BlackiceConfig() {
	}
	
	public void loadParameters() throws FileNotFoundException{
		ClassLoader clsLoader = this.getClass().getClassLoader();
		try{
			File[] dirFiles = listBlackiceFilesK(clsLoader.getResource("WEB-INF"));
			if(dirFiles == null || dirFiles.length == 0)
				dirFiles = listBlackiceFilesK(clsLoader.getResource(""));
			if(dirFiles == null || dirFiles.length == 0)
				throw new ConfigurationFileNotFoundException("The configuration file has not been defined on the classpath");
			for(File file : dirFiles){
				if(file.isFile()){
					FileType type = getFileType(file);
					if(type != null){
						this.parameters = type.getResolver().resolveParameters(file);
						break;
					}
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
	
	private File[] listBlackiceFilesK(URL location) throws URISyntaxException{
		if(location == null) 
			return null;
		File dir = new File(location.toURI());
		return dir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.toLowerCase().startsWith("blackice.");
			}
		});
	}
	
	private FileType getFileType(File file){
		String fileName = file.getName();
		String extension = fileName.substring(fileName.indexOf(".")+1).toLowerCase();
		if(extension.equals("properties"))
			return FileType.PROPERTIES;
		else if(extension.equals("xml"))
			return FileType.XML;
		return null;
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
	 * Gets all the controllers mapped by BlackIce
	 */
	public WebElementMap<Controller> getControllers(){
		return this.controllers;
	}
}
