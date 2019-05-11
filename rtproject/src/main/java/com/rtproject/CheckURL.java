package com.rtproject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class CheckURL implements Runnable{

	String web;
	Logger log = Logger.getLogger("InvalidUrls");

    public CheckURL(String web){
        this.web=web;
    }

    public void run() {
		if (exists(web)) {
			System.out.println(Thread.currentThread().getName() + " - " + web);
		}

		else {
			try {
				// FileHandler fileHandler = new FileHandler("LogFile.Log", true);
				// log.addHandler(fileHandler);
				// SimpleFormatter formatter = new SimpleFormatter();
				// fileHandler.setFormatter(formatter);
				// log.info(web + " - Not Exist");
				LogFile log = new LogFile(web);
				log.createLog();
			} catch (Exception e) {
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