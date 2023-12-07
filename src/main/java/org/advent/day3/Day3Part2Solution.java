package org.advent.day3;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

public class Day3Part2Solution {
    private List<Integer> gearRatios;

    private Map<Point, Integer> gearRatioMap;
    private char[][] inputMatrix;
    public Day3Part2Solution(String filePath) throws IOException, URISyntaxException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        Path path = Paths.get(classloader.getResource(filePath).toURI());
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        int rows = lines.size();
        this.inputMatrix = new char[rows][];
        this.gearRatios = new ArrayList<>();
        this.gearRatioMap = new HashMap<>();

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
        //boolean isValidPartNumber = false;
        Set<Point> starPOSs = new HashSet<>();
        for (int j = 0; j < this.inputMatrix.length; j++) {
            for (int k = 0; k < this.inputMatrix[j].length; k++) {
                if (!Character.isDigit(this.inputMatrix[j][k])) {
                    continue;
                }
                currentNumber = currentNumber * 10 + Character.getNumericValue(inputMatrix[j][k]);
                if (symbolInNeighbors(j, k, this.inputMatrix.length, this.inputMatrix[j].length)) {
                    //isValidPartNumber = true;
                    Point starPOS = getStarPOS(j, k, this.inputMatrix.length, this.inputMatrix[j].length);
                    starPOSs.add(starPOS);
                }
                int nextK = k + 1;
                if ( (nextK >= this.inputMatrix[j].length) ||
                        (nextK < this.inputMatrix[j].length && isSymbol(this.inputMatrix[j][nextK])) ||
                        (nextK < this.inputMatrix[j].length && this.inputMatrix[j][nextK] == '.') ||
                        (nextK < this.inputMatrix[j].length && isOtherSymbol(this.inputMatrix[j][nextK]))) {
                    if (!starPOSs.isEmpty()) {
                        System.out.println("Found Eligible number: " + currentNumber);
                        for (Point starPOS : starPOSs) {
                            System.out.println("Star Coordinate: " + starPOS);
                            if (this.gearRatioMap.containsKey(starPOS)) {
                                System.out.println("found StartPOS:" + starPOS + " for number:" + currentNumber + ", the other number is: " + this.gearRatioMap.get(starPOS));
                                this.gearRatios.add(currentNumber * this.gearRatioMap.get(starPOS));
                                this.gearRatioMap.clear();
                                starPOSs = new HashSet<>();
                            } else {
                                this.gearRatioMap.put(starPOS, currentNumber);
                            }
                        }
                    }
                    currentNumber = 0;
                    //isValidPartNumber = false;
                    starPOSs = new HashSet<>();

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

    private Point getStarPOS(int x, int y, int rows, int columns) {
        if (y + 1 < columns) {
            if (isSymbol(this.inputMatrix[x][y + 1])) {
                return new Point(x, y + 1);
            }
        }
        if (y - 1 >= 0 ) {
            if (isSymbol(this.inputMatrix[x][y - 1])) {
                return new Point(x, y - 1);
            }
        }
        if (x + 1 < rows) {
            if (y - 1 >= 0) {
                //TR - Top Right
                if (isSymbol(this.inputMatrix[x + 1][y - 1])) {
                    return new Point(x + 1, y - 1);
                }
            }
            if (isSymbol(this.inputMatrix[x + 1][y])){ // R - Right
                return new Point(x + 1, y);
            }
            if (y + 1 < columns) {
                //BR - Bottom Right
                if (isSymbol(this.inputMatrix[x + 1][y + 1])) {
                    return new Point(x + 1, y + 1);
                }
            }
        }

        if (x - 1 >= 0 ) {
            if (y - 1 >= 0) {
                //TL - Top Left
                if (isSymbol(this.inputMatrix[x - 1][y - 1])) {
                    return new Point(x - 1, y - 1);
                }
            }
            if (isSymbol(this.inputMatrix[x - 1][y])) { // L - left
                return new Point(x - 1, y);
            }
            if (y + 1 < columns) {
                //BL - Bottom Left
                if (isSymbol(this.inputMatrix[x - 1][y + 1])) {
                    return new Point(x - 1, y + 1);
                }
            }
        }
        return new Point(-1, -1);
    }

    private boolean isSymbol(char c) {
        return c == '*';
    }

    private boolean isOtherSymbol(char c) {
        return !Character.isDigit(c) && c != '.' && c != '*';
    }
    public int sumOfGearRatios() {
        return this.gearRatios.stream().mapToInt(Integer::intValue).sum();
    }


}
