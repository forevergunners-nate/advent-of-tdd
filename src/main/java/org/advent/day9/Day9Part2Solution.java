package org.advent.day9;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Day9Part2Solution {

    private List<Long> backRetrieves;
    public Day9Part2Solution(BufferedReader bufferedReader) {
        this.backRetrieves = new ArrayList<>();
        try {
            for (String line; (line = bufferedReader.readLine()) != null; ) {
                // puzzle input
                // 0 3 6 9 12 15
                String[] numStrings = line.trim().split(" ");
                SequenceValuePart2 current = null;
                SequenceValuePart2 prev = null;
                SequenceValuePart2 head = null;
                for (int i = 0; i <= numStrings.length - 1 ; i++) {
                    current = new SequenceValuePart2(Long.parseLong(numStrings[i].trim()), prev);
                    if (head == null) {
                        head = current;
                    }
                    prev = current;
                }
                this.backRetrieves.add(calculateForecastValue(current, head));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private long calculateForecastValue(SequenceValuePart2 root, SequenceValuePart2 head) {
        List<Long> diffs = new ArrayList<>();
        int zeros = 0;
        while (root != null && root.getPrev() != null) {
            long diff = root.getCurrent() - root.getPrev().getCurrent();
            diffs.add(diff);
            if (diff == 0L) {
                zeros++;
            }
            root = root.getPrev();
        }
        Collections.reverse(diffs);
        //System.out.println("Diffs: " + Arrays.toString(diffs.toArray()));
        if (zeros == diffs.size()) {
            //System.out.println("exit conditon - Diffs: " + Arrays.toString(diffs.toArray()));
            return head.getCurrent();
        }
        SequenceValuePart2 newCurrent = null;
        SequenceValuePart2 newPrev = null;
        SequenceValuePart2 newHead = null;
        for (int i = 0; i <= diffs.size() - 1; i++) {
            newCurrent = new SequenceValuePart2(diffs.get(i), newPrev);
            if (newHead == null) {
                newHead = newCurrent;
            }
            newPrev = newCurrent;
        }
        return head.getCurrent() - calculateForecastValue(newCurrent, newHead);
    }

    public long solve() {
        return this.backRetrieves.stream().mapToLong(Long::longValue).sum();
    }
}
