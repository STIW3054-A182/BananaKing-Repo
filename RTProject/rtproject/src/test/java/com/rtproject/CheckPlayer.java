package com.rtproject;

import java.io.*;

public class CheckPlayer{

    public static void main(String[]args) throws IOException{
        long startTime = System.currentTimeMillis();

        File file = new File("D:\\Documents\\UUM\\Sem 4\\Real-time Programming\\STIW3054\\RTProject\\rtproject\\src\\main\\resource\\chess.txt"); 
  
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