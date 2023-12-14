package org.advent.day14;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

public class Day14Part1Solution {
    private List<Point> rocks;
    private long totalLoadOfRocks = 0;
    private char[][] input;
    private int rows;
    private int cols;
    public Day14Part1Solution(String filePath) throws IOException, URISyntaxException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        Path path = Paths.get(classloader.getResource(filePath).toURI());
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        //System.out.println("lines.size():" + lines.size());
        this.rows = lines.size();
        this.rocks = new ArrayList<>();
        this.input = new char[this.rows][];
        int i = 0;
        for (String line : lines) {
            //System.out.println("Line:" + line);
            // puzzle input
            //O....#....
            //O.OO#....#
            //.....##...
            //OO.#O....O
            //.O.....O#.
            //O.#..O.#.#
            //..O..#O..O
            //.......O..
            //#....###..
            //#OO..#....
            // verticalStore
            char[] lineArray = line.toCharArray();
            this.input[i] = lineArray;
            this.cols = lineArray.length;
            for (int j = 0; j < this.cols; j++) {
                if (lineArray[j] == 'O') {
                    Point point = new Point(i, j);
                    rocks.add(point);
                }
            }
            i++;
        }
        //System.out.println(Arrays.toString(rocks.toArray()));
        tiltNorth();
        //System.out.println(Arrays.deepToString(this.input));
        //System.out.println(Arrays.toString(rocks.toArray()));
        this.totalLoadOfRocks = calculateSum();
    }

    private long calculateSum() {
        Map<Integer, Integer> temp = new HashMap<>();
        this.rocks.forEach(e -> {
            int x = e.x;
            int count = 1;
            if (temp.containsKey(x)) {
                count = temp.get(x) + 1;
            }
            temp.put(x, count);
        });
        //System.out.println(Arrays.toString(temp.entrySet().toArray()));
        return temp.entrySet().stream().mapToLong(e -> (long) (this.rows - e.getKey()) * e.getValue()).sum();
    }

    private void tiltNorth() {
        this.rocks.forEach(e -> {
            int x = e.x;
            while (x - 1 >= 0) {
                if (this.input[x - 1][e.y] == '#' || this.input[x - 1][e.y] == 'O') {
                    break;
                } else {
                    char c = this.input[e.x][e.y];
                    this.input[e.x][e.y] = this.input[e.x - 1][e.y];
                    this.input[e.x - 1][e.y] = c;
                    e.x = x - 1;
                }
                x--;
            }
        });
    }

    public long solve() {
        return this.totalLoadOfRocks;
    }
}
