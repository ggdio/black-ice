package br.com.ggdio.blackice.config;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Used for storing the framework deploy configuration(blackice.properties/blackice.xml)
 * @author Guilherme Dio
 */
@XStreamAlias("framework-config")
public class FrameworkConfiguration {
	
	@XStreamAlias("basePackage")
	public String basePackage;
	
	public FrameworkConfiguration() {
		
	}
	
	public FrameworkConfiguration(String basePackage) {
		super();
		this.basePackage = basePackage;
	}

	public String getBasePackage() {
		return basePackage;
	}

	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}
}
