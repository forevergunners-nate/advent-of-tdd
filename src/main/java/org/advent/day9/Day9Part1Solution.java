package org.advent.day9;

import org.advent.day8.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class Day9Part1Solution {

    private List<Long> forecasts;
    public Day9Part1Solution(BufferedReader bufferedReader) {
        this.forecasts = new ArrayList<>();
        try {
            for (String line; (line = bufferedReader.readLine()) != null; ) {
                // puzzle input
                // 0 3 6 9 12 15
                String[] numStrings = line.trim().split(" ");
                SequenceValue current = null;
                SequenceValue last = null;
                SequenceValue tail = null;
                for (int i = numStrings.length - 1; i >= 0; i--) {
                    current = new SequenceValue(Long.parseLong(numStrings[i]), last);
                    if (tail == null) {
                        tail = current;
                    }
                    last = current;
                }
                this.forecasts.add(calculateForecastValue(current, tail));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private long calculateForecastValue(SequenceValue root, SequenceValue tail) {
        List<Long> diffs = new ArrayList<>();
        int zeros = 0;
        while (root != null && root.getNext() != null) {
            long diff = root.getNext().getCurrent() - root.getCurrent();
            diffs.add(diff);
            if (diff == 0L) {
                zeros++;
            }
            root = root.getNext();
        }
        if (zeros == diffs.size()) {
            return tail.getCurrent() + 0L;
        }
        SequenceValue newCurrent = null;
        SequenceValue newLast = null;
        SequenceValue newTail = null;
        for (int i = diffs.size() - 1; i >= 0; i--) {
            newCurrent = new SequenceValue(diffs.get(i), newLast);
            if (newLast == null) {
                newTail = newCurrent;
            }
            newLast = newCurrent;
        }
        return tail.getCurrent() + calculateForecastValue(newCurrent, newTail);
    }

    public long solve() {
        return this.forecasts.stream().mapToLong(Long::longValue).sum();
    }
}
