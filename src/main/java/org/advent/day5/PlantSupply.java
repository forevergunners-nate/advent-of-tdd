package org.advent.day5;

import java.math.BigInteger;

public class PlantSupply {
    private String type;
    private BigInteger sourceStart;
    private BigInteger sourceEnd;
    private BigInteger destinationStart;
    private BigInteger destinationEnd;
    private BigInteger range;

    public PlantSupply(String type, BigInteger sourceStart, BigInteger destinationStart, BigInteger range) {
        this.type = type;
        this.range = range;
        this.sourceStart = sourceStart;
        this.sourceEnd = sourceStart.add(range).add(BigInteger.valueOf(-1));
        this.destinationStart = destinationStart;
        this.destinationEnd = destinationStart.add(range).add(BigInteger.valueOf(-1));
    }

    public boolean isWithinRange(BigInteger input) {
        return (input.compareTo(this.sourceStart) == 0 || input.compareTo(this.sourceStart) > 0)
                && (input.compareTo(this.sourceEnd) == 0 || input.compareTo(this.sourceEnd) < 0);
    }

    public BigInteger getDestination(BigInteger input) {
        return this.destinationStart.add(input.add(this.sourceStart.negate()));
    }

    @Override
    public String toString() {
        return "PlantSupply{" +
                "type='" + type + '\'' +
                ", sourceStart=" + sourceStart +
                ", sourceEnd=" + sourceEnd +
                ", destinationStart=" + destinationStart +
                ", destinationEnd=" + destinationEnd +
                ", range=" + range +
                '}';
    }
}
