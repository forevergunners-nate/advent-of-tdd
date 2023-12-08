package org.advent.day5;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyPair;
import java.util.*;
import java.util.List;

public class Day5Part2Solution {
    private Map<String, List<PlantSupply>> plantSupplies;

    private List<Day5Part2ValuePair> seedRanges;

    private List<BigInteger> locations;

    private BigInteger globalMin;
    private boolean firstGroup = true;

    public Day5Part2Solution(BufferedReader bufferedReader) {
        this.plantSupplies = new HashMap<>();
        this.seedRanges = new ArrayList<>();
        this.locations = new ArrayList<>();
        String currentType = null;
        try {
            for (String line; (line = bufferedReader.readLine()) != null; ) {
                if (line.isEmpty()) {
                    continue;
                }
                // seeds:
                if (line.contains("seeds: ")) {
                    String seedsString = line.split(":")[1].trim();
                    StringTokenizer stringTokenizer = new StringTokenizer(seedsString, " ");
                    while (stringTokenizer.hasMoreTokens()) {
                        BigInteger seedNumberStart = new BigInteger(stringTokenizer.nextToken().trim());
                        BigInteger seedNumberRange = new BigInteger(stringTokenizer.nextToken().trim());
                        this.seedRanges.add(new Day5Part2ValuePair(seedNumberStart, seedNumberRange));
                    }
                    continue;
                }
                if (line.contains("seed-to-soil")) {
                    currentType = "seed-to-soil";
                } else if (line.contains("soil-to-fertilizer")) {
                    currentType = "soil-to-fertilizer";
                } else if (line.contains("fertilizer-to-water")) {
                    currentType = "fertilizer-to-water";
                } else if (line.contains("water-to-light")) {
                    currentType = "water-to-light";
                } else if (line.contains("light-to-temperature")) {
                    currentType = "light-to-temperature";
                } else if (line.contains("temperature-to-humidity")) {
                    currentType = "temperature-to-humidity";
                } else if (line.contains("humidity-to-location")) {
                    currentType = "humidity-to-location";
                } else {
                    // Des  Seed Range
                    // 50 98 2
                    List<PlantSupply> currentSupplies = this.plantSupplies.getOrDefault(currentType, new ArrayList<>());
                    String[] numbers = line.trim().split(" ");
                    PlantSupply newPlantSupply = new PlantSupply(currentType, new BigInteger(numbers[1]), new BigInteger(numbers[0]), new BigInteger(numbers[2]));
                    currentSupplies.add(newPlantSupply);
                    this.plantSupplies.put(currentType, currentSupplies);
                }
            }
            for (Day5Part2ValuePair valuePair : this.seedRanges) {
                this.locations = new ArrayList<>();
                for (BigInteger i = valuePair.getStart(); i.compareTo(valuePair.getStart().add(valuePair.getRange())) < 0; i = i.add(BigInteger.ONE)) {
                    Seed seed = new Seed(i);
                    updateAttributes(seed);
                    this.locations.add(seed.getLocation());
                    if (this.locations.size() == 10000) {
                        if (this.firstGroup) {
                            this.globalMin = groupLowestLocationNumber();
                            firstGroup = false;
                        } else {
                            this.globalMin = this.globalMin.min(groupLowestLocationNumber());
                        }
                        this.locations = new ArrayList<>();
                    }
                }
                if (this.firstGroup) {
                    this.globalMin = groupLowestLocationNumber();
                    firstGroup = false;
                } else {
                    this.globalMin = this.globalMin.min(groupLowestLocationNumber());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void updateAttributes(Seed seed) {
        List<PlantSupply> supplies = null;
        //"seed-to-soil"
        boolean notFound = true;
        supplies = this.plantSupplies.get("seed-to-soil");
        for (PlantSupply supply : supplies) {
            if (supply.isWithinRange(seed.getSeed())) {
                seed.setSoil(supply.getDestination(seed.getSeed()));
                notFound = false;
                break;
            }
        }
        if (notFound) {
            seed.setSoil(seed.getSeed());
        }
        //"soil-to-fertilizer"
        notFound = true;
        supplies = this.plantSupplies.get("soil-to-fertilizer");
        for (PlantSupply supply : supplies) {
            if (supply.isWithinRange(seed.getSoil())) {
                seed.setFertilizer(supply.getDestination(seed.getSoil()));
                notFound = false;
                break;
            }
        }
        if (notFound) {
            seed.setFertilizer(seed.getSoil());
        }
        //"fertilizer-to-water"
        notFound = true;
        supplies = this.plantSupplies.get("fertilizer-to-water");
        for (PlantSupply supply : supplies) {
            if (supply.isWithinRange(seed.getFertilizer())) {
                seed.setWater(supply.getDestination(seed.getFertilizer()));
                notFound = false;
                break;
            }
        }
        if (notFound) {
            seed.setWater(seed.getFertilizer());
        }
        //"water-to-light"
        notFound = true;
        supplies = this.plantSupplies.get("water-to-light");
        for (PlantSupply supply : supplies) {
            if (supply.isWithinRange(seed.getWater())) {
                seed.setLight(supply.getDestination(seed.getWater()));
                notFound = false;
                break;
            }
        }
        if (notFound) {
            seed.setLight(seed.getWater());
        }
        //"light-to-temperature"
        notFound = true;
        supplies = this.plantSupplies.get("light-to-temperature");
        for (PlantSupply supply : supplies) {
            if (supply.isWithinRange(seed.getLight())) {
                seed.setTemperature(supply.getDestination(seed.getLight()));
                notFound = false;
                break;
            }
        }
        if (notFound) {
            seed.setTemperature(seed.getLight());
        }
        //"temperature-to-humidity"
        notFound = true;
        supplies = this.plantSupplies.get("temperature-to-humidity");
        for (PlantSupply supply : supplies) {
            if (supply.isWithinRange(seed.getTemperature())) {
                seed.setHumidity(supply.getDestination(seed.getTemperature()));
                notFound = false;
                break;
            }
        }
        if (notFound) {
            seed.setHumidity(seed.getTemperature());
        }
        //"humidity-to-location"
        notFound = true;
        supplies = this.plantSupplies.get("humidity-to-location");
        for (PlantSupply supply : supplies) {
            if (supply.isWithinRange(seed.getHumidity())) {
                seed.setLocation(supply.getDestination(seed.getHumidity()));
                notFound = false;
                break;
            }
        }
        if (notFound) {
            seed.setLocation(seed.getHumidity());
        }
    }

    private BigInteger groupLowestLocationNumber() {
        Optional<BigInteger> min = this.locations.stream().min(BigInteger::compareTo);
        return min.get();
    }
    public BigInteger lowestLocationNumber() {
        return this.globalMin;
    }
}
