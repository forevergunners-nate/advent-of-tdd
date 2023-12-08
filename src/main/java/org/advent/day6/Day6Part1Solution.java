package org.advent.day6;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class Day6Part1Solution {

    private List<RaceRecord> raceRecords;
    public Day6Part1Solution(BufferedReader bufferedReader) {
        this.raceRecords = new ArrayList<>();
        try {
            for (String line; (line = bufferedReader.readLine()) != null; ) {
                // Race records:
                if (line.contains("Time: ")) {
                    String string = line.split(":")[1].trim();
                    StringTokenizer stringTokenizer = new StringTokenizer(string, " ");
                    while (stringTokenizer.hasMoreTokens()) {
                        RaceRecord raceRecord = new RaceRecord(Long.parseLong(stringTokenizer.nextToken().trim()), 0);
                        this.raceRecords.add(raceRecord);
                    }
                } else {
                    String string = line.split(":")[1].trim();
                    StringTokenizer stringTokenizer = new StringTokenizer(string, " ");
                    int i = 0;
                    while (stringTokenizer.hasMoreTokens() && i < this.raceRecords.size()) {
                        this.raceRecords.get(i).setDistance(Long.parseLong(stringTokenizer.nextToken().trim()));
                        i++;
                    }
                }
            }
            System.out.println("Before: " + Arrays.toString(this.raceRecords.toArray()));
            for (RaceRecord raceRecord : this.raceRecords) {
                calculateNumberOfWaysToBeat(raceRecord);
            }
            System.out.println("After: " + Arrays.toString(this.raceRecords.toArray()));
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
        return this.raceRecords.stream().mapToLong(RaceRecord::getWaysToBeat).reduce(1, (a, b) -> a * b);
    }
}
