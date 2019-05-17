package com.rtproject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

 
public class PropertiesFile {
	InputStream inputStream;
    Properties prop = new Properties();
    String result = "";

	public void createProperties() throws IOException{
		
        OutputStream os = new FileOutputStream("config.properties");

        String dir = System.getProperty("user.dir");

        prop.setProperty("path", dir);
        prop.setProperty("textFile", "url.txt");
        prop.setProperty("state", "KEDAH");
        prop.setProperty("top", "3");
        prop.setProperty("player", "Rosli Iman Hasif");

        prop.store(os, null);
    }

    public void readProperties() throws IOException{

        InputStream input = new FileInputStream("config.properties");
        prop.load(input);
    }

    public String getTextFile() throws IOException{
        String dir = prop.getProperty("path");
        String file = prop.getProperty("textFile");

        return dir+File.separator+file;
    }

    /*public String getPropValues() throws IOException {
 
		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";
 
			//inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			inputStream = new FileInputStream(System.getProperty("user.dir")+"\\resources\\config.properties");
 
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
	}*/
}