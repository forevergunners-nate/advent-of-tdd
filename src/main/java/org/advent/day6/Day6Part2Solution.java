package org.advent.day6;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Day6Part2Solution {

    private RaceRecord raceRecord;
    public Day6Part2Solution(BufferedReader bufferedReader) {
        try {
            for (String line; (line = bufferedReader.readLine()) != null; ) {
                // Race records:
                if (line.contains("Time: ")) {
                    String string = line.split(":")[1].trim();
                    raceRecord = new RaceRecord(Long.parseLong(string.trim()), 0);
                } else {
                    String string = line.split(":")[1].trim();
                    raceRecord.setDistance(Long.parseLong(string));
                }
            }

            System.out.println("Before: " + raceRecord);
            calculateNumberOfWaysToBeat(raceRecord);
            System.out.println("After: " + raceRecord);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void calculateNumberOfWaysToBeat(RaceRecord raceRecord) {
        long holdButtonTime = 0;
        long travelSpeed = 0;
        long travelTime = raceRecord.getTime();
        long historicBest = raceRecord.getDistance();
        while (travelTime - holdButtonTime > 0) {
            if ((travelTime - holdButtonTime) * travelSpeed > historicBest) {
                raceRecord.setWaysToBeat(raceRecord.getWaysToBeat() + 1);
            }
            holdButtonTime++;
            travelSpeed++;
        }
    }


    public long solve() {
        return this.raceRecord.getWaysToBeat();
    }
}
