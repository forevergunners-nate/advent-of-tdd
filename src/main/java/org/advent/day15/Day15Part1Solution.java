package org.advent.day15;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day15Part1Solution {
    private long sum = 0;
    public Day15Part1Solution(String filePath) throws IOException, URISyntaxException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        Path path = Paths.get(classloader.getResource(filePath).toURI());
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        //System.out.println("lines.size():" + lines.size());
        int i = 0;
        for (String line : lines) {
            //System.out.println("Line:" + line);
            // puzzle input

            char[] lineArray = line.toCharArray();
            long tmp = 0;
            for (char c : lineArray) {
                if(c != ',') {
                    tmp += (int)c;
                    tmp *= 17L;
                    tmp = tmp % 256;
                } else {
                    this.sum += tmp;
                    tmp = 0;
                }
            }
            this.sum += tmp;
            i++;
        }

    }


    public long solve() {
        return this.sum;
    }
}
