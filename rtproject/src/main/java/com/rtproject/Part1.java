package com.rtproject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Future;

public class Part1 {

    public static void part1() throws Exception {
        int coreCount = Runtime.getRuntime().availableProcessors();
        ExecutorService service = Executors.newFixedThreadPool(coreCount);
        List<String> validLink = new ArrayList<String>();
      
        PropertiesFile prop = new PropertiesFile();
        prop.createProperties();
        prop.readProperties();
        Path path = Paths.get(prop.getTextFile());
        List<String> url = Files.readAllLines(path);
        System.out.println("");

        for (int i = 0; i < url.size(); i++) {
            CheckURL t = new CheckURL(url.get(i));
            Future<String> future = service.submit(t);
            try{
                if (future.get()!=null){
                    String valid = future.get();
                    validLink.add(valid);
                }
            }catch(InterruptedException | ExecutionException e){
                e.printStackTrace();
            }
        }

        for(int a=0; a<validLink.size();a++){
            System.out.println(validLink.get(a));
        }

        System.out.println("");
        System.out.println("===================================================================================================");
        System.out.println("");
      
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
