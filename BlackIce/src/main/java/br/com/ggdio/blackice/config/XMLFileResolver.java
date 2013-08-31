package br.com.ggdio.blackice.config;

import java.io.File;
import java.io.FileInputStream;

import com.thoughtworks.xstream.XStream;

public class XMLFileResolver implements FileParameterResolver {

	@Override
	public BlackiceParameters resolveParameters(File file) {
		try{
			return (BlackiceParameters) new XStream().fromXML(new FileInputStream(file));
		}
		catch(Exception e){
			return null;
		}
	}

}
