package org.advent.day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class Puzzle1Solution
{
    private List<Integer> calibrationValues;
    public Puzzle1Solution(BufferedReader bufferedReader) {
        this.calibrationValues = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        try {
            for (String line; (line = bufferedReader.readLine()) != null; ) {
                char[] lineCharArray= line.toCharArray();
                for (char c : lineCharArray) {
                    if (Character.isDigit(c)) {
                        queue.offer(Character.getNumericValue(c));
                    }
                }
                if (queue.isEmpty()) {
                    // no digits have been identified.
                    this.calibrationValues.add(0);
                } else {
                   int firstNumber = queue.peek() * 10;
                   while (queue.size() > 1) {
                       queue.poll();
                   }
                   int secondNumber = queue.poll();
                   this.calibrationValues.add(firstNumber + secondNumber);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int sumOfCalibrationValues() {
        return this.calibrationValues.stream().mapToInt(Integer::intValue).sum();
    }
}
