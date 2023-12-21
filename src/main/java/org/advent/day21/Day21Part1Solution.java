package org.advent.day21;

import javax.swing.text.Position;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

public class Day21Part1Solution {
    public static final char UP = 'U';
    public static final char DOWN = 'D';
    public static final char LEFT = 'L';
    public static final char RIGHT = 'R';
    public static final String DIRECTIONS = "UDLR";
    public static final int[] DIRECTION_X = {-1, 1, 0, 0};
    public static final int[] DIRECTION_Y = {0, 0, -1, 1};
    private int steps = 0;
    private char[][] input;
    private boolean[][] visited;
    List<Point> points;
    private int startX;
    private int startY;

    public Day21Part1Solution(int steps, String filePath) throws IOException, URISyntaxException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        Path path = Paths.get(classloader.getResource(filePath).toURI());
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        int rows = lines.size() - 2;
        this.steps = steps;
        this.input = new char[rows][];
        this.visited = new boolean[rows][];

        for (int i = 0; i < lines.size() ; i++ ) {
            String line = lines.get(i);
            if (i == 0 || i == lines.size() - 1) {
                continue;
            }
            char[] array = line.toCharArray();
            this.input[i - 1] = array;
            this.visited[i - 1] = new boolean[line.length()];
            if (line.contains("S")) {
                this.startY = line.indexOf('S');
                this.startX = i - 1;
            }
        }
        //System.out.println(startX + ", " + startY);
        this.points = new ArrayList<>();
        bfs(startX, startY, this.steps);
    }

    private void bfs(int startX, int startY, long steps) {
        Queue<GardenerPosition> queue = new LinkedList<>();
        queue.offer(new GardenerPosition(startX, startY, steps));
        while (!queue.isEmpty()) {
            GardenerPosition current = queue.poll();
            // even numbers of steps, add point
            if (current.getSteps() % 2 == 0L) {
                //System.out.println("current.getPointObject()" + current.getPointObject());
                this.points.add(current.getPointObject());
            }
            if (current.getSteps() == 0L) {
                continue;
            }
            for (int i = 0; i < DIRECTIONS.length(); i++) {
                int nextX = getNextX(current.getX(), DIRECTIONS.charAt(i));
                int nextY = getNextY(current.getY(), DIRECTIONS.charAt(i));
                if (isCoordinateValid(nextX, nextY, this.input.length, this.input[current.getX()].length)
                        && !this.visited[nextX][nextY]) {
                    this.visited[nextX][nextY] = true;
                    queue.offer(new GardenerPosition(nextX, nextY, current.getSteps() - 1));
                }
            }
        }
    }

    private boolean coordinateInbound(int x, int y, int rows, int cols) {
        return x >= 0 && x <= rows - 1 && y >= 0 && y <= cols - 1;
    }

    private boolean isCoordinateValid(int x, int y, int rows, int cols) {
        return this.input[x][y] != '#' && coordinateInbound(x, y, rows, cols);
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
        return this.points.size() - 1;// minus starting point;
    }
}
