package com.rtproject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class CheckURL implements Callable<String>{

	String link;
	String web;
	Logger logger = Logger.getLogger("InvalidLink");

    public CheckURL(String web){
        this.web=web;
    }

    public String call() throws Exception{
		if (exists(web)) {
			link=web;
		}

		else {
			try {
				FileHandler fileHandler = new FileHandler("LogFile.Log", true);
				logger.addHandler(fileHandler);
				SimpleFormatter formatter = new SimpleFormatter();
				fileHandler.setFormatter(formatter);
				logger.setUseParentHandlers(false);
				logger.info(web + " - Invalid URL");
				

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return link;
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