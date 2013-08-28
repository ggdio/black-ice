package br.com.ggdio.blackice.test;

import br.com.ggdio.blackice.scan.ProjectScanner;
import br.com.sourcesphere.core.util.Assert;

/**
 * Class for general testing with CTRL+F11
 * @author Guilherme Dio
 *
 */
public class Main {
	static Assert assertion = new Assert();
	public static void main(String[] args) throws Exception {
		ProjectScanner scanner = ProjectScanner.getInstance();
		scanner.init();
		scanner.scan();
	}
}
