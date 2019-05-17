package com.rtproject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Properties;

 
public class PropertiesFile {
	InputStream inputStream;
	Properties prop = new Properties();

	public void createProperties() throws IOException{
		
        OutputStream os = new FileOutputStream("config.properties");

        String dir = System.getProperty("user.dir");

        prop.setProperty("path", dir);
        prop.setProperty("textFile", "url.txt");
        prop.setProperty("state", "KEADH");
        prop.setProperty("top", "3");

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

	public Properties setProperties() {
		Properties prop = new Properties();
        String filePath = "C:\\Users\\HP\\eclipse-workspace\\rtproject\\config.properties"; //change your path

        try (Writer inputStream = new FileWriter(filePath)) {
            //set the properties value
            prop.setProperty("path","C:\\Users\\HP\\eclipse-workspace\\project\\url.txt"); //change your path
            prop.setProperty("txtFile","url.txt");
            prop.setProperty("state", "KEDAH");
            //save properties to project root folder
            prop.store(inputStream,null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
	
}