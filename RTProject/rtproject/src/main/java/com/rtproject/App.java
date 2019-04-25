package com.rtproject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class App{

    public static void main(String[]args){
        final String url = "http://chess-results.com/tnr424280.aspx?lan=1&art=1&rd=8";

        try{
            Document doc = Jsoup.connect(url).get();

            for(Element row : doc.select("table.CRs1 tr")){
                if(row.select("td.CRc:nth-of-type(1)").text().equals("")){
                    continue;
                }
                else{
                    final String rank = row.select("td.CRc:nth-of-type(1)").text();
                    final String sno = row.select("td.CRc:nth-of-type(2)").text();
                    final String name = row.select("td.CR:nth-of-type(4)").text();
                    final String fed = row.select("td.CR:nth-of-type(5)").text();
                    final String rtg = row.select(".CRr").text();
                    final String club_city = row.select("td.CR:nth-of-type(7)").text();
                    final String pts = row.select("td.CRc:nth-of-type(8)").text();
                    final String tb1 = row.select("td.CRc:nth-of-type(9)").text();
                    final String tb2 = row.select("td.CRc:nth-of-type(10)").text();
                    final String tb3 = row.select("td.CRc:nth-of-type(11)").text();
                    System.out.println(rank+" | "+sno+" | "+name+" | "+fed+" | "+rtg+" | "+club_city+" | "+pts+" | "+tb1+" | "+tb2+" | "+tb3);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
