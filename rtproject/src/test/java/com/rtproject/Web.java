package com.rtproject;

import java.net.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Web{
    String url;

    public Web(String url){
        this.url=url;
    }

    public void check(){
		try {
			if(exists(url)){
				

				if (player(url)) {
					System.out.println( " - " + url + " (Exist: Valid)");	
				}
				
				else {
						System.out.println( " - " + url +" (Exist: Not Valid)");	
				}
			}
	
			else{
				System.out.println( " - " + url + " (Not Exist)");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean exists(String URLName) {

		try {
			HttpURLConnection.setFollowRedirects(false);
			HttpURLConnection con = (HttpURLConnection) new URL(URLName).openConnection();
			con.setConnectTimeout(1000);
			con.setReadTimeout(1000);
			con.setRequestMethod("HEAD");
			return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean player(String web) {

		Document doc = null;
			
		try {
			doc = Jsoup.connect(web).get();

			if (doc.select("table.CRs1 tr").isEmpty()) {
				return false;
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}
