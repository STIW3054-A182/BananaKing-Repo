package com.rtproject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class SabahPlayers implements Runnable {
	List<String> link;
	static int totalplayer;

	public SabahPlayers(List<String> line) {
		this.link = line;

	}

	public SabahPlayers() {
		
	}

	public void run() {
		List<String> kList = new ArrayList<String>();
		int sabahplyer = 0;

		for (int i = 0; i < link.size(); i++) {
			if (validTable(link.get(i))) {
				Document doc = null;

				try {
					doc = Jsoup.connect(link.get(i)).get();
					String category="";

					for (Element e : doc.select("div.defaultDialog:nth-of-type(2) > h2")) {
						Pattern regex = Pattern.compile("([A-Z][0-9]{1,2}([A-Z])?)");
						Matcher regexMatcher = regex.matcher(e.text());
						while(regexMatcher.find()) {
							category = regexMatcher.group(1);
						}
						if(category==""){
							category="Junior";
						}
					}

					for (Element row : doc.select("table.CRs1 tr")) {
						final String state = row.select("td:nth-of-type(7)").text();
						if (("SABAH").equals(state)) {	
							sabahplyer++;
							} 
						else {
								kList.add(category);
							}
						}

					
					String a = "SABAH";
					String format = "| %-13s| %-8s| %-8s|\n";
					System.out.format(format, a, category, sabahplyer);
					totalplayer += sabahplyer;
					sabahplyer = 0;
				} catch (Exception e) {

				}

			}
		}

	String a = "TOTAL";
	String format = "| %-13s| %-8s| %-8d|\n";
	System.out.format(format," ", a, totalplayer);
	System.out.println("|              |         |         |");
	}

	public boolean validTable(String url) {

		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
			if (doc.select("table.CRs1 tr").isEmpty())
				return false;
		} catch (Exception e) {

		}
		return true;
	}

	public int getGrandTotal() {

		return SabahPlayers.totalplayer;
	}

}
