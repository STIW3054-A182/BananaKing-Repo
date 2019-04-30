package com.rtproject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

 
public class GetPropertiesValue {
	String result = "";
	InputStream inputStream;
 
	public String getPropValues() throws IOException {
 
		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";
 
			//inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			inputStream = new FileInputStream(System.getProperty("user.dir")+"\\rtproject\\resources\\config.properties");
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
 
			// get the property value and print it out
			String path = prop.getProperty("path");
			result=path;

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return result;
	}
}