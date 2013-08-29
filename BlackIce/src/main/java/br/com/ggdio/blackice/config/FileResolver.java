package br.com.ggdio.blackice.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileResolver {
	public BlackiceParameters resolveParameters(File file) throws FileNotFoundException, IOException;
}
