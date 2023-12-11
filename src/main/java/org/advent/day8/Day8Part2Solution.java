package org.advent.day8;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class Day8Part2Solution {
    private static final String startLabel = "A";
    private static final String destinationLabel = "Z";
    private Map<String, Node> labelNodeMap;

    private List<Node> startNodes;
    private List<Node> destinationNodes;
    private String directions;
    private List<Long> subSteps;
    public Day8Part2Solution(BufferedReader bufferedReader) {
        this.labelNodeMap = new HashMap<>();
        this.startNodes = new ArrayList<>();
        this.destinationNodes = new ArrayList<>();
        this.subSteps = new ArrayList<>();
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
                    if (rootLabel.endsWith(startLabel)) {
                        this.startNodes.add(rootNode);
                    }
                    if (rootLabel.endsWith(destinationLabel)) {
                        this.destinationNodes.add(rootNode);
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

    public long solve() {
        // for loop solution
        long subStep = 0L;
        for (int directionCounter = 0; directionCounter < this.directions.length(); directionCounter++) {
            subStep++;
            List<Node> nextStartNodes = new ArrayList<>();
            if (this.startNodes.isEmpty()) {
                return getLCM(this.subSteps.stream().mapToLong(Long::longValue).toArray(), 0);
            }
            for (Node startNode : this.startNodes) {
                if(this.directions.charAt(directionCounter) == 'L') {
                    startNode = startNode.getLeft();
                } else {
                    startNode = startNode.getRight();
                }
                if (this.destinationNodes.contains(startNode)) {
                    // reaches at least 1 'Z' node
                    this.subSteps.add(subStep);
                } else {
                    nextStartNodes.add(startNode);
                }
            }

            this.startNodes = nextStartNodes;
            if (directionCounter == this.directions.length() - 1) {
                directionCounter = -1;
            }
        }
        return -1;
//        return this.totalSteps;
    }


    static long gcd(long a, long b)
    {
        return b == 0? a:gcd(b, a % b);
    }

    // lowest common multiply
    static long getLCM(long[] nums, int idx)
    {

        // lcm(a,b) = (a*b/gcd(a,b))
        if (idx == nums.length - 1){
            return nums[idx];
        }
        long a = nums[idx];
        long b = getLCM(nums, idx+1);
        return (a * b  /gcd(a, b));
    }
}
