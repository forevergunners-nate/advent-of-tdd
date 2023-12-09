package org.advent.day7;

import java.util.HashMap;
import java.util.Map;

public class GameCard implements Comparable<GameCard>{
    public static final char[] LABELS = {'A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2'};
    public static final String[] TYPES = {"High card", "One pair", "Two pair", "Three of a kind", "Full house", "Four of a kind", "Five of a kind"};
    public static final Map<Character, Integer> labelWeightMap;
    static {
        labelWeightMap = new HashMap<>();
        int w = 1;
        for (char c : LABELS) {
            labelWeightMap.put(c, w++);
        }
    }
    public static final Map<String, Integer> typeWeightMap;
    static {
        typeWeightMap = new HashMap<>();
        int w = 7;
        for (String string : TYPES) {
            typeWeightMap.put(string, w--);
        }
    }
    private Map<Character, Integer> typeCheckerMap;
    private String labels;
    private long bid;
    private String type;
    private int rank;

    public GameCard(String labels, long bid) {
        // Default OPS, build checker map: A, K, Q, J, T, 9, 8, 7, 6, 5, 4, 3, or 2
        this.typeCheckerMap = new HashMap<>();
        this.labels = labels;
        this.bid = bid;
        this.type = getCardType();
        this.rank = -1;
    }

    private String getCardType() {
        String cardType = "Unknown";
        for (char c : this.labels.toCharArray()) {
            int currentCount = this.typeCheckerMap.getOrDefault(c, 0);
            this.typeCheckerMap.put(c, currentCount + 1);
        }
        int fives = 0;
        int fours = 0;
        int threes = 0;
        int twos = 0;
        int ones = 0;
        for (int count : this.typeCheckerMap.values()) {
            if (count == 5) {
                fives++;
            }
            else if (count == 4) {
                fours++;
            }
            else if (count == 3) {
                threes++;
            }
            else if (count == 2) {
                twos++;
            } else {
                ones++;
            }
        }
        if (ones == 5) {
            return "High card";
        } else if (twos == 1 && threes == 0) {
            return "One pair";
        } else if (twos == 2) {
            return "Two pair";
        } else if (twos == 0 && threes == 1) {
            return "Three of a kind";
        } else if (twos == 1 && threes == 1) {
            return "Full house";
        } else if (fours == 1) {
            return "Four of a kind";
        } else if (fives == 1) {
            return "Five of a kind";
        }
        return cardType;
    }

    @Override
    public int compareTo(GameCard o) {
        if (this.type.equalsIgnoreCase(o.getType())) {
            int totalLabel = this.labels.length();
            for (int i = 0; i < totalLabel; i++) {
                if (this.labels.charAt(i) != o.getLabels().charAt(i)) {
                    if (labelWeightMap.get(this.labels.charAt(i)) > labelWeightMap.get(o.getLabels().charAt(i))) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            }
        } else {
            if (typeWeightMap.get(this.type) > typeWeightMap.get(o.getType())) {
                return 1;
            } else {
                return -1;
            }
        }
        return 0;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public long getBid() {
        return bid;
    }

    public void setBid(long bid) {
        this.bid = bid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "GameCard{" +
                "labels='" + labels + '\'' +
                ", bid=" + bid +
                ", type='" + type + '\'' +
                ", rank=" + rank +
                '}';
    }
}
