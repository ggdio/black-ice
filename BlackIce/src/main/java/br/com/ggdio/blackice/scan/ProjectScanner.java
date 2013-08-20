package br.com.ggdio.blackice.scan;

import java.net.URL;
import java.util.Properties;

import br.com.ggdio.blackice.config.FrameworkConfiguration;
import br.com.ggdio.blackice.scan.exception.ConfigurationFileNotFoundException;
import br.com.ggdio.blackice.scan.exception.ProjectNotScannedException;
import br.com.ggdio.blackice.scan.exception.ScannerException;

import com.thoughtworks.xstream.XStream;

public class ProjectScanner {
	
	private static final String propertiesFile = "/blackice.properties";
	private static final String xmlFile = "/blackice.xml";
	private static Boolean initiated = false;
	private static FrameworkConfiguration config;
	
	private ProjectScanner(){
		
	}
	
	public static void init() throws ScannerException{
		URL configFile = null;
		try{
			//The properties file is the default
			configFile = ProjectScanner.class.getResource(propertiesFile);
			if(configFile != null){
				Properties props = new Properties();
				props.load(configFile.openStream());
				ProjectScanner.config = new FrameworkConfiguration(props.getProperty("basePackage"));
				ProjectScanner.initiated = true;
				return;
			}
			
			//The XML is optional, if the properties does not exist it will be used. Otherwise, it wont be read.
			configFile = ProjectScanner.class.getResource(xmlFile);
			if(configFile != null){
				XStream xs = new XStream();
				ProjectScanner.config = (FrameworkConfiguration) xs.fromXML(configFile.openStream());
				ProjectScanner.initiated = true;
				return;
			}
			//File not found, exception must be thrown to warn the developer
			throw new ConfigurationFileNotFoundException("The configuration file(blackice.properties or blackice.xml) has not been defined on the classpath");
		}
		catch(Exception e){
			throw new ScannerException("An error occured while initiating the project.",e);
		}
		finally{
			configFile = null;
		}
	}
	
	public static void scan(){
		if(!ProjectScanner.initiated)
			throw new ProjectNotScannedException("The project has not been initiated yet. Call init() method before scanning");
		Package basePackage = Package.getPackage(ProjectScanner.config.getBasePackage());
		
	}
}
