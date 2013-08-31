package br.com.ggdio.blackice.config;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesFileResolver implements FileParameterResolver {

	@Override
	public BlackiceParameters resolveParameters(File file) {
		try {
			Properties props = new Properties();
			props.load(new FileInputStream(file));
			return new SystemPropertiesResolver().resolveParameters(props);
		} 
		catch (Exception e) {
			return null;
		}
	}

}
