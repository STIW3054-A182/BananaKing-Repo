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
}