package com.rtproject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class KedahPlayer{
    private String url;
    private String state;
    private String cat;
    private String title;
	 
	 public KedahPlayer(String url, String state) {
            this.url = url;
            this.state=state;
	    }
	
	public void run() {		
          Document doc = null;
            try {
                doc = Jsoup.connect(url).get();

                for (Element e : doc.select("div.defaultDialog:nth-of-type(2) > h2")) {
                    Pattern regex = Pattern.compile("([A-Z][0-9]{1,2}([A-Z])?)");
                    Matcher regexMatcher = regex.matcher(e.text());
                    while(regexMatcher.find()) {
                        cat = regexMatcher.group(1);
                    }
                    if(cat==null){
                        cat="Junior";
                    }
                }
            
                for (Element e : doc.select("div.defaultDialog:nth-of-type(3) > h2")) {
                    title=e.text();
                }

                for (Element row : doc.select("table.CRs1 tr")) {
                    final String rk=row.select("td:nth-of-type(1)").text();
                    final String sno=row.select("td:nth-of-type(2)").text();
                    final String name=row.select("td:nth-of-type(4)").text();
                    final String rtg=row.select("td:nth-of-type(6)").text();
                    final String state2=row.select("td:nth-of-type(7)").text();
                    final String pointer=row.select("td:nth-of-type(8)").text();

                    if (state2.contains(state)) {
                    	String format ="| %-5s | %-5s | %-35s| %-8s| %-8s| %-8s| %-8s|\n";
	                    System.out.printf(format, rk, sno, name, rtg, state2, pointer, cat);	
                    }
                }

            }catch (Exception e) {
                e.printStackTrace();
            }
		}
}