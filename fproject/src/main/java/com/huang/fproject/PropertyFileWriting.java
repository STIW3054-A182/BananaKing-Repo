package com.huang.fproject;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Properties;

public class PropertyFileWriting {
    public Properties setProperties() {
        Properties prop = new Properties();
        String filePath = "E:\\eclipse\\workplace\\rtproject\\resources\\config.properties"; //change your path

        try (Writer inputStream = new FileWriter(filePath)) {
            //set the properties value
            prop.setProperty("path","E:\\eclipse\\workplace\\rtproject\\resources\\chess.txt"); //change your path
            prop.setProperty("txtFile","URL.txt");
            prop.setProperty("state", "KEDAH");
            //save properties to project root folder
            prop.store(inputStream,null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

	
}