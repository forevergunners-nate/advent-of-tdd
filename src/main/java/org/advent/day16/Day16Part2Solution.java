package org.advent.day16;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day16Part2Solution {
    public static final char UP = 'U';
    public static final char DOWN = 'D';
    public static final char LEFT = 'L';
    public static final char RIGHT = 'R';
    public static final String DIRECTIONS = "UDLR";
    public static final int[] DIRECTION_X = {-1, 1, 0, 0};
    public static final int[] DIRECTION_Y = {0, 0, -1, 1};

    private long sum = 0;

    private long max = 0;
    private char[][] input;
    private boolean[][] visited;
    private int rows;
    private int cols;

    private Set<Day16GridPoint> path;
    public Day16Part2Solution(String filePath) throws IOException, URISyntaxException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        Path path = Paths.get(classloader.getResource(filePath).toURI());
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        this.rows = lines.size();
        this.input = new char[this.rows][];
        int i = 0;
        for (String line : lines) {
            //System.out.println("Line:" + line);
            // puzzle input
            char[] lineArray = line.toCharArray();
            this.cols = lineArray.length;
            input[i++] = lineArray;
        }
        for (int j = 0; j < this.cols; j++) {
            this.sum = 0;
            this.visited = new boolean[this.rows][this.cols];
            this.path = new HashSet<>();
            dfs(0, j, DOWN);
            this.max = Long.max(this.max, this.sum);
            this.sum = 0;
            this.visited = new boolean[this.rows][this.cols];
            this.path = new HashSet<>();
            dfs(this.rows - 1, j, UP);
            this.max = Long.max(this.max, this.sum);
        }
        for (int j = 0; j < this.rows; j++) {
            this.sum = 0;
            this.visited = new boolean[this.rows][this.cols];
            this.path = new HashSet<>();
            dfs(j, 0, RIGHT);
            this.max = Long.max(this.max, this.sum);
            this.sum = 0;
            this.visited = new boolean[this.rows][this.cols];
            this.path = new HashSet<>();
            dfs(j, this.cols - 1, LEFT);
            this.max = Long.max(this.max, this.sum);
        }
        //System.out.println(Arrays.deepToString(this.input));
    }

    private void dfs(int x, int y, char direction) {
        Day16GridPoint currentGrid = new Day16GridPoint(x, y, direction);
        if (!coordinateInbound(x, y) || this.path.contains(currentGrid)) {
            return;
        }
        char currentC = this.input[x][y];
        if (!this.visited[x][y]) {
            this.sum++;
            this.visited[x][y] = true;
        }
        this.path.add(currentGrid);

        if (currentC == '.') {
            this.dfs(getNextX(x, direction), getNextY(y, direction), direction);
        } else if (currentC == '|') {
            if (direction == UP || direction == DOWN) {
                this.dfs(getNextX(x, direction), getNextY(y, direction), direction);
            } else {
                // if (direction == RIGHT || direction == LEFT)
                this.dfs(getNextX(x, UP), getNextY(y, UP), UP);
                this.dfs(getNextX(x, DOWN), getNextY(y, DOWN), DOWN);
            }
        } else if (currentC == '-') {
            if (direction == LEFT || direction == RIGHT) {
                this.dfs(getNextX(x, direction), getNextY(y, direction), direction);
            } else {
                // if (direction == UP || direction == DOWN)
                this.dfs(getNextX(x, LEFT), getNextY(y, LEFT), LEFT);
                this.dfs(getNextX(x, RIGHT), getNextY(y, RIGHT), RIGHT);
            }
        } else if (currentC == '\\') {
            if (direction == RIGHT) {
                this.dfs(getNextX(x, DOWN), getNextY(y, DOWN), DOWN);
            } else if (direction == UP) {
                this.dfs(getNextX(x, LEFT), getNextY(y, LEFT), LEFT);
            }
            else if (direction == LEFT) {
                this.dfs(getNextX(x, UP), getNextY(y, UP), UP);
            } else if (direction == DOWN) {
                this.dfs(getNextX(x, RIGHT), getNextY(y, RIGHT), RIGHT);
            }

        } else if (currentC == '/') {
            if (direction == RIGHT) {
                this.dfs(getNextX(x, UP), getNextY(y, UP), UP);
            } else if (direction == DOWN) {
                this.dfs(getNextX(x, LEFT), getNextY(y, LEFT), LEFT);
            }
            else if (direction == LEFT) {
                this.dfs(getNextX(x, DOWN), getNextY(y, DOWN), DOWN);
            } else if (direction == UP) {
                this.dfs(getNextX(x, RIGHT), getNextY(y, RIGHT), RIGHT);
            }
        }
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
        return this.max;
    }
}
