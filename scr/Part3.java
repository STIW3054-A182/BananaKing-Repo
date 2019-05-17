package com.rtproject;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Part3 {
	public static void part1Display() throws Exception {
        int coreCount = Runtime.getRuntime().availableProcessors();
        ExecutorService service = Executors.newFixedThreadPool(coreCount);
 
        PropertiesFile prop = new PropertiesFile();
    //    prop.createProperties();
    //    prop.readProperties();
        List<String> validLink = new ArrayList<String>();

        Path path = Paths.get(prop.getPropValues());

        List<String> url = Files.readAllLines(path);
        System.out.println("Display valid link:");
        for (int i = 0; i < url.size(); i++) {
 
            CheckURL t = new CheckURL(url.get(i));  	
            Future<String> future = service.submit(t);
            try{
                if (future.get()!=null){
                    String valid = future.get();
                    validLink.add(valid);
                }
            }catch(ExecutionException e){
                e.printStackTrace();
            }
        }
        
        
       // for(int a=0; a<validLink.size();a++){
         //  System.out.println(validLink.get(a));
      // }
        
       // for (int i = 0; i < validLink.size(); i++) {
       //     Thread myThread = new Thread(new KedahPlayer(url.get(i)));
       //     service.execute(myThread);
      //  }
      

      
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
