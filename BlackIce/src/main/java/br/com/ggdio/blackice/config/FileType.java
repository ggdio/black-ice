package br.com.ggdio.blackice.config;

import java.io.File;

/**
 * 
 * This enum defines a FileType type
 * @author Guilherme Dio
 * @since 28/08/2013
 */
public enum FileType {
	PROPERTIES(new PropertiesFileResolver()),
	XML(new XMLFileResolver());
	//JSON(new JSonFileResolver()); - not yet implemented,
	//CSV(new CSVFileResolver()); - not yet implemented
	
	private FileType(ParameterResolver resolver){
		this.resolver = resolver;
	}
	
	private final ParameterResolver resolver;
	public ParameterResolver getResolver(){
		return this.resolver;
	}
	
	/**
	 * Gets the equivalent enum of the file
	 * @param File - The file to be resolved
	 * @return A FileType - If the extension of the file matches the pattern
	 * 		   <p>Null - If the File does not match the pattern
	 */
	public static FileType valueOf(File file){
		String fileName = file.getName().toUpperCase();
		return FileType.valueOf(fileName.substring(fileName.lastIndexOf(".")+1));
	}
}
