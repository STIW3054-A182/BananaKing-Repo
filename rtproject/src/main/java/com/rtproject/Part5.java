package com.rtproject;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Part5{

    public static void main(String[]args) throws Exception{
        final String url = "http://chess-results.com/tnr424275.aspx?lan=1&art=1&rd=7";
        String cat="";
        String title="";
        PlayerList player = new PlayerList();
        Properties pro = new Properties();
        PropertiesFile prop = new PropertiesFile();
        prop.createProperties();
        InputStream input = new FileInputStream("config.properties");
        pro.load(input);
        final int top = Integer.parseInt(pro.getProperty("top"));

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

            for (Element e : doc.select("div.defaultDialog:nth-of-type(2) > h2")) {
                Pattern regex = Pattern.compile("\\((.*?)\\)");
                Matcher regexMatcher = regex.matcher(e.text());
                while(regexMatcher.find()) {
                    cat = regexMatcher.group(1);
                }
            }
            
            for (Element e : doc.select("div.defaultDialog:nth-of-type(3) > h2")) {
                title=e.text();
            }

            System.out.println(title + " (" + cat + ")");
            System.out.println("--------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-3s | %-3s | %-30s | %-5s | %-5s | %-15s | %-5s | %-7s | %-7s | %-7s |\n", player.getRank().get(0), player.getSno().get(0), player.getName().get(0), player.getFed().get(0), player.getRtg().get(0), player.getClub_city().get(0),player.getPts().get(0),player.getTb1().get(0),player.getTb2().get(0), player.getTb3().get(0));
            System.out.println("--------------------------------------------------------------------------------------------------------------------");
            for (int i=1; i<=top; i++){
                System.out.printf("%-3s | %-3s | %-30s | %-5s | %-5s | %-15s | %-5s | %-7s | %-7s | %-7s |\n", player.getRank().get(i), player.getSno().get(i), player.getName().get(i), player.getFed().get(i), player.getRtg().get(i), player.getClub_city().get(i),player.getPts().get(i),player.getTb1().get(i),player.getTb2().get(i), player.getTb3().get(i));
            }
            System.out.println("--------------------------------------------------------------------------------------------------------------------");
            System.out.println("");
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
    }
}
