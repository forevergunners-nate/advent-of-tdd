package org.advent.day5;

import java.math.BigInteger;

public class Day5Part2ValuePair {
    private BigInteger start;
    private BigInteger range;

    public Day5Part2ValuePair(BigInteger start, BigInteger range) {
        this.start = start;
        this.range = range;
    }

    public BigInteger getStart() {
        return start;
    }

    public void setStart(BigInteger start) {
        this.start = start;
    }

    public BigInteger getRange() {
        return range;
    }

    public void setRange(BigInteger range) {
        this.range = range;
    }
}
