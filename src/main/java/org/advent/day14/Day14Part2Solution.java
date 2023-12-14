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

public class Day14Part2Solution {
    private long totalLoadOfRocks = 0;
    private char[][] input;
    private int rows;
    private int cols;
    public Day14Part2Solution(String filePath) throws IOException, URISyntaxException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        Path path = Paths.get(classloader.getResource(filePath).toURI());
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        //System.out.println("lines.size():" + lines.size());
        this.rows = lines.size();
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
            i++;
        }
        long spinCycles = 1000000000;
        long spins = 1;
        while (spins++ <= spinCycles) {
            tiltNorth();
            tiltWest();
            tiltSouth();
            tiltEast();
        }

        System.out.println(Arrays.deepToString(this.input));
        calculateSum();
    }

    private void calculateSum() {
        for (int x = 0; x < this.rows; x++) {
            for (int y = 0; y < this.cols; y++) {
                if (this.input[x][y] == 'O') {
                    this.totalLoadOfRocks += this.rows - x;
                }
            }
        }
    }

    private void tiltNorth() {
        for (int x = 1; x < this.rows; x++) {
            for (int y = 0; y < this.cols; y++) {
                if (this.input[x][y] == 'O') {
                    int currentX = x;
                    int current = x;
                    while (current - 1 >= 0) {
                        if (this.input[current - 1][y] == '#' || this.input[current- 1][y] == 'O') {
                            break;
                        } else {
                            char c = this.input[currentX][y];
                            this.input[currentX][y] = this.input[current - 1][y];
                            this.input[current - 1][y] = c;
                            currentX = current - 1;
                        }
                        current--;
                    }
                }
            }
        }
    }

    private void tiltSouth() {
        for (int x = this.rows - 2; x >= 0; x--) {
            for (int y = 0; y < this.cols; y++) {
                if (this.input[x][y] == 'O') {
                    int currentX = x;
                    int current = x;
                    while (current + 1 < this.rows) {
                        if (this.input[current + 1][y] == '#' || this.input[current + 1][y] == 'O') {
                            break;
                        } else {
                            char c = this.input[currentX][y];
                            this.input[currentX][y] = this.input[current + 1][y];
                            this.input[current + 1][y] = c;
                            currentX = current + 1;
                        }
                        current++;
                    }
                }
            }
        }
    }

    private void tiltWest() {
        for (int y = 1; y < this.cols; y++) {
            for (int x = 0; x < this.rows; x++) {
                if (this.input[x][y] == 'O') {
                    int currentY = y;
                    int current = y;
                    while (current - 1 >= 0) {
                        if (this.input[x][current - 1] == '#' || this.input[x][current - 1] == 'O') {
                            break;
                        } else {
                            char c = this.input[x][currentY];
                            this.input[x][currentY] = this.input[x][current - 1];
                            this.input[x][current - 1] = c;
                            currentY = current - 1;
                        }
                        current--;
                    }
                }
            }

        }
    }

    private void tiltEast() {
        for (int y = this.cols - 2; y >= 0; y--) {
            for (int x = 0; x < this.rows; x++) {
                if (this.input[x][y] == 'O') {
                    int currentY = y;
                    int current = y;
                    while (current + 1 < this.cols) {
                        if (this.input[x][current + 1] == '#' || this.input[x][current + 1] == 'O') {
                            break;
                        } else {
                            char c = this.input[x][currentY];
                            this.input[x][currentY] = this.input[x][current + 1];
                            this.input[x][current + 1] = c;
                            currentY = current + 1;
                        }
                        current++;
                    }
                }
            }

        }
    }


    public long solve() {
        return this.totalLoadOfRocks;
    }
}
