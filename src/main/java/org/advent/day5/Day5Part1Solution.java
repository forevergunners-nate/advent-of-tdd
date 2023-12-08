package org.advent.day5;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

public class Day5Part1Solution {
    private Map<BigInteger, Seed> seeds;

    private Map<String, List<PlantSupply>> plantSupplies;

    public Day5Part1Solution(BufferedReader bufferedReader) {
        this.seeds = new HashMap<>();
        this.plantSupplies = new HashMap<>();
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
                        BigInteger seedNumber = new BigInteger(stringTokenizer.nextToken().trim());
                        this.seeds.put(seedNumber, new Seed(seedNumber));
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
            System.out.println(Arrays.deepToString(this.plantSupplies.values().toArray()));
            for (Seed seed : this.seeds.values()) {
                updateAttributes(seed);
            }
            System.out.println(Arrays.deepToString(this.seeds.values().toArray()));
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

    public BigInteger lowestLocationNumber() {
        Optional<BigInteger> min = this.seeds.values().stream().map(Seed::getLocation).min(BigInteger::compareTo);
        return min.get();
    }
}
