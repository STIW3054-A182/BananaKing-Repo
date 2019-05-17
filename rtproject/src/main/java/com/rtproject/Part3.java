package com.rtproject;

import java.util.concurrent.ExecutorService;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Part3 {
	public static void part3Display() throws Exception {

		int coreCount = Runtime.getRuntime().availableProcessors();
		ExecutorService service = Executors.newFixedThreadPool(coreCount);

		PropertiesFile obj = new PropertiesFile();
		Properties write = obj.setProperties();
		String path = write.getProperty("path");
		String fileName = write.getProperty("txtFile");
		String state = write.getProperty("state");

		Path path1 = Paths.get("C:\\Users\\HP\\eclipse-workspace\\rtproject\\resources\\url.txt"); // change your path
		List<String> line = null;

		line = Files.readAllLines(path1);

		String format = "| %-13s| %-8s| %-8s|\n";
		System.out.format(format, "State", "Category", "Total");

		Thread myThread = new Thread(new KedahPlayers(line));
		myThread.start();

		try {
			myThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Thread myThread2 = new Thread(new KLPlayers(line));
		myThread2.start();
		try {
			myThread2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Thread myThread3 = new Thread(new PPPlayers(line));
		myThread3.start();
		try {
			myThread3.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Thread myThread4 = new Thread(new NSPlayers(line));
		myThread4.start();
		try {
			myThread4.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Thread myThread5 = new Thread(new PahangPlayers(line));
		myThread5.start();
		try {
			myThread5.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Thread myThread6 = new Thread(new PutrajayaPlayers(line));
		myThread6.start();
		try {
			myThread6.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread myThread7 = new Thread(new PerakPlayers(line));
		myThread7.start();
		try {
			myThread7.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Thread myThread8 = new Thread(new SelangorPlayers(line));
		myThread8.start();
		try {
			myThread8.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Thread myThread9 = new Thread(new SarawakPlayers(line));
		myThread9.start();
		try {
			myThread9.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Thread myThread10 = new Thread(new SabahPlayers(line));
		myThread10.start();
		try {
			myThread10.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Thread myThread11 = new Thread(new MelakaPlayers(line));
		myThread11.start();
		try {
			myThread11.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Thread myThread12 = new Thread(new KelantanPlayers(line));
		myThread12.start();
		try {
			myThread12.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Thread myThread13 = new Thread(new JohorPlayers(line));
		myThread13.start();
		try {
			myThread13.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Thread myThread14 = new Thread(new grandTotalPlayers());
		myThread14.start();
		try {
			myThread14.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			if (!service.awaitTermination(60, TimeUnit.SECONDS)) { // wait for existing tasks to terminate
				service.shutdownNow(); // cancel currently executing task
				if (!service.awaitTermination(60, TimeUnit.SECONDS)) { // wait for tasks to respond to being cancelled
					System.err.println("Service didn't terminate!");
				}
			}
		} catch (InterruptedException e) {
			service.shutdownNow(); // re-cancel if current thread also interrupted
			Thread.currentThread().interrupt(); // preserve interrupt status
		}

	}
}
