package br.com.ggdio.blackice.config;

import java.util.Properties;

public class SystemPropertiesResolver implements ParameterResolver<Properties> {

	@Override
	public BlackiceParameters resolveParameters(Properties props) {
		try {
			BlackiceParameters parameters = new BlackiceParameters();
			parameters.setBasePackage(props.getProperty("basePackage"));
			parameters.setAnnotationBased(Boolean.parseBoolean(props.getProperty("annotationBased")));
			return parameters;
		} 
		catch (Exception e) {
			return null;
		}
	}

}
