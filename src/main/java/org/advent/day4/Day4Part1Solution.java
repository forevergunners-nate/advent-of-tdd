package org.advent.day4;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class Day4Part1Solution {
    private List<Integer> gamePoints;

    public Day4Part1Solution(BufferedReader bufferedReader) {
        this.gamePoints = new ArrayList<>();
        try {
            for (String line; (line = bufferedReader.readLine()) != null; ) {
                Map<Integer, Integer> winninerNumberTracker = new HashMap<>();
                //Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
                String[] lineArray = line.split(":");
                String winnerNumbers = lineArray[1].split("\\|")[0].trim();
                String myNumbers = lineArray[1].split("\\|")[1].trim();
                System.out.println("Winner Numbers: " + winnerNumbers);
                System.out.println("My Numbers: " + myNumbers);
                StringTokenizer stringTokenizer = new StringTokenizer(winnerNumbers, " ");
                while (stringTokenizer.hasMoreTokens()) {
                    String current = stringTokenizer.nextToken();
                    int number = Integer.parseInt(current.trim());
                    if (winninerNumberTracker.containsKey(number)) {
                        winninerNumberTracker.put(number, winninerNumberTracker.get(number) + 1);
                    } else {
                        winninerNumberTracker.put(number, 1);
                    }
                }
                System.out.println(Arrays.toString(winninerNumberTracker.entrySet().toArray()));
                stringTokenizer = new StringTokenizer(myNumbers, " ");
                while (stringTokenizer.hasMoreTokens()) {
                    String current = stringTokenizer.nextToken();
                    int number = Integer.parseInt(current.trim());
                    if (winninerNumberTracker.containsKey(number)) {
                        winninerNumberTracker.put(number, winninerNumberTracker.get(number) - 1);
                    }
                }
                System.out.println(Arrays.toString(winninerNumberTracker.entrySet().toArray()));
                int winTimes = 1;
                int gamePoint = 0;
                boolean everWon = false;
                if (!winninerNumberTracker.isEmpty()) {
                    for (Map.Entry<Integer, Integer> entry : winninerNumberTracker.entrySet()) {
                        if (0 == entry.getValue()) {
                            gamePoint = winTimes <= 1 ? 1 : gamePoint*2;
                            winTimes++;
                        }
                    }
                    System.out.println("Adding gamePoint:" + gamePoint);
                    this.gamePoints.add(gamePoint);

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public int sumOfGamePoints() {
        return this.gamePoints.stream().mapToInt(Integer::intValue).sum();
    }
}
