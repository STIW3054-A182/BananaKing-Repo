package com.rtproject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class LogFile{
    Logger log = Logger.getLogger("InvalidUrls");
    String web;

    public LogFile(String web){
        this.web=web;
    }

    public void createLog() throws SecurityException, IOException {
        FileHandler fileHandler = new FileHandler("LogFile.Log", true);
	    log.addHandler(fileHandler);
	    SimpleFormatter formatter = new SimpleFormatter();
	    fileHandler.setFormatter(formatter);
        log.info(web + " - Not Exist");
    }

}