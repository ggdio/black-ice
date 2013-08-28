package br.com.ggdio.blackice.scan;

import static org.reflections.ReflectionUtils.withAnnotation;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.reflections.ReflectionUtils;
import org.reflections.Reflections;

import br.com.ggdio.blackice.config.BlackiceConfig;
import br.com.ggdio.blackice.config.BlackiceConfig.FileType;
import br.com.ggdio.blackice.config.BlackiceParameters;
import br.com.ggdio.blackice.scan.exception.ConfigurationFileNotFoundException;
import br.com.ggdio.blackice.scan.exception.ScanNotYetInitializedException;
import br.com.ggdio.blackice.scan.exception.ScannerException;
import br.com.ggdio.blackice.structure.Action;
import br.com.ggdio.blackice.structure.Controller;
import br.com.ggdio.blackice.structure.WebElementMap;
import br.com.ggdio.blackice.structure.exception.WebElementConflictException;

import com.thoughtworks.xstream.XStream;

/**
 * 
 * This class defines a ProjectScanner to search the project for elements
 * @author Guilherme Dio
 * @since 27/08/2013
 */
public final class ProjectScanner {
	
	/**
	 * The singleton instance of this class
	 */
	private static ProjectScanner instance;
	
	/**
	 * Flag to notify if the process has been initiated before scanning
	 */
	private Boolean initiated = false;
	
	/**
	 * The annotations classes references
	 */
	private final Class<br.com.ggdio.blackice.annotation.Controller> controllerAnnotationClass = br.com.ggdio.blackice.annotation.Controller.class;
	private final Class<br.com.ggdio.blackice.annotation.Action> actionAnnotationClass = br.com.ggdio.blackice.annotation.Action.class;
	
	/**
	 * The framework configuration
	 */
	private final BlackiceConfig config = BlackiceConfig.getInstance();
	
	/**
	 * Private constructor(singleton)
	 */
	private ProjectScanner(){
	}
	
	/**
	 * Gets an instance of the class
	 */
	public static ProjectScanner getInstance(){
		if(instance == null)
			instance = new ProjectScanner();
		return instance;
	}
	
	/**
	 * Initiate the process before scanning
	 * @throws ScannerException
	 */
	public void init() throws ScannerException{
		URL configFile = null;
		try{
			//The properties file is the default
			configFile = this.config.getConfigurationFile(FileType.PROPERTIES);
			if(configFile != null){
				Properties props = new Properties();
				props.load(configFile.openStream());
				this.config.setParameters(props.getProperty("basePackage"),Boolean.parseBoolean(props.getProperty("annotationBased")));
				this.initiated = true;
				return;
			}
			
			//The XML is optional, if the properties does not exist it will be used. Otherwise, it wont be read.
			configFile = this.config.getConfigurationFile(FileType.XML);
			if(configFile != null){
				BlackiceParameters params = (BlackiceParameters) new XStream().fromXML(configFile.openStream());
				this.config.setParameters(params.getBasePackage(),params.isAnnotationBased());
				this.initiated = true;
				return;
			}
			//File not found, exception must be thrown to warn the developer
			throw new ConfigurationFileNotFoundException("The configuration file has not been defined on the classpath");
		}
		catch(Exception e){
			throw new ScannerException("An error occured while initiating the project.",e);
		}
		finally{
			configFile = null;
		}
	}
	
	/**
	 * Execute the project scan process
	 * WARNING: The init() method must be called before scanning
	 * @throws WebElementConflictException if a WebElement(Controller or Action) have been defined more than once
	 * @throws ScanNotYetInitializedException if the scan has not been initiated
	 */
	public void scan() throws WebElementConflictException{
		if(!this.initiated) throw new ScanNotYetInitializedException("The project has not been initiated yet. Call init() method before scanning");
		resolveControllers(getListOfControllers(), this.config.getControllers());
	}
	
	/**
	 * Resolves the Controllers on the project
	 * @param classes - The classes to be scanned
	 * @param controllers - A controller map to populate with the resolved ones
	 * @throws WebElementConflictException - If a Controller has been specified more than once
	 */
	private void resolveControllers(List<Class<?>> classes,WebElementMap<Controller> controllers) throws WebElementConflictException{
		for(Class<?> clazz : classes){
			Controller controller = new Controller(clazz);
			resolveActions(controller);
			controllers.put(controller);
		}
	}
	
	/**
	 * Resolves the Actions on the project
	 * @param controller
	 * @throws WebElementConflictException
	 */
	private void resolveActions(Controller controller) throws WebElementConflictException{
		for(Method method : getListOfActions(controller.getElementDefinition()))
			controller.put(new Action(method));
	}
	
	/**
	 * Determines if will get 'all annotated classes' or 'all public classes' on the project
	 */
	private List<Class<?>> getListOfControllers(){
		if(this.config.getParameters().isAnnotationBased())
			return listAllControllerClassesWithAnnotation();
		return listAllControllerClasses();
	}
	
	/**
	 * Lists the controllers on the project
	 */
	private List<Class<?>> listAllControllerClasses()
	{
		Reflections reflections = new Reflections(this.config.getParameters().getBasePackage());
		Set<Class<?>> classes = reflections.getSubTypesOf(Object.class);
		return new ArrayList<Class<?>>(classes);
	}
	
	/**
	 * Lists the controllers on the project
	 */
	private List<Class<?>> listAllControllerClassesWithAnnotation()
	{
		Reflections reflections = new Reflections(this.config.getParameters().getBasePackage());
		Set<Class<?>> classes = reflections.getTypesAnnotatedWith(this.controllerAnnotationClass);
		return new ArrayList<Class<?>>(classes);
	}
	
	/**
	 * Determines if will get 'all annotated methods' or 'all public methods' of a class
	 */
	private List<Method> getListOfActions(Class<?> clazz){
		if(this.config.getParameters().isAnnotationBased())
			return listAllActionMethodsWithAnnotation(clazz);
		return listAllActionMethods(clazz);
	}
	
	/**
	 * Lists the actions(public methods) on the project
	 */
	@SuppressWarnings("unchecked")
	private List<Method> listAllActionMethods(Class<?> clazz)
	{
		Set<Method> methods = ReflectionUtils.getAllMethods(clazz, ReflectionUtils.withModifier(Modifier.PUBLIC));
		return new ArrayList<Method>(methods);
	}
	
	/**
	 * Lists the actions(methods annotated with @Action) on the project
	 */
	@SuppressWarnings("unchecked")
	private List<Method> listAllActionMethodsWithAnnotation(Class<?> clazz)
	{
		Set<Method> methods = ReflectionUtils.getAllMethods(clazz, withAnnotation(this.actionAnnotationClass));
		return new ArrayList<Method>(methods);
	}
	
}
