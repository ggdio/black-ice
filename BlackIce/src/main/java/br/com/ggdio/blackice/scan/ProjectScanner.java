package br.com.ggdio.blackice.scan;

import static org.reflections.ReflectionUtils.withAnnotation;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.reflections.ReflectionUtils;
import org.reflections.Reflections;

import br.com.ggdio.blackice.config.BlackiceConfig;
import br.com.ggdio.blackice.config.BlackiceParameters;
import br.com.ggdio.blackice.scan.exception.ConfigurationFileNotFoundException;
import br.com.ggdio.blackice.scan.exception.ProjectNotScannedException;
import br.com.ggdio.blackice.scan.exception.ScannerException;
import br.com.ggdio.blackice.scan.exception.WebElementConflict;
import br.com.ggdio.blackice.structure.Action;
import br.com.ggdio.blackice.structure.Controller;
import br.com.ggdio.blackice.structure.WebElementMap;

import com.thoughtworks.xstream.XStream;

public final class ProjectScanner {
	
	private static ProjectScanner instance;
	
	private final String propertiesFile = "/blackice.properties";
	private final String xmlFile = "/blackice.xml";
	private Boolean initiated = false;
	
	private final Class<br.com.ggdio.blackice.annotation.Controller> controllerAnnotationClass = br.com.ggdio.blackice.annotation.Controller.class;
	private final Class<br.com.ggdio.blackice.annotation.Action> actionAnnotationClass = br.com.ggdio.blackice.annotation.Action.class;
	
	private ProjectScanner(){
		
	}
	
	public static ProjectScanner getInstance(){
		if(instance == null)
			instance = new ProjectScanner();
		return instance;
	}
	
	public void init() throws ScannerException{
		URL configFile = null;
		try{
			//The properties file is the default
			configFile = ProjectScanner.class.getResource(propertiesFile);
			if(configFile != null){
				Properties props = new Properties();
				props.load(configFile.openStream());
				BlackiceConfig.parameters.setBasePackage(props.getProperty("basePackage"));
				this.initiated = true;
				return;
			}
			
			//The XML is optional, if the properties does not exist it will be used. Otherwise, it wont be read.
			configFile = ProjectScanner.class.getResource(xmlFile);
			if(configFile != null){
				XStream xs = new XStream();
				String basePackage = ((BlackiceParameters) xs.fromXML(configFile.openStream())).getBasePackage();
				BlackiceConfig.parameters.setBasePackage(basePackage);
				this.initiated = true;
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
	
	public void scan() throws WebElementConflict{
		if(!this.initiated)
			throw new ProjectNotScannedException("The project has not been initiated yet. Call init() method before scanning");
		List<Class<?>> classes = listAllControllerClasses();
		WebElementMap<Controller> controllers = BlackiceConfig.controllers;
		for(Class<?> clazz : classes){
			String ctrlKey = clazz.getAnnotation(controllerAnnotationClass).value();
			Controller controller = new Controller(clazz);
			for(Method method : listAllActionMethods(clazz)){
				String actionKey = method.getAnnotation(actionAnnotationClass).value();
				Action action = new Action(method);
				controller.putAction(actionKey, action);
			}
			controllers.put(ctrlKey, controller);
		}
	}
	
	private List<Class<?>> listAllControllerClasses()
	{
		Reflections reflections = new Reflections(BlackiceConfig.parameters.getBasePackage());
		Set<Class<?>> classes = reflections.getTypesAnnotatedWith(this.controllerAnnotationClass);
		return new ArrayList<Class<?>>(classes);
	}
	
	@SuppressWarnings("unchecked")
	private List<Method> listAllActionMethods(Class<?> clazz)
	{
		Set<Method> methods = ReflectionUtils.getAllMethods(clazz, withAnnotation(this.actionAnnotationClass));
		return new ArrayList<Method>(methods);
	}
	
}
