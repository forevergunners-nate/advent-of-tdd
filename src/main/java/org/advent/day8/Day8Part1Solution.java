package org.advent.day8;

import org.advent.day7.GameCard;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class Day8Part1Solution {
    private static final String startLabel = "AAA";
    private static final String destinationLabel = "ZZZ";
    private Map<String, Node> labelNodeMap;
    private String directions;
    private long totalSteps;
    public Day8Part1Solution(BufferedReader bufferedReader) {
        this.labelNodeMap = new HashMap<>();
        try {
            for (String line; (line = bufferedReader.readLine()) != null; ) {
                // puzzle input
                if (line.isEmpty()) {
                    continue;
                }
                if (line.contains("=")) {
                    // AAA = (BBB, BBB)
                    String[] params = line.trim().split("=");
                    String rootLabel = params[0].trim();
                    String[] leftRight = params[1].replace("(", "").replace(")", "").split(",");
                    String leftLabel = leftRight[0].trim();
                    String rightLabel = leftRight[1].trim();
                    Node rootNode = this.labelNodeMap.computeIfAbsent(rootLabel, Node::new);
                    if (!leftLabel.isEmpty()) {
                        Node leftNode = this.labelNodeMap.computeIfAbsent(leftLabel, Node::new);
                        rootNode.setLeft(leftNode);
                    }
                    if (!rightLabel.isEmpty()) {
                        Node rightNode = this.labelNodeMap.computeIfAbsent(rightLabel, Node::new);
                        rootNode.setRight(rightNode);
                    }
                } else {
                    this.directions = line.trim();
                }
            }
            System.out.println(this.labelNodeMap.size());
            System.out.println(Arrays.toString(this.labelNodeMap.entrySet().toArray()));
            System.out.println(this.startLabel);
            System.out.println(this.directions);

//            int directionCounter = 0;
//            this.totalSteps = getTotalStepsToDestination(this.labelNodeMap.get(this.startLabel), directionCounter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Recursive function - stackoverflow
     * @param root
     * @param direction
     * @param directionCounter
     * @return
     */
    private long getTotalStepsToDestination(Node root, int directionCounter) {
        Node nextNode = null;
        if (this.directions.charAt(directionCounter) == 'L') {
            nextNode = root.getLeft();
        } else {
            nextNode = root.getRight();
        }
        if (nextNode.getLabel().equalsIgnoreCase(this.destinationLabel)) {
            return 1L;
        }
        directionCounter++;
        if (directionCounter == this.directions.length()) {
            directionCounter = 0;
        }
        return 1L + getTotalStepsToDestination(nextNode, directionCounter);
    }

    public long solve() {
        // for loop solution
        Node root = this.labelNodeMap.get(this.startLabel);
        for (int directionCounter = 0; directionCounter < this.directions.length(); directionCounter++) {
            this.totalSteps++;
            if(this.directions.charAt(directionCounter) == 'L') {
                root = root.getLeft();
            } else {
                root = root.getRight();
            }
            if (root.getLabel().equalsIgnoreCase(this.destinationLabel)) {
                return this.totalSteps;
            }
            if (directionCounter == this.directions.length() - 1) {
                directionCounter = -1;
            }
        }
        return -1;
//        return this.totalSteps;
    }
}
