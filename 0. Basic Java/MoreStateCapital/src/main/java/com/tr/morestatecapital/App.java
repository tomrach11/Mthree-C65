package com.tr.morestatecapital;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class App {
    public static void main(String[] args) throws IOException {
        HashMap<String, Capital> map = new HashMap<>();

        Scanner sc = new Scanner(new BufferedReader(new FileReader("MoreStateCapitals.txt")));

        while(sc.hasNextLine()) {
            String[] list = sc.nextLine().split("::");
            //set up capital object
            Capital capital = new Capital();
            capital.setName(list[1]);
            capital.setPopulation(Integer.parseInt(list[2]));
            capital.setArea(Double.parseDouble(list[3]));
            //add to HashMap
            map.put(list[0], capital);
        }

        Set<String> keys = map.keySet();
        //display all info. of 50 states
        System.out.println("All 50 States:");
        System.out.println("======================");
        for(String key : keys) {
            Capital capital = map.get(key);
            System.out.println(key + " - " + capital.getName() + " | Pop: " + capital.getPopulation() + " | Area: " +capital.getArea() + "sq mi");
        }

        sc = new Scanner(System.in);
        System.out.print("\nPlease enter the lower limit for capital city population: ");
        int population = Integer.parseInt(sc.nextLine());

        System.out.println("\nAll State(s) population over " + population + " : ");
        System.out.println("======================");
        for(String key : keys) {
            Capital capital = map.get(key);
            if(capital.getPopulation() >= population) {
                System.out.println(key + " - " + capital.getName() + " | Pop: " + capital.getPopulation() + " | Area: " + capital.getArea() + "sq mi");
            }
        }

        System.out.print("\nPlease enter the upper limit for capital city population: ");
        double area = Integer.parseInt(sc.nextLine());

        System.out.println("\nAll State(s) area under " + area + " : ");
        System.out.println("======================");
        for(String key : keys) {
            Capital capital = map.get(key);
            if(capital.getArea() <= area) {
                System.out.println(key + " - " + capital.getName() + " | Pop: " + capital.getPopulation() + " | Area: " + capital.getArea() + "sq mi");
            }
        }


    }
}
