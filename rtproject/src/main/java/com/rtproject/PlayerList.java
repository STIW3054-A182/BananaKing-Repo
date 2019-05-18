package com.rtproject;

import java.util.ArrayList;
import java.util.List;

public class PlayerList{

    private List<String> ranklist= new ArrayList<String>();
    private List<String> snolist= new ArrayList<String>();
    private List<String> namelist= new ArrayList<String>();
    private List<String> fedlist= new ArrayList<String>();
    private List<String> rtglist= new ArrayList<String>();
    private List<String> club_citylist= new ArrayList<String>();
    private List<String> ptslist= new ArrayList<String>();
    private List<String> tb1list= new ArrayList<String>();
    private List<String> tb2list= new ArrayList<String>();
    private List<String> tb3list= new ArrayList<String>();

    public PlayerList(){

    }

    public void setRank(String rank){
        
        ranklist.add(rank);
    }

    public void setSno(String sno){
        snolist.add(sno);
    }

    public void setName(String name){
        namelist.add(name);
    }

    public void setFed(String fed){
        fedlist.add(fed);
    }

    public void setRtg(String rtg){
        rtglist.add(rtg);
    }
    
    public void setClub_city(String club_city){
        club_citylist.add(club_city);
    }

    public void setPts(String ptst){
        String pts = ptst.replace(",", ".");
        ptslist.add(pts);
    }

    public void setTb1(String tb1t){
        String tb1 = tb1t.replace(",", ".");
        tb1list.add(tb1);
    }

    public void setTb2(String tb2t){
        String tb2 = tb2t.replace(",", ".");
        tb2list.add(tb2);
    }

    public void setTb3(String tb3t){
        String tb3 = tb3t.replace(",", ".");
        tb3list.add(tb3);
    }

    public List<String> getRank(){
        return ranklist;
    }

    public List<String> getSno(){
        return snolist;
    }

    public List<String> getName(){
        return namelist;
    }

    public List<String> getFed(){
        return fedlist;
    }

    public List<String> getRtg(){
        return rtglist;
    }

    public List<String> getClub_city(){
        return club_citylist;
    }

    public List<String> getPts(){
        return ptslist;
    }

    public List<String> getTb1(){
        return tb1list;
    }

    public List<String> getTb2(){
        return tb2list;
    }

    public List<String> getTb3(){
        return tb3list;
    }
}