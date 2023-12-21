package org.advent.day18;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *  Reads to solve the problem:
 *  1. Shoelace Formula: https://en.wikipedia.org/wiki/Shoelace_formula
 *  2. Pick's theorem: https://en.wikipedia.org/wiki/Pick%27s_theorem
 *
 *
 */
public class Day18Part2Solution {
    public static final char UP = 'U';
    public static final char DOWN = 'D';
    public static final char LEFT = 'L';
    public static final char RIGHT = 'R';
    public static final String DIRECTIONS = "RDLU";
    private List<GridPoint> points;
    private long bouderies = 0L;
    public Day18Part2Solution(String filePath) throws IOException, URISyntaxException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        Path path = Paths.get(classloader.getResource(filePath).toURI());
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        this.points = new ArrayList<>();
        int i = 0;
        GridPoint last = new GridPoint(0, 0);
        this.points.add(last);
        for (String line : lines) {
            //System.out.println("Line:" + line);
            // puzzle input
            // R 6 (#70c710)
            String[] params = line.split(" ");
            String part2Params = params[2].replace("(#", "").replace(")", "");
            char direction = getDirection(Integer.parseInt(part2Params.substring(5)));
            int distance = Integer.parseInt(part2Params.substring(0, 5), 16);
            last = dig(last, direction, distance);
            this.points.add(last);
            i++;
        }
    }

    private char getDirection(int i) {
        return DIRECTIONS.charAt(i);
    }

    private GridPoint dig(GridPoint last, char direction, int distance) {
        this.bouderies += distance; // for the distance;
        int row = last.x;
        int col = last.y;
        GridPoint result = null;
        if (direction == RIGHT) {
            int new_col = col + distance;
            result = new GridPoint(row, new_col);
        }
        if (direction == LEFT) {
            int new_col = col - distance;
            result = new GridPoint(row, new_col);
        }
        if (direction == UP) {
            int new_row = row - distance;
            result = new GridPoint(new_row, col);
        }
        if (direction == DOWN) {
            int new_row = row + distance;
            result = new GridPoint(new_row, col);
        }
        return result;
    }

    public long solve() {
        long shoelaceLeftProductSum = 0L;
        long shoelaceRightProductSum = 0L;
        for (int i = 0; i + 1 < this.points.size(); i++) {
            shoelaceLeftProductSum += (long) this.points.get(i).x * this.points.get(i + 1).y;
            shoelaceRightProductSum += (long) this.points.get(i).y * this.points.get(i + 1).x;
        }
        long a = Math.abs(shoelaceLeftProductSum - shoelaceRightProductSum) / 2L; // Shoelace Formula
        long i = a -  this.bouderies / 2L + 1; // Pick's theorem
        return i + this.bouderies;
    }

}