package com.rtproject;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Countp {
    private String url;
    private String cat;
    private String title;
    public PlayerList player = new PlayerList();
    private int count;

    public Countp(String url) {
        this.url = url;

    }

    public void getTitle() {
        try {
            Document doc = Jsoup.connect(url).get();

            for (Element e : doc.select("div.defaultDialog:nth-of-type(2) > h2")) {
                Pattern regex = Pattern.compile("([A-Z][0-9]{1,2}([A-Z])?)");
                Matcher regexMatcher = regex.matcher(e.text());
                while(regexMatcher.find()) {
                    cat = regexMatcher.group(1);
                }

            
            }
            
            if(cat==null){
                cat="Junior";
            }
        
            for (Element e : doc.select("div.defaultDialog:nth-of-type(3) > h2")) {
                title=e.text();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void display(){      
        count = player.getName().size()-1;
        System.out.printf("|%-8s |%-8d |\n", cat, count); 
    }

    public int getCount(){
        return count;
    }

    public void saveTable(){
        try{
            Document doc = Jsoup.connect(url).get();

            for(Element row : doc.select("table.CRs1 tr")){
                if(row.select("td.CRc:nth-of-type(2)").text().equals("")){
                    continue;
                }
                
                else{         
                    final String name = row.select("td.CR:nth-of-type(4)").text();
                    player.setName(name);
                                                
                }         
            }               
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}