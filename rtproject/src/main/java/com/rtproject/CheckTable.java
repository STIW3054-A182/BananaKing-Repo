package com.rtproject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class CheckTable {
    private List <String> ValidURLList;
    private Document doc;

    private List<List> Alldata = new ArrayList<List>();

    public CheckTable(List <String> ValidURLList){

        this.ValidURLList =ValidURLList;

    }

    public void Checktable () throws IOException {

        for (String uri : ValidURLList) {
            String Category="";
            try{
                doc = Jsoup.connect(uri).get();
                Pattern p=Pattern.compile("([A-Z][0-9]{1,2}([A-Z])?)");
                Matcher m = p.matcher(doc.select("div.defaultDialog h2").first().text().trim());
                while(m.find()){
                    Category = m.group();
                }
                if(Category==""){
                    Category="Junior";
                }
                
                Elements table = doc.select("table.CRs1 tr");
                if(table.hasText()){
                    table.remove(0);
                    for (Element tr : table) {
                        List<String> Rowdata = new ArrayList<String>();
                        Elements tds = tr.select("td");

                        for(Element td:tds){
                            Rowdata.add(td.text().trim().replace(",", "."));
                        }

                        Rowdata.add(Category);
                        Alldata.add(Rowdata);
                    }
                }
            } catch(IllegalStateException | NullPointerException e){}
        }
    }

    public List getPlayerList() {
        return Alldata;
    }
}