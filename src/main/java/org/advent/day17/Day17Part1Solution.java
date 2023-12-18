package org.advent.day17;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Day17Part1Solution {
    public static final char UP = 'U';
    public static final char DOWN = 'D';
    public static final char LEFT = 'L';
    public static final char RIGHT = 'R';
    public static final String DIRECTIONS = "UDLR";
    public static final int[] DIRECTION_X = {-1, 1, 0, 0};
    public static final int[] DIRECTION_Y = {0, 0, -1, 1};
    private int[][] input;
    private Set<MovementRecord> visited;
    private int rows;
    private int cols;
    public Day17Part1Solution(String filePath) throws IOException, URISyntaxException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        Path path = Paths.get(classloader.getResource(filePath).toURI());
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        this.rows = lines.size();
        this.input = new int[this.rows][];
        int i = 0;
        for (String line : lines) {
            //System.out.println("Line:" + line);
            // puzzle input
            char[] lineArray = line.toCharArray();
            this.cols = lineArray.length;
            this.input[i] = new int[this.cols];
            for (int j = 0; j < this.cols; j++) {
                this.input[i][j] = Character.getNumericValue(lineArray[j]);
            }
            i++;
        }
        this.visited = new HashSet<>();
        //System.out.println(Arrays.deepToString(this.input));
    }

    private long bfs(int dX, int dy) {
        PriorityQueue<MovementState> heap = new PriorityQueue<>();
        heap.add(new MovementState(new MovementRecord(0, 1, RIGHT, 1), this.input[0][1]));
        heap.add(new MovementState(new MovementRecord(1, 0, DOWN, 1), this.input[1][0]));
        while (!heap.isEmpty()) {
            MovementState current = heap.remove();
            MovementRecord currentMr = current.getMr();
            int currentX = currentMr.x();
            int currentY = currentMr.y();
            if (this.visited.contains(currentMr)) {
                continue;
            }
            if (currentX == dX && currentY == dy) {
                return current.getCost();
            }
            this.visited.add(currentMr);
            char currentDirection = currentMr.direction();
            long currentCost = current.getCost();
            long currentSteps = currentMr.steps();
            for (int i = 0; i < DIRECTIONS.length(); i++) {
                char reverseDirection = getReverseDirection(currentDirection);
                char skipDirection = currentSteps >= 2 ? currentDirection : ' ';
                char nextDirection = DIRECTIONS.charAt(i);
                if (nextDirection == reverseDirection) {
                    continue;
                }
                if (nextDirection == skipDirection) {
                    continue;
                }
                int nextX = getNextX(currentX, nextDirection);
                int nextY = getNextY(currentY, nextDirection);
                if (!coordinateInbound(nextX, nextY)) {
                    continue;
                }
                long nextCost = currentCost + this.input[nextX][nextY];
                long nextSteps = currentDirection == nextDirection ? currentSteps + 1 : 0;
                System.out.println(String.format("added x=%d, y=%d to bfs heap", nextX, nextY));
                heap.add(new MovementState(new MovementRecord(nextX, nextY, nextDirection, nextSteps), nextCost));
            }

        }
        return -1;
    }

    private char getReverseDirection(char direction) {
        return switch (direction) {
            case UP -> DOWN;
            case DOWN -> UP;
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
            default -> ' ';
        };
    }


    private boolean coordinateInbound(int x, int y) {
        return x >= 0 && x <= this.rows - 1 && y >= 0 && y <= this.cols - 1;
    }

    private int getNextX(int x, char direction) {
        int index = DIRECTIONS.indexOf(direction);
        return x + DIRECTION_X[index];
    }
    private int getNextY(int y, char direction) {
        int index = DIRECTIONS.indexOf(direction);
        return y + DIRECTION_Y[index];
    }

    public long solve() {
        return bfs(this.rows - 1, this.cols - 1);
    }

}
