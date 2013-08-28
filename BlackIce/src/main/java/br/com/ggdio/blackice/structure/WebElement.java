package br.com.ggdio.blackice.structure;

/**
 * 
 * This interface defines a WebElement type
 * @author Guilherme Dio
 * @since 27/08/2013
 * @param <GenericDeclaration>
 */
public interface WebElement<GenericDeclaration> {
	public String getElementName();
	public GenericDeclaration getElementDefinition();
}
