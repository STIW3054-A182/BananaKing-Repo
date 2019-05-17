package com.rtproject;

public class Main {

    public static void main(String[] args) throws Exception {

        System.out.println("------------STIW3054 REAL-TIME PROGRAMMING-------------");
        System.out.println("------------------FINAL PROJECT------------------------");
        System.out.println("1. Check URLs");
        System.out.println("2. Count the number of players");
        System.out.println("3. Display statistics");
        System.out.println("4. Display all players from KEDAH");
        System.out.println("5. Display all TOP 3 players from each category");
        System.out.println("6. Count the winning points");
        System.out.println("7. Display a player result");
        System.out.println("");
        System.out.println("-------------------------------------------------------");
        System.out.println("Verifying URL...");
        System.out.println("");
        System.out.println("1. Display valid URLs:");
        Part1.part1();
        System.out.println("2. Count the number of players:");
        Part2.part2();
        System.out.println("3. Display statistics:");
        Part3.part3();
        System.out.println("4. Display all players from KEDAH:");
        Part4.part4();
        System.out.println("5. Display all TOP 3 players from each category:");
        Part5.part5();
        System.out.println("6. Count the winning points:");
        Part6.part6();
        System.out.println("7. Display a player result:");
        Part7.part7();
        System.out.println("Finished!");

    }
}