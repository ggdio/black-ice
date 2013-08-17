package br.com.ggdio.blackice.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import br.com.sourcesphere.core.util.Assert;

/**
 * Class for general testing with CTRL+F11
 * @author Guilherme Dio
 *
 */
public class Main {
	static Assert assertion = new Assert();
	public static void main(String[] args) throws IOException {
		//testing the config on properties
		InputStream is = Main.class.getResourceAsStream("/blackice.properties");
		String config = "";
		try{
			assertion.notNull(is);
			Scanner scan = new Scanner(is);
			while(scan.hasNextLine()){
				config = config.concat(scan.nextLine());
			}
			scan.close();
		}
		catch(Exception e){
			System.out.println("blackice.properties not found on classpath. Using default config");
			config = "basePackage=null";
		}
		finally{
			if(is != null)
				is.close();
		}
		System.out.println(config);
	}
}
