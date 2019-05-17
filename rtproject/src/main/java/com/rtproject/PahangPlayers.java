package com.rtproject;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class PahangPlayers implements Runnable {
	List<String> link;
	static int totalplayer;

	public PahangPlayers(List<String> line) {
		this.link = line;

	}

	public PahangPlayers() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		List<String> kList = new ArrayList<String>();
		int pahangplyer = 0;

		for (int i = 0; i < link.size(); i++) {
			if (validTable(link.get(i))) {
				Document doc = null;

				try {
					doc = Jsoup.connect(link.get(i)).get();

					// get category
					String title = doc.title();
					int scrape = title.indexOf("9");
					String category = title.substring(scrape + 1).replace("(", "").replace(")", "");

					for (Element row : doc.select("table.CRs1 tr")) {
						final String state = row.select("td:nth-of-type(7)").text();
						if (("PAHANG").equals(state)) {	
							pahangplyer++;
							} 
						else {
								kList.add(category);
							}
						}

					
					String a = "PAHANG";
					String format = "| %-13s| %-8s| %-8s|\n";
					System.out.format(format, a, category, pahangplyer);
					totalplayer += pahangplyer;
					pahangplyer = 0;
				} catch (Exception e) {

				}

			}
		}

	String a = "TOTAL";
	String format = "| %-13s| %-8s| %-8d|\n";System.out.format(format," ",a,totalplayer);

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

		return PahangPlayers.totalplayer;
	}

}
