package com.rtproject;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DisplayStatics {

	public static void part3Display() throws Exception {
		String path, fileName, state;

		int coreCount = Runtime.getRuntime().availableProcessors();
		ExecutorService service = Executors.newFixedThreadPool(coreCount);

		PropertiesFile obj = new PropertiesFile();
		Properties write = obj.setProperties();
		path = write.getProperty("path");
		fileName = write.getProperty("txtFile");
		state = write.getProperty("state");
		System.out.println("Path: " + path);
		System.out.println("FileName: " + fileName);
		System.out.println("State: " + state);

		Path path1 = Paths.get("C:\\Users\\HP\\eclipse-workspace\\rtproject\\resources\\url.txt"); // change your path
		List<String> line = null;

		line = Files.readAllLines(path1);

		String format = "| %-13s| %-8s| %-8s|\n";
		System.out.format(format, "State", "Pts", "Category");

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

		Thread myThread3 = new Thread(new grandTotalPlayers());
		myThread3.start();
		try {
			myThread3.join();
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
