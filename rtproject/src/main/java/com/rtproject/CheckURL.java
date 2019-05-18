package com.rtproject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.concurrent.Callable;

public class CheckURL implements Callable<String>{

    private String url;
    private String valid;

    public CheckURL(String url) {
        this.url = url;
    }

    public String call() {
        long startTime, endTime, executeTime;
        String date = new Date().toString();
        String logFile = "LogFile.log";
        File file = new File(logFile);
        FileWriter fw;
        BufferedWriter bw;

        try {
            startTime = System.currentTimeMillis();
            if (exists(url)) {
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
            } else {
                    endTime = System.currentTimeMillis();
                    executeTime = endTime - startTime;
                    fw = new FileWriter(file, true);
                    bw = new BufferedWriter(fw);
                    bw.write(date + "\t");
                    bw.write(System.lineSeparator());
                    bw.write(url + " - URL Not Exist");
                    bw.write(System.lineSeparator());
                    bw.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return valid;
    }

    private boolean exists(String url) {
        try {
            HttpURLConnection.setFollowRedirects(true);
            HttpURLConnection checkUrl = (HttpURLConnection) new URL(url).openConnection();
            checkUrl.getResponseCode();
            return (checkUrl.getResponseCode() == HttpURLConnection.HTTP_OK);
        } catch (Exception e) {
            return false;
        }
    }
}