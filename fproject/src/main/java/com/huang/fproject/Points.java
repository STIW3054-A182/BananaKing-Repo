package com.huang.fproject;



import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class Points implements Runnable {
    List<List> data;


    public Points (List<List> alldata) {
        this.data = alldata;
    }

    @Override
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
	    		 System.out.format(format, name, pointList.get(c),catList.get(c) );
	    		 subpoint = subpoint+pointList.get(c);
	    	 }
	    	 System.out.format(format, "Total", subpoint," " );
	    	 System.out.format(format, " ", " "," " );
	    	 grandtotal=grandtotal+subpoint;
    	 }
	    	 
    	 System.out.format(format, "GRAND TOTAL", grandtotal," " );
	    }
	}
    

    
    
    
    
//        
//         double ki = 0;
//         double tt = 0;
//         
//        for (int i = 0; i < link.size(); i++) {
//        if (validTable(link.get(i))) {
//            Document doc = null;
//
//            try {
//                doc = Jsoup.connect(link.get(i)).get();
//
//                //get category
//                String title = doc.title();
//                int scrape = title.indexOf("9");
//                String category = title.substring(scrape + 1).replace("(", "").replace(")", "");
//
//          
//                
//                for (Element row : doc.select("table.CRs1 tr")) {
//                	final String state=row.select("td:nth-of-type(7)").text();
//                    final String pointer=row.select("td:nth-of-type(8)").text();
//                   
//                
//                if (("KEDAH").equals(state)) {
//                	 if (kList.contains(category)) {
//                		 double kis = Double.parseDouble(pointer.replace(",", "."));
//							ki = ki+kis;
//                	 }else {
//                		 ki = Double.parseDouble(pointer.replace(",", "."));
//							kList.add(category);
//                	 }
//                }
//                
//            }
//                
//                
//                
//                String a = "KEDAH";
//                String format ="| %-13s| %-8s| %-8s|\n";
//                System.out.format(format, a, ki, category);
//                tt=ki+tt;
//                ki=0;
//            }catch (Exception e) {
//               
//            }
//           
//        }
//        }
//        String a = "TOTAL";
//        String format ="| %-13s| %-8s| %-8s|\n";
//        System.out.format(format, a, tt," ");
//    }
//
//    public boolean validTable(String url) {
//
//        Document doc = null;
//        try {
//            doc = Jsoup.connect(url).get();
//            if (doc.select("table.CRs1 tr").isEmpty())
//                return false;
//        } catch (Exception e) {
//            
//        }
//        return true;
//    }

//double pointerKL, pointerNS, pointerPP, pointerPAHANG, pointerPTRJAYA, pointerPERAK, pointerSELANGOR, pointerJOHOR,
//pointerKEDAH, pointerSRW, pointerSB, pointerMLK, pointerKLT;
//double totalPointerKL = 0, totalPointerNS= 0,totalPointerPP = 0,totalPointerPAHANG = 0,totalPointerPTRJAYA = 0,totalPointerPERAK = 0,totalPointerSELANGOR = 0,
//totalPointerJOHOR = 0, totalPointerKEDAH = 0, totalPointerSRW = 0,totalPointerSB = 0,totalPointerMLK = 0,totalPointerKLT = 0;


//switch (state) {
//case "KUALA LUMPUR":
//    pointerKL = points;
//    totalPointerKL += pointerKL;
//    break;
//
//case "N.SEMBILAN":
//    pointerNS = points;
//    totalPointerNS += pointerNS;
//    break;
//
//case "PULAU PINANG":
//    pointerPP = points;
//    totalPointerPP += pointerPP;
//    break;
//
//case "PAHANG":
//    pointerPAHANG = points;
//    totalPointerPAHANG += pointerPAHANG;
//    break;
//
//case "PUTRAJAYA":
//    pointerPTRJAYA = points;
//    totalPointerPTRJAYA+= pointerPTRJAYA;
//    break;
//
//case "PERAK":
//    pointerPERAK= points;
//    totalPointerPERAK += pointerPERAK;
//    break;
//
//case "SELANGOR":
//    pointerSELANGOR = points;
//    totalPointerSELANGOR += pointerSELANGOR;
//
//case "JOHOR":
//    pointerJOHOR = points;
//    totalPointerJOHOR+=pointerJOHOR;
//    break;
//
//case "KEDAH":
//    pointerKEDAH = points;
//    totalPointerKEDAH+=pointerKEDAH;
//    break;
//
//case "SARAWAK":
//    pointerSRW = points;
//    totalPointerSRW+=pointerSRW;
//    break;
//
//case "SABAH":
//    pointerSB = points;
//    totalPointerSB+=pointerSB;
//    break;
//
//case "MELAKA":
//    pointerMLK = points;
//    totalPointerMLK+=pointerMLK;
//    break;
//
//case "KELANTAN":
//    pointerKLT = points;
//    totalPointerKLT+=pointerKLT ;
//    break;
//
//}
