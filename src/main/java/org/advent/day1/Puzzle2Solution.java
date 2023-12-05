package org.advent.day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class Puzzle2Solution
{
    private static final List<Integer> DIGITS = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
    private static final List<String> STRING_DIGITS = Arrays.asList("zero", "one", "two", "three", "four", "five", "six",
            "seven", "eight", "nine");

    private List<Integer> calibrationValues;

    public Puzzle2Solution(BufferedReader bufferedReader) {
        this.calibrationValues = new ArrayList<>();
        try {
            for (String line; (line = bufferedReader.readLine()) != null; ) {
                String line_in_lower_case = line.toLowerCase();
                TreeMap<Integer, Integer> results = searchDigits(line_in_lower_case);
                if (results.isEmpty()) {
                    // no digits have been identified.
                    this.calibrationValues.add(0);
                } else {
                   int firstNumber = results.firstEntry().getValue() * 10;
                   int secondNumber = results.lastEntry().getValue();
                   this.calibrationValues.add(firstNumber + secondNumber);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private TreeMap<Integer, Integer> searchDigits(String line) {
        TreeMap<Integer, Integer> map = new TreeMap<>();;
        // find digits
        char[] lineCharArray= line.toCharArray();
        int i = 0;
        for (; i < lineCharArray.length; i++) {
            if (Character.isDigit(lineCharArray[i])) {
                map.put(i, Character.getNumericValue(lineCharArray[i]));
            }
        }
        // find string digits
        i = 0;
        for (; i < STRING_DIGITS.size(); i++) {
            String currentStringDigit = STRING_DIGITS.get(i);
            if (line.contains(currentStringDigit)) {
                map.put(line.indexOf(currentStringDigit), DIGITS.get(i));
                map.put(line.lastIndexOf(currentStringDigit), DIGITS.get(i));
            }
        }
        return map;
    }
    public int sumOfCalibrationValues() {
        return this.calibrationValues.stream().mapToInt(Integer::intValue).sum();
    }
}
