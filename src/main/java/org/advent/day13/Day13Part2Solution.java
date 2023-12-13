package org.advent.day13;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day13Part2Solution {
    public long patternNotesSum = 0;
    public Day13Part2Solution(String filePath) throws IOException, URISyntaxException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        Path path = Paths.get(classloader.getResource(filePath).toURI());
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        //System.out.println("lines.size():" + lines.size());
        int totalLines = lines.size();
        int i = 0;
        Map<Integer, String> horizontalStore = new HashMap<>();
        Map<Integer, String> verticalStore = new HashMap<>(); // rotated matrix
        for (int l = 0; l < totalLines; l++) {
            String line = lines.get(l);
            //System.out.println("Line:" + line);
            if (!line.isEmpty()) {
                // puzzle input
                // #.##..##.
                // ..#.##.#.
                // ##......#
                // ##......#
                // ..#.##.#.
                // ..##..##.
                // #.#.##.#.
                // horizontalStore
                String rec = horizontalStore.computeIfAbsent(i, key -> line);
                // verticalStore
                char[] lineArray = line.toCharArray();
                for (int j = 0; j < lineArray.length; j++) {
                    char c = lineArray[j];
                    if (verticalStore.containsKey(j)) {
                        String column = verticalStore.get(j);
                        column = column + String.valueOf(c);
                        verticalStore.put(j, column);
                    } else {
                        String column = String.valueOf(c);
                        verticalStore.put(j, column);

                    }
                }
                i++;
            }
            if (line.isEmpty() || l == totalLines - 1) {
                this.patternNotesSum += checkAndCalculateMatrix(horizontalStore, true);
                this.patternNotesSum += checkAndCalculateMatrix(verticalStore, false);
                // new matrix
                i = 0;
                horizontalStore = new HashMap<>();
                verticalStore = new HashMap<>();
            }
        }

    }

    private long checkAndCalculateMatrix(Map<Integer, String> store, boolean checkHorizontal) {
        int notes = calculate(store);

        if (checkHorizontal) {
            notes *= 100L;
        }
        return notes;
    }

    /**
     * 1. Find 2 continued rows that are the same or with 1 smudge - the mirror may start for there.
     * 2. Find all the mirror rows or rows with 1 or ZERO smudge from where #1 starts
     * 3. Till all hit the edges -> ceiling or bottom, return the up index in #1
     * @param store
     * @return
     */
    private int calculate(Map<Integer, String> store) {
        int length = store.size();
        for (int i = 1; i < length; i++) {
            String up = store.get(i - 1);
            String down = store.get(i);
            int mirrorSmudges = calculateSmudges(up, down);
            if (mirrorSmudges <= 1) {
                //System.out.println("Up and down indexes: " + (i - 1) + ", " + i);
                int matchedLines = Math.min(i - 1, length - i - 1); // max possible matches
                boolean matchContinues = true;
                boolean smudgeFound = false;
                for (int j = 0; j < matchedLines; j++) {
                    //
                    // i - j - 2  UpMatch when j = 0 -> i - 1 - 1 - j
                    // ------- -> i - 1
                    // ------- -> i
                    // i + j + 1  DownMatch when j = 0 -> i + 1 + j
                    //
                    String upMatch = store.get( i - j - 2);
                    String downMatch = store.get( i + j + 1);
                    int matchedRowsSmudges = calculateSmudges(upMatch, downMatch);
                    if (matchedRowsSmudges > 1) {
                        matchContinues = false;
                        break;
                    } else if (matchedRowsSmudges > 0 && mirrorSmudges > 0) {
                        matchContinues = false;
                        break;
                    } else if (matchedRowsSmudges > 0 && smudgeFound) {
                        matchContinues = false;
                        break;
                    } else if (matchedRowsSmudges > 0 && !smudgeFound) {
                        smudgeFound = true;
                    }
                }
                if (matchContinues && !smudgeFound && mirrorSmudges == 1) {
                    return i;
                }
                if (matchContinues && smudgeFound && mirrorSmudges == 0 ) {
                    return i;
                }
            }
        }
        return 0;
    }

    private int calculateSmudges(String up, String down) {
        int smudgeCount = 0;
        for (int i = 0; i < up.length(); i++) {
            if (up.charAt(i) != down.charAt(i)) {
                smudgeCount++;
            }
        }
        return smudgeCount;
    }

    public long solve() {
        return this.patternNotesSum;
    }

}
