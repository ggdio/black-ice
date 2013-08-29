package br.com.ggdio.blackice.config;

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
	
	private FileType(FileResolver resolver){
		this.resolver = resolver;
	}
	
	private final FileResolver resolver;
	public FileResolver getResolver(){
		return this.resolver;
	}
}
