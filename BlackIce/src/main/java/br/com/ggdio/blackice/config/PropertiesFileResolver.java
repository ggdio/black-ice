package br.com.ggdio.blackice.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileResolver implements FileResolver {

	@Override
	public BlackiceParameters resolveParameters(File file) throws FileNotFoundException, IOException {
		Properties props = new Properties();
		props.load(new FileInputStream(file));
		BlackiceParameters parameters = new BlackiceParameters();
		parameters.setBasePackage(props.getProperty("basePackage"));
		parameters.setAnnotationBased(Boolean.parseBoolean(props.getProperty("annotationBased")));
		return parameters;
	}

}
