package com.rtproject;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class DisplayTop {
    private String url;
    private String cat;
    private String title;
    private int top;
    public PlayerList player = new PlayerList();

    public DisplayTop(String url, int top) {
        this.url = url;
        this.top = top;
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
                if(cat==null){
                    cat="Junior";
                }
            }
        
            for (Element e : doc.select("div.defaultDialog:nth-of-type(3) > h2")) {
                title=e.text();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void display(){
        System.out.println(title + " (" + cat + ")");
        System.out.println("---------------------------------------------------------------------------------------------------");
        System.out.printf("| %-3s | %-3s | %-30s | %-5s | %-5s | %-15s | %-5s | %-8s |\n", player.getRank().get(0), player.getSno().get(0), player.getName().get(0), player.getFed().get(0), player.getRtg().get(0), player.getClub_city().get(0),player.getPts().get(0), "Category");
        System.out.println("---------------------------------------------------------------------------------------------------");
                
        for (int i=1; i<=top; i++){
            System.out.printf("| %-3s | %-3s | %-30s | %-5s | %-5s | %-15s | %-5s | %-8s |\n", player.getRank().get(i), player.getSno().get(i), player.getName().get(i), player.getFed().get(i), player.getRtg().get(i), player.getClub_city().get(i),player.getPts().get(i), cat);
        }

        System.out.println("---------------------------------------------------------------------------------------------------");
        System.out.println("");
    }

    public void saveTable(){
        try{
        Document doc = Jsoup.connect(url).get();

                for(Element row : doc.select("table.CRs1 tr")){
                    if(row.select("td.CRc:nth-of-type(2)").text().equals("")){
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
                        player.setRank(rank);
                        player.setSno(sno);
                        player.setName(name);
                        player.setFed(fed);
                        player.setRtg(rtg);
                        player.setClub_city(club_city);
                        player.setPts(pts);
                        player.setTb1(tb1);
                        player.setTb2(tb2);
                        player.setTb3(tb3);
                    }         
                }               
            }catch(Exception e){
                e.printStackTrace();
            }
    }
}