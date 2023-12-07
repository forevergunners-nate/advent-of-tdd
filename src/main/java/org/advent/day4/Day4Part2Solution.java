package org.advent.day4;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class Day4Part2Solution {
    private Map<Integer, List<Integer>> winningScratchCardTracker;
    private int currentCard = 1;

    public Day4Part2Solution(BufferedReader bufferedReader) {
        this.winningScratchCardTracker = new HashMap<>();
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
                stringTokenizer = new StringTokenizer(myNumbers, " ");
                while (stringTokenizer.hasMoreTokens()) {
                    String current = stringTokenizer.nextToken();
                    int number = Integer.parseInt(current.trim());
                    if (winninerNumberTracker.containsKey(number)) {
                        winninerNumberTracker.put(number, winninerNumberTracker.get(number) - 1);
                    }
                }
                int winTimes = 0;
                if (!winninerNumberTracker.isEmpty()) {
                    for (Map.Entry<Integer, Integer> entry : winninerNumberTracker.entrySet()) {
                        if (0 == entry.getValue()) {
                            winTimes++;
                        }
                    }
                    System.out.println("Card : " + currentCard + ", winTimes: " + winTimes);
                    List<Integer> currentScratchCards = this.winningScratchCardTracker.getOrDefault(currentCard, new ArrayList<>());
                    while(winTimes > 0) {
                        currentScratchCards.add(currentCard + winTimes);
                        winTimes--;
                    }
                    this.winningScratchCardTracker.put(currentCard, currentScratchCards);
                    System.out.println(Arrays.toString(this.winningScratchCardTracker.entrySet().toArray()));
                }
                currentCard++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public int sumOfScratchCards() {
        int sum = 0;
        for(Map.Entry<Integer, List<Integer>> entry : this.winningScratchCardTracker.entrySet()) {
            sum++; // current card
            sum += calculateWonScratchCards(entry.getValue());
        }
        return sum;
    }

    private int calculateWonScratchCards(List<Integer> cardsWon) {
        int sum = 0;
        if (cardsWon.isEmpty()) {
            return 0;
        }
        sum += cardsWon.size();
        for (int cardWon : cardsWon) {
            sum += calculateWonScratchCards(this.winningScratchCardTracker.get(cardWon));
        }
        return sum;
    }
}
