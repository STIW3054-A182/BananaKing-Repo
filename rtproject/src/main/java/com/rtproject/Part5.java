package com.rtproject;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Part5 {

	public static void part5() throws Exception{

		int coreCount = Runtime.getRuntime().availableProcessors();
		ExecutorService service = Executors.newFixedThreadPool(coreCount);
        
        PropertiesFile prop = new PropertiesFile();
        prop.createProperties();
		prop.readProperties();
		Properties pro = new Properties();
		InputStream input = new FileInputStream("config.properties");
        pro.load(input);
		int top = Integer.parseInt(pro.getProperty("top"));
		List<String> validLink = new ArrayList<String>();
        Path path = Paths.get(prop.getTextFile());
        List<String> url = Files.readAllLines(path);

        System.out.println("");

        for (int i = 0; i < url.size(); i++) {
            ValidLink t = new ValidLink(url.get(i));
            Future<String> future = service.submit(t);
                if (future.get()!=null){
					String valid = future.get();
					validLink.add(valid);
                }
		}

		service.shutdown();
        try {
            if (!service.awaitTermination(60, TimeUnit.SECONDS)) {
                service.shutdownNow();

                if (!service.awaitTermination(60, TimeUnit.SECONDS)) {
                    //System.err.println("");
                }
            }

        } catch (InterruptedException e) {
            service.shutdownNow();
            Thread.currentThread().interrupt();
        }
		
		for(int x=0; x<validLink.size();x++){
			DisplayTop dt = new DisplayTop(validLink.get(x), top);
			dt.saveTable();
			dt.getTitle();
			dt.display();
        }
        
        System.out.println("===================================================================================================");
        System.out.println("");
	}

}
