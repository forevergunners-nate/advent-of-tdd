package org.advent.day15;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Day15Part2Solution {
    private Map<Integer, Stack<Lens>> stores;
    public Day15Part2Solution(String filePath) throws IOException, URISyntaxException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        Path path = Paths.get(classloader.getResource(filePath).toURI());
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        this.stores = new HashMap<>();
        //System.out.println("lines.size():" + lines.size());
        int i = 0;
        for (String line : lines) {
            //System.out.println("Line:" + line);
            // puzzle input

            char[] lineArray = line.toCharArray();
            StringBuilder stringBuilder = new StringBuilder();
            for (char c : lineArray) {
                if(c != ',') {
                    stringBuilder.append(c);
                } else {
                    String step = stringBuilder.toString();
                    stringBuilder = new StringBuilder();
                    process(step);
                    //System.out.println(Arrays.toString(this.stores.entrySet().toArray()));
                }
            }
            process(stringBuilder.toString());
            //System.out.println(Arrays.toString(this.stores.entrySet().toArray()));
            i++;
        }

    }

    private void process(String step) {
        //
        //System.out.println("Step:" + step);
        String[] params;
        boolean addFlag = false;
        if (step.contains("=")) {
            params = step.split("=");
            addFlag = true;
        } else {
            params = step.split("-");
        }
        String label = params[0];
        int hashValue = getHashValue(params[0]);
        int focalLength = 0;
        if (addFlag) {
            focalLength = Integer.parseInt(params[1]);
        }
        Lens lens = new Lens(label, focalLength);
        Stack<Lens> stack = this.stores.computeIfAbsent(hashValue, key -> new Stack<Lens>());
        if (addFlag) {
            if (!stack.contains(lens)) {
                stack.push(lens);
                //System.out.println("Added " + lens.toString());
            } else {
                int index = stack.search(lens);
                stack.set(stack.size() - index, lens);
                //System.out.println("Replaced index " + (stack.size() - index) + " with " + lens.toString());

            }
        } else {
            if (stack.contains(lens)) {
                stack.remove(lens);
                //System.out.println("Removed: " + lens);
            }
        }
    }

    private int getHashValue(String tobeHashed) {
        int tmp = 0;
        for (char c : tobeHashed.toCharArray()) {
            tmp += (int)c;
            tmp *= 17;
            tmp = tmp % 256;
        }
        return tmp;
    }

    public long solve() {
        return this.stores.entrySet().stream().filter(e -> !e.getValue().isEmpty()).mapToLong(e -> {
            long subSum = 0;
            long boxNum = e.getKey() + 1L;
            Stack<Lens> lense = e.getValue();
            int i = lense.size();
            while (!lense.isEmpty()) {
                Lens lens = lense.pop();
                subSum += boxNum * i * lens.length();
                i--;
            }
            return subSum;
        }).sum();
    }
}
