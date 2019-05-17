package com.rtproject;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

public class Part3 {
	public static void part3() throws Exception {
		PropertiesFile prop = new PropertiesFile();
        prop.createProperties();
		prop.readProperties();
		Properties pro = new Properties();
		InputStream input = new FileInputStream("config.properties");
        pro.load(input);
        Path path = Paths.get(prop.getTextFile());
        List<String> line = Files.readAllLines(path);

		System.out.println("");
		String format = "| %-13s| %-8s| %-8s|\n";
		System.out.println("------------------------------------");
		System.out.format(format, "State", "Category", "Total");
		System.out.println("|--------------|---------|---------|");

		Thread myThread = new Thread(new KedahPlayers(line));
		myThread.start();

		try {
			myThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Thread myThread2 = new Thread(new KLPlayers(line));
		myThread2.start();
		try {
			myThread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Thread myThread3 = new Thread(new PPPlayers(line));
		myThread3.start();
		try {
			myThread3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Thread myThread4 = new Thread(new NSPlayers(line));
		myThread4.start();
		try {
			myThread4.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Thread myThread5 = new Thread(new PahangPlayers(line));
		myThread5.start();
		try {
			myThread5.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Thread myThread6 = new Thread(new PutrajayaPlayers(line));
		myThread6.start();
		try {
			myThread6.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Thread myThread7 = new Thread(new PerakPlayers(line));
		myThread7.start();
		try {
			myThread7.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Thread myThread8 = new Thread(new SelangorPlayers(line));
		myThread8.start();
		try {
			myThread8.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Thread myThread9 = new Thread(new SarawakPlayers(line));
		myThread9.start();
		try {
			myThread9.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Thread myThread10 = new Thread(new SabahPlayers(line));
		myThread10.start();
		try {
			myThread10.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Thread myThread11 = new Thread(new MelakaPlayers(line));
		myThread11.start();
		try {
			myThread11.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Thread myThread12 = new Thread(new KelantanPlayers(line));
		myThread12.start();
		try {
			myThread12.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Thread myThread13 = new Thread(new JohorPlayers(line));
		myThread13.start();
		try {
			myThread13.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Thread myThread14 = new Thread(new grandTotalPlayers());
		myThread14.start();
		try {
			myThread14.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("");
        System.out.println("===================================================================================================");
        System.out.println("");
	}
}
