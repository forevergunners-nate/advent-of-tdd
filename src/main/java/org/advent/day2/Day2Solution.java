package org.advent.day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class Day2Solution {

    private static final String COLOR_RED = "red";
    private static final String COLOR_GREEN = "green";
    private static final String COLOR_BLUE = "blue";
    private static final List<String> COLORS = Arrays.asList(COLOR_RED, COLOR_GREEN, COLOR_BLUE);
    private List<Integer> idsOfPossibleGames;
    public Day2Solution(int targetRedCubes, int targetGreenCubes, int targetBlueCubes,
                        BufferedReader bufferedReader) {
        this.idsOfPossibleGames = new ArrayList<>();
        try {
            for (String line; (line = bufferedReader.readLine()) != null; ) {
                String line_in_lower_case = line.toLowerCase();
                int gameId = extractGameID(line_in_lower_case);
                List<GameSet> gameSets = extractGameSets(line_in_lower_case);
                if (this.isLinePossible(targetRedCubes, targetGreenCubes, targetBlueCubes, gameSets)) {
                    this.idsOfPossibleGames.add(gameId);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private boolean isLinePossible(int targetRedCubes, int targetGreenCubes, int targetBlueCubes, List<GameSet> gameSets) {
        for (GameSet gameSet : gameSets) {
            if (gameSet.redCount > targetRedCubes ||
                    gameSet.greenCount > targetGreenCubes ||
                    gameSet.blueCount > targetBlueCubes) {
                return false;
            }
        }
        return true;
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

    private int extractGameID(String line) {
        // Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
        String[] temp1 = line.trim().split(":");
        String[] temp2 = temp1[0].trim().split(" ");
        return Integer.parseInt(temp2[1]);
    }

    public int sumOfIDsOfPossibleGames() {
        return this.idsOfPossibleGames.stream().mapToInt(Integer::intValue).sum();
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
