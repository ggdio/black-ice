package br.com.ggdio.blackice.structure;

import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Method;

/**
 * Defines a WebElement, such as a {@link Controller} or an {@link Action}
 * @author Guilherme Dio
 */
public abstract class WebElement {
	private final GenericDeclaration piece;
	
	public WebElement(GenericDeclaration piece) {
		super();
		this.piece = piece;
	}
	
	/**
	 * Gets a piece of this element
	 * @return {@link Class} for {@link Controller} and {@link Method} for {@link Action}
	 */
	public GenericDeclaration getPiece() {
		return piece;
	}
}
