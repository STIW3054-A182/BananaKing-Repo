package com.huang.fproject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.concurrent.Callable;

public class CheckUrls implements Callable<String> {

    private String line, link;

    public CheckUrls(String line, String link) {
        this.line = line;
        this.link = link;
    }

    private boolean checkURL(String url) {

        try {
            HttpURLConnection.setFollowRedirects(false);
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setConnectTimeout(60000);
            connection.setReadTimeout(60000);
            connection.setRequestMethod("HEAD");
            return (connection.getResponseCode() == HttpURLConnection.HTTP_OK);
        } catch (Exception e) {
            return false;

        }
    }

    @Override
    public String call() throws Exception {
        if (checkURL(link)) {
            //ystem.out.println(Thread.currentThread().getName() + "-" + url + ": Exist");

        } else{
            try {
                LogFile logFile = new LogFile();
                logFile.createLogFile(line, link);
                link = "0";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return link;
    }
}