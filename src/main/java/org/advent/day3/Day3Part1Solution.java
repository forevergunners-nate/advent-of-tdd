package org.advent.day3;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day3Part1Solution {
    private List<Integer> partNumbers;
    private char[][] inputMatrix;
    public Day3Part1Solution(String filePath) throws IOException, URISyntaxException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        Path path = Paths.get(classloader.getResource(filePath).toURI());
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        int rows = lines.size();
        this.inputMatrix = new char[rows][];
        this.partNumbers = new ArrayList<>();

        int i = 0;
        for (String line : lines) {
            char[] charArray = line.toCharArray();
            this.inputMatrix[i] = charArray;
            i++;
        }
        System.out.println("this.inputMatrix.length:" + this.inputMatrix.length);
        System.out.println("this.inputMatrix[0].length:" + this.inputMatrix[0].length);
        System.out.println(Arrays.deepToString(inputMatrix));
        int currentNumber = 0;
        boolean isValidPartNumber = false;
        for (int j = 0; j < this.inputMatrix.length; j++) {
            for (int k = 0; k < this.inputMatrix[j].length; k++) {
                if (!Character.isDigit(this.inputMatrix[j][k])) {
                    continue;
                }
                currentNumber = currentNumber * 10 + Character.getNumericValue(inputMatrix[j][k]);
                if (symbolInNeighbors(j, k, this.inputMatrix.length, this.inputMatrix[j].length)) {
                    isValidPartNumber = true;
                }
                int nextK = k + 1;
                if ( (nextK >= this.inputMatrix[j].length) ||
                        (nextK < this.inputMatrix[j].length && isSymbol(this.inputMatrix[j][nextK])) ||
                        (nextK < this.inputMatrix[j].length && this.inputMatrix[j][nextK] == '.')) {
                    if (isValidPartNumber) {
                        this.partNumbers.add(currentNumber);
                    }
                    currentNumber = 0;
                    isValidPartNumber = false;

                }
            }
        }

    }

    /**
     * | TL T TR | Both TL and TR can be DIGIT OR SYMBOL , T has to be a SYMBOL
     * | L  X R  | Both L and R can be DIGIT OR SYMBOL
     * | BL B BR | Both BL and BR can be DIGIT OR SYMBOL  , B has to be a SYMBOL
     * @param x
     * @param y
     * @return
     */
    private boolean symbolInNeighbors(int x, int y, int rows, int columns) {
        if (y + 1 < columns) {
            if (isSymbol(this.inputMatrix[x][y + 1])) {
                return true;
            }
        }
        if (y - 1 >= 0 ) {
            if (isSymbol(this.inputMatrix[x][y - 1])) {
                return true;
            }
        }
        if (x + 1 < rows) {
            if (y - 1 >= 0) {
                //TR - Top Right
                if (isSymbol(this.inputMatrix[x + 1][y - 1])) {
                    return true;
                }
            }
            if (isSymbol(this.inputMatrix[x + 1][y])){ // R - Right
                return true;
            }
            if (y + 1 < columns) {
                //BR - Bottom Right
                if (isSymbol(this.inputMatrix[x + 1][y + 1])) {
                    return true;
                }
            }
        }

        if (x - 1 >= 0 ) {
            if (y - 1 >= 0) {
                //TL - Top Left
                if (isSymbol(this.inputMatrix[x - 1][y - 1])) {
                    return true;
                }
            }
            if (isSymbol(this.inputMatrix[x - 1][y])) { // L - left
                return true;
            }
            if (y + 1 < columns) {
                //BL - Bottom Left
                if (isSymbol(this.inputMatrix[x - 1][y + 1])) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isSymbol(char c) {
        return !Character.isDigit(c) && c != '.';
    }
    public int sumOfPartNumbers() {
        return this.partNumbers.stream().mapToInt(Integer::intValue).sum();
    }

}
