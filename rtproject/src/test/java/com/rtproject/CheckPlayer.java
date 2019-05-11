package com.rtproject;

import java.io.*;

public class CheckPlayer{

    public static void main(String[]args) throws IOException{
        long startTime = System.currentTimeMillis();
        String path = System.getProperty("user.dir")+"\\rtproject\\resources\\chess.txt";
        System.out.println(path);

        //C:\Users\Asus\Desktop\RTProject\rtproject\resources\chess.txt
        File file = new File(path); 
  
        BufferedReader br = new BufferedReader(new FileReader(file));
        
            
        String url; 

        while ((url = br.readLine()) != null) {
            Web web = new Web(url);
            web.check();
        }
        br.close();


        long endTime = System.currentTimeMillis();
        long executeTime = endTime - startTime;
        System.out.println("\nExecution time in milliseconds: " + executeTime);
    }
}