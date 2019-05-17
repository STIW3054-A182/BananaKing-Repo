package com.rtproject;

import java.util.ArrayList;
import java.util.List;

public class Points implements Runnable {
    private List<List> data;

    public Points (List<List> alldata) {
        this.data = alldata;
    }

    public void run(){       
    	 List<String> allList = new  ArrayList<String>();
    	 double grandtotal = 0.0;
    	 String name  = "";
    	 boolean check = true;
    	 String format ="| %-13s| %-8s| %-8s|\n";
    	 
    	 for (int b = 0; b<13;b++) {
    		 List<Double> pointList = new  ArrayList<Double>();
        	 List<String> catList = new  ArrayList<String>();
    		 check = true;
    		 double subpoint = 0.0;
    		 name  = "";
	    	 for(List Row:data){
	    		 String state = Row.get(6).toString().trim();
	             String category = Row.get(11).toString().trim();
	             Double points = Double.parseDouble(Row.get(7).toString().trim());
	             
	            if (allList.contains(state)) {         	
	            	 if ((name).equals(state)) {
	            		 
	 	            	if (catList.contains(category)) {
	                		 int index = catList.indexOf(category);
	                		 
	                		 double newpoints = pointList.get(index);
	                		 newpoints = newpoints+points;
	                		 pointList.set(index, newpoints);
	                	 }else {
	                		catList.add(category);
	                		pointList.add(points);
	                		
	                	 }
	                }
	            }else {
	            	if (check == true) {
	            	name = state;
	            	allList.add(state);
	            	catList.add(category);
	            	pointList.add(points);
	            	check = false;
	            	}
	            }
	             }
	           
	            
	    	 for (int c =0;c<catList.size();c++) {
	    		 System.out.format(format, name, catList.get(c), pointList.get(c));
	    		 subpoint = subpoint+pointList.get(c);
	    	 }
	    	 System.out.format(format," " , "Total", subpoint);
	    	 System.out.format(format, " ", " "," " );
	    	 grandtotal=grandtotal+subpoint;
    	 }
	    	 
    	 System.out.format(format, "GRAND TOTAL", " " , grandtotal );
	    }
	}