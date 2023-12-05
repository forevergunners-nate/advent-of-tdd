package org.advent.day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class Day2Part2Solution {

    private static final String COLOR_RED = "red";
    private static final String COLOR_GREEN = "green";
    private static final String COLOR_BLUE = "blue";
    private static final List<String> COLORS = Arrays.asList(COLOR_RED, COLOR_GREEN, COLOR_BLUE);
    private List<Integer> powerOfGames;
    public Day2Part2Solution(BufferedReader bufferedReader) {
        this.powerOfGames = new ArrayList<>();
        try {
            for (String line; (line = bufferedReader.readLine()) != null; ) {
                String line_in_lower_case = line.toLowerCase();
                List<GameSet> gameSets = extractGameSets(line_in_lower_case);
                int maxRedCount = Integer.MIN_VALUE;
                int maxGreenCount = Integer.MIN_VALUE;
                int maxBlueCount = Integer.MIN_VALUE;
                for (GameSet gameSet : gameSets) {
                    maxRedCount = Math.max(maxRedCount, gameSet.redCount);
                    maxBlueCount = Math.max(maxBlueCount, gameSet.blueCount);
                    maxGreenCount = Math.max(maxGreenCount, gameSet.greenCount);
                }
                this.powerOfGames.add(maxRedCount * maxBlueCount * maxGreenCount);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public int sumOfPowers() {
        return this.powerOfGames.stream().mapToInt(Integer::intValue).sum();
    }

    private List<GameSet> extractGameSets(String line) {
        List<GameSet> gameSets = new ArrayList<>();
        // Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
        String[] temp = line.trim().split(":");
        StringTokenizer stringTokenizer = new StringTokenizer(temp[1], ";");
        while (stringTokenizer.hasMoreTokens()) {
            gameSets.add(new GameSet(stringTokenizer.nextToken()));
        }
        return gameSets;
    }

    class GameSet {
        int redCount = 0;
        int blueCount = 0;
        int greenCount = 0;
        GameSet(String gameSetString) {
            //" 1 red, 2 green, 6 blue"
            gameSetString = gameSetString.trim();
            StringTokenizer stringTokenizer = new StringTokenizer(gameSetString, ",");
            while (stringTokenizer.hasMoreTokens()) {
                String current = stringTokenizer.nextToken();
                if (current.contains(COLOR_RED)) {
                    this.redCount = extractCount(current);
                } else if (current.contains(COLOR_BLUE)) {
                    this.blueCount = extractCount(current);
                } else if (current.contains(COLOR_GREEN)) {
                    this.greenCount = extractCount(current);
                }
            }

        }

        private int extractCount(String current) {
            //" 1 red"
            String[] temp = current.trim().split(" ");
            return Integer.parseInt(temp[0]);
        }
    }
}
