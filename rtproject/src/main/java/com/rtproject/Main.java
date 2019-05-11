package com.rtproject;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws Exception {
        int coreCount = Runtime.getRuntime().availableProcessors();
        ExecutorService service = Executors.newFixedThreadPool(coreCount);

        
        PropertiesFile prop = new PropertiesFile();
        prop.createProperties();
        prop.readProperties();

        Path path = Paths.get(prop.getTextFile());

        List<String> url = null;
        url = Files.readAllLines(path);
        for (int i = 0; i < url.size(); i++) {
            Thread thread = new Thread(new CheckURL(url.get(i)));
            service.execute(thread);
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

    }
}
