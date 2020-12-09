package com.tr.statecapital1;

import java.util.HashMap;
import java.util.Set;

public class App {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("Alabama", "Montgomery");
        map.put("Alaska", "Juneau");
        map.put("Arizona", "Phoenix");
        map.put("Arkansas", "Little Rock");

        System.out.println("\nState:");
        System.out.println("===============");
        Set<String> keys = map.keySet();
        for(String key : keys) {
            System.out.println(key);
        }

        System.out.println("\nCapital:");
        System.out.println("===============");
        for(String key : keys) {
            System.out.println(map.get(key));
        }

        System.out.println("\nState/Capital:");
        System.out.println("===============");
        for(String key : keys) {
            System.out.println(key + " - " + map.get(key));
        }
    }
}
