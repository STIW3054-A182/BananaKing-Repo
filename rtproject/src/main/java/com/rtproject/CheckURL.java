package com.rtproject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class CheckURL implements Runnable{

	private String web;
	Logger log = Logger.getLogger("InvalidUrls");

    public CheckURL(String web){
        this.web=web;
    }

    public void run(){
		if(exists(web)){
			System.out.println(Thread.currentThread().getName()+ " - " +web);
		}

		else{
			try{
				FileHandler fileHandler = new FileHandler("LogFile.log");
				log.addHandler(fileHandler);
				SimpleFormatter formatter = new SimpleFormatter();
				fileHandler.setFormatter(formatter);
				log.info(web + " - Not Exist");
					
			}catch (SecurityException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean exists(String URLName) {

		try {
			HttpURLConnection.setFollowRedirects(false);
			HttpURLConnection con = (HttpURLConnection) new URL(URLName).openConnection();
			con.setConnectTimeout(1000);
			con.setReadTimeout(1000);
			con.setRequestMethod("HEAD");
			return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
		} catch (Exception e) {
			return false;
		}
	}
}