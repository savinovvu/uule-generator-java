package com.company;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;



public class UuleGenerator {

    public static void main(String[] args) {
        generate();
    }


    public static String generate() {
        String UULE_KEY = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_";
        Map<String, String> map = new HashMap<>();
        String zip = "";
        String city = "Bryansk";
        String state = "Bryansk Oblast";
        String country = "Russia";
        List<String> strings = new ArrayList<>();
        strings.add(zip);
        strings.add(city);
        strings.add(state);
        strings.add(country);
        String collect = strings.stream().map(String::trim).filter(v -> nonNull(v) && !v.isEmpty()).collect(Collectors.joining(","));
        String loc = collect.toLowerCase()
                .replaceAll("å", "a")
                .replaceAll("ä", "a")
                .replaceAll("ö", "o");

        System.out.println(loc);
        String encoded = Base64.getEncoder().encodeToString((loc).getBytes()).replaceAll("=", "").trim();


        int numberStartSymbol = loc.length() % UULE_KEY.length();
        String symbol = String.valueOf(UULE_KEY.toCharArray()[numberStartSymbol]);
        String result = "w+CAIQICI" + symbol + encoded;
        return result;
    }
}