package com.rtproject;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException{
        String cat="";
        String title="";
        //String url = "http://chess-results.com/tnr424287.aspx?lan=1&art=1";
        String url = "http://chess-results.com/tnr424282.aspx?lan=1&art=1&rd=8";
        
        
        try {
            Document doc = Jsoup.connect(url).get();

            for (Element e : doc.select("div.defaultDialog:nth-of-type(2) > h2")) {
                //Pattern regex = Pattern.compile("\\((.*?)\\)");
                Pattern regex = Pattern.compile("([A-Z][0-9]{1,2}([A-Z])?)");
                Matcher regexMatcher = regex.matcher(e.text());
                while(regexMatcher.find()) {
                    cat = regexMatcher.group(1);
                
                }
            }
        
            for (Element e : doc.select("div.defaultDialog:nth-of-type(3) > h2")) {
                title=e.text();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(title + " " + cat);

    }
}

