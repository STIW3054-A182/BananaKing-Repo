package com.rtproject;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class KedahPlayers implements Runnable {
	List<String> link;
	static double totalplayer;

	public KedahPlayers(List<String> line) {
		this.link = line;

	}

	public KedahPlayers() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		List<String> kList = new ArrayList<String>();
		double kedahplyer = 0;

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
						final String player = row.select("td:nth-of-type(4)").text();
						if (("KEDAH").equals(state)) {
							if (kList.contains(category)) {
								double kedahplyers = Double.parseDouble(player.replace(",", "."));
								kedahplyer += kedahplyers;
							} else {
								kedahplyer = Double.parseDouble(player.replace(",", "."));
								kList.add(category);
							}
						}

					}
					String a = "KUALA LUMPUR";
					String format = "| %-13s| %-8s| %-8s|\n";
					System.out.format(format, a, kedahplyer, category);
					totalplayer += kedahplyer;
					kedahplyer = 0;
				} catch (Exception e) {

				}

			}
		}
		String a = "TOTAL";
		String format = "| %-13s| %-8s| %-8s|\n";
		System.out.format(format, a, totalplayer, " ");

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

	public double getGrandTotal() {

		return KedahPlayers.totalplayer;
	}

}

