package br.com.ggdio.blackice.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;

public class XMLFileResolver implements FileResolver {

	@Override
	public BlackiceParameters resolveParameters(File file) throws FileNotFoundException, IOException {
		return (BlackiceParameters) new XStream().fromXML(new FileInputStream(file));
	}

}
