package com.huang.fproject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class MainClass {

    //static Vector<String> existList = new Vector<String>();
    //static String [] existLinkList;
    public static void main(String[] args) throws IOException {

        String path, fileName, state;

        int coreCount = Runtime.getRuntime().availableProcessors();
        ExecutorService service = Executors.newFixedThreadPool(coreCount);


        PropertyFileWriting obj = new PropertyFileWriting();
        Properties write = obj.setProperties();
        path = write.getProperty("path");
        fileName = write.getProperty("txtFile");
        state = write.getProperty("state");
        System.out.println("Path: " + path);
        System.out.println("FileName: " + fileName);
        System.out.println("State: " + state);

        Path path1 = Paths.get("E:\\eclipse\\workplace\\rtproject\\resources\\chess.txt"); //change your path
        String path2 = String.valueOf(path1.getParent());
        String fileName2 = String.valueOf(path1.getFileName());

        List<String> myURLArrayList = Files.readAllLines(path1);
        List<String> ValidURLlist = new ArrayList<>();
        System.out.println("\nVerifying the URLs ......");

        for (String url : myURLArrayList) {
            Future<String> ValidURL = service.submit(new CheckUrls(path2, url));
            try {
				if (!ValidURL.get().equals("0"))
				    ValidURLlist.add(ValidURL.get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        ValidURLlist.forEach(System.out::println);
        service.shutdown();


        try {
            if (!service.awaitTermination(60, TimeUnit.SECONDS)) { //wait for existing tasks to terminate
                service.shutdownNow(); //cancel currently executing task
                if (!service.awaitTermination(60, TimeUnit.SECONDS)) { //wait for tasks to respond to being cancelled
                    System.err.println("Service didn't terminate!");
                }
            }
        } catch (InterruptedException e) {
            service.shutdownNow(); //re-cancel if current thread also interrupted
            Thread.currentThread().interrupt(); //preserve interrupt status
        }
        
        CheckTable CheckT = new CheckTable(ValidURLlist);
        CheckT.Checktable();
        List<List> Alldata = CheckT.getPlayerList();
        
        System.out.println("******************* WINNING POINTS **********************");
        String format ="| %-13s| %-8s| %-8s|\n";
        System.out.format(format, "State", "Pts", "Category");


        
            Thread myThread = new Thread(new Points(Alldata));
            myThread.start();

           
		/*
		 * for (int i = 0; i < line.size(); i++) { 
		 * Thread myThread2 = new Thread(newKLPoints(line.get(i)));
		 * service.execute(myThread2); 
		 * }
		 */
        
   

    

      
    }
}