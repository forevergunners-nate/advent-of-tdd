package org.advent.day1;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Solution
{
    public static void main(String[] args) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("day1-elf-calories.txt");
        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        ElfCalorieCounter ecc = new ElfCalorieCounter(reader);
        int result = ecc.maxElfCalories();
        System.out.println("Max Calories Value: " + result);
    }
}
