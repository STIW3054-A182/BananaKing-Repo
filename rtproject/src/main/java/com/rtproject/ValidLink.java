package com.rtproject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ValidLink implements Callable<String>{
    private List<String> exist = new ArrayList<String>();
    private List<String> table = new ArrayList<String>();
    private String url;
    private String valid;
    Logger logger = Logger.getLogger("InvalidLink");

    public ValidLink(String url){
        this.url=url;
    }


    public String call(){
        if(exists(url) && validTable(url)){
                valid = url;
        }
        else if(exists(url) && !validTable(url)){
            try{
            FileHandler fileHandler = new FileHandler("LogFile.Log", true);
			logger.addHandler(fileHandler);
			SimpleFormatter formatter = new SimpleFormatter();
			fileHandler.setFormatter(formatter);
			logger.setUseParentHandlers(false);
            logger.info(url + " - URL without table");
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return valid;
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
    
    public boolean validTable(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            if (doc.select("table.CRs1 tr").isEmpty())
                return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}