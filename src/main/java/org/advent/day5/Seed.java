package org.advent.day5;

import java.math.BigInteger;

public class Seed {
    private BigInteger seed;
    private BigInteger soil;
    private BigInteger fertilizer;
    private BigInteger water;
    private BigInteger light;
    private BigInteger temperature;
    private BigInteger humidity;
    private BigInteger location;

    public BigInteger getSeed() {
        return seed;
    }

    public BigInteger getSoil() {
        return soil;
    }

    public void setSoil(BigInteger soil) {
        this.soil = soil;
    }

    public BigInteger getFertilizer() {
        return fertilizer;
    }

    public void setFertilizer(BigInteger fertilizer) {
        this.fertilizer = fertilizer;
    }

    public BigInteger getWater() {
        return water;
    }

    public void setWater(BigInteger water) {
        this.water = water;
    }

    public BigInteger getLight() {
        return light;
    }

    public void setLight(BigInteger light) {
        this.light = light;
    }

    public BigInteger getTemperature() {
        return temperature;
    }

    public void setTemperature(BigInteger temperature) {
        this.temperature = temperature;
    }

    public BigInteger getHumidity() {
        return humidity;
    }

    public void setHumidity(BigInteger humidity) {
        this.humidity = humidity;
    }

    public BigInteger getLocation() {
        return location;
    }

    public void setLocation(BigInteger location) {
        this.location = location;
    }

    public void setSeed(BigInteger seed) {
        this.seed = seed;
    }

    public Seed(BigInteger seed) {
        this.seed = seed;
    }

    @Override
    public String toString() {
        return "Seed{" +
                "seed=" + seed +
                ", soil=" + soil +
                ", fertilizer=" + fertilizer +
                ", water=" + water +
                ", light=" + light +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", location=" + location +
                '}';
    }
}
