package com.rtproject;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class KedahPlayer implements Runnable{
	String url;
	 
	 public KedahPlayer(String url) {
	        this.url = url;
	    }
	
	public void run() {		
          Document doc = null;
            try {
                doc = Jsoup.connect(url).get();

                //get category
                String title = doc.title();
                int scrape = title.indexOf("9");
                String category = title.substring(scrape + 1).replace("(","").replace(")","");

                for (Element row : doc.select("table.CRs1 tr")) {
                    final String rk=row.select("td:nth-of-type(1)").text();
                    final String sno=row.select("td:nth-of-type(2)").text();
                    final String name=row.select("td:nth-of-type(4)").text();
                    final String rtg=row.select("td:nth-of-type(6)").text();
                    final String state=row.select("td:nth-of-type(7)").text();
                    final String pointer=row.select("td:nth-of-type(8)").text();
                    if (state.contains("KEDAH")) {
                    	  String format ="| %-5s | %-5s | %-35s| %-8s| %-8s| %-8s| %-8s|\n";
	                      System.out.printf(format, rk, sno, name, rtg, state, pointer, category);	
                    }
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
		}
}
