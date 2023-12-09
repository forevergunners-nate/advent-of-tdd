package org.advent.day7;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Day7Part2Solution {

    private List<GameCardPart2> gameCards;
    public Day7Part2Solution(BufferedReader bufferedReader) {
        this.gameCards = new ArrayList<>();
        try {
            for (String line; (line = bufferedReader.readLine()) != null; ) {
                // Cards and bids:
                String[] params = line.trim().split(" ");
                GameCardPart2 gameCard = new GameCardPart2(params[0], Long.parseLong(params[1].trim()));
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
