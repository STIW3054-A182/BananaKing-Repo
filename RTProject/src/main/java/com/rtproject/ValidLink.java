package com.rtproject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.concurrent.Callable;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ValidLink implements Callable<String>{
    private String url;
    private String valid;

    public ValidLink(String url){
        this.url=url;
    }


    public String call(){
        long startTime, endTime, executeTime;
        String date = new Date().toString();
        //String logFile = "LogFile.log";
        //File file = new File(logFile);
        FileWriter fw;
        BufferedWriter bw;

        try {
            startTime = System.currentTimeMillis();
            if (exists(url)&& validTable(url)) {
                endTime = System.currentTimeMillis();
                executeTime = endTime - startTime;
                if (executeTime > 60000) {
                    System.exit(0);
                    fw = new FileWriter("LogFile.log", true);
                    bw = new BufferedWriter(fw);
                    bw.write(date + "\t");
                    bw.write(System.lineSeparator());
                    bw.write(url + " - OverTime");
                    bw.write(System.lineSeparator());
                    bw.close();
                }
                valid = url;
            }else {

            }
        } catch (Exception e) {
            e.printStackTrace();
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