package org.advent.day6;

public class RaceRecord {
    private long time;
    private long distance;

    private long waysToBeat;

    public RaceRecord(long time, long distance) {
        this.time = time;
        this.distance = distance;
        this.waysToBeat = 0;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    public long getWaysToBeat() {
        return waysToBeat;
    }

    public void setWaysToBeat(long waysToBeat) {
        this.waysToBeat = waysToBeat;
    }

    @Override
    public String toString() {
        return "RaceRecord{" +
                "time=" + time +
                ", distance=" + distance +
                ", waysToBeat=" + waysToBeat +
                '}';
    }
}
