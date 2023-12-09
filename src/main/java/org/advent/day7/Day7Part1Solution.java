package org.advent.day7;

import org.advent.day6.RaceRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class Day7Part1Solution {

    private List<GameCard> gameCards;
    public Day7Part1Solution(BufferedReader bufferedReader) {
        this.gameCards = new ArrayList<>();
        try {
            for (String line; (line = bufferedReader.readLine()) != null; ) {
                // Cards and bids:
                String[] params = line.trim().split(" ");
                GameCard gameCard = new GameCard(params[0], Long.parseLong(params[1].trim()));
                this.gameCards.add(gameCard);
            }
            System.out.println("Before: " + Arrays.toString(this.gameCards.toArray()));
            Collections.sort(this.gameCards);
            int totalGameCard = this.gameCards.size();
            for (int i = totalGameCard - 1; i >= 0; i--) {
                this.gameCards.get(i).setRank(totalGameCard - i);
            }
            System.out.println("After: " + Arrays.toString(this.gameCards.toArray()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public long solve() {
        return this.gameCards.stream().mapToLong(e -> e.getBid() * e.getRank()).sum();
    }
}
