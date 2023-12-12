package org.advent.day12;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Queue;
import java.util.*;
import java.util.stream.Collectors;

public class Day12Part1Solution {
    public static final char UNKNOWN = '?';
    public static final char OPERATIONAL = '.';
    public static final char DAMAGED = '#';
    public static final char[] UNKNOWN_REPLACEMENTS = {OPERATIONAL, DAMAGED};
    public static final String UNKNOWN_REPLACEMENTS_STRING = ".#";
    private List<ArrayList<Character>> paths;
    private int rows;
    public Day12Part1Solution(String filePath) throws IOException, URISyntaxException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        Path path = Paths.get(classloader.getResource(filePath).toURI());
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        this.rows = lines.size();
        this.paths = new ArrayList<>();
        int i = 0;
        for (String line : lines) {
            // puzzle input
            // ?###???????? 3,2,1
            String[] input = line.split(" ");
            String conditionRecordString = input[0].trim();
            String contiguousGroupOfDamagedSpringString = input[1].trim();
            char[] conditionRecord = conditionRecordString.toCharArray();
            List<Integer> contiguousGroupOfDamagedSpring = new ArrayList<>();
            StringTokenizer stringTokenizer = new StringTokenizer(contiguousGroupOfDamagedSpringString,",");
            while (stringTokenizer.hasMoreTokens()) {
                contiguousGroupOfDamagedSpring.add(Integer.parseInt(stringTokenizer.nextToken()));
            }
            List<Character> subPath = new ArrayList<>();
            backTrack(0, conditionRecord, contiguousGroupOfDamagedSpring, subPath);
            i++;
        }
    }

    private void backTrack(int index, char[] conditionRecord, List<Integer> contiguousGroupOfDamagedSpring,
                           List<Character> subPath) {

        if (subPath.size() == conditionRecord.length) {
            if (isSubPathComplete(subPath, contiguousGroupOfDamagedSpring, conditionRecord)) {
                this.paths.add(new ArrayList<Character>(subPath));
            }
            return;
        }
        char currentC = conditionRecord[index];
        if (UNKNOWN == currentC) {
            for (int i = 0; i < UNKNOWN_REPLACEMENTS.length; i++) {
                char replacement = UNKNOWN_REPLACEMENTS[i];
                subPath.add(replacement);
                backTrack(index + 1, conditionRecord, contiguousGroupOfDamagedSpring, subPath);
                subPath.remove(subPath.size() - 1);
            }
        } else {
            subPath.add(currentC);
            backTrack(index + 1, conditionRecord, contiguousGroupOfDamagedSpring, subPath);
            subPath.remove(subPath.size() - 1);
        }
    }

    private boolean isSubPathComplete(List<Character> subPath, List<Integer> contiguousGroupOfDamagedSpring,
                                      char[] conditionRecord) {
        String subPathString = subPath.stream().map(String::valueOf).collect(Collectors.joining());
        subPathString = subPathString.replace(String.valueOf(OPERATIONAL), " ").replaceAll(" +", " ");
        String[] groups = subPathString.trim().split(" ");
        if (groups.length == contiguousGroupOfDamagedSpring.size()) {
            for (int i = 0; i < groups.length; i++) {
                if (groups[i].length() != contiguousGroupOfDamagedSpring.get(i)) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;

    }

    public int solve() {
        return this.paths.size();
    }

}
