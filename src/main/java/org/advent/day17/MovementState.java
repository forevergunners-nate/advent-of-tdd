package org.advent.day17;

public class MovementState implements Comparable<MovementState>{
    private MovementRecord mr;
    private long cost;

    public MovementState(MovementRecord mr, long cost) {
        this.mr = mr;
        this.cost = cost;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public MovementRecord getMr() {
        return mr;
    }

    public void setMr(MovementRecord mr) {
        this.mr = mr;
    }

    @Override
    public int compareTo(MovementState o) {
        long result = cost - o.cost;
        if (result == 0L && mr.direction() == o.getMr().direction()) {
            result = mr.steps() - o.getMr().steps();
        }
        if (result == 0L) {
            result = (o.getMr().x() - mr.x()) + (o.getMr().y() - mr.y());
        }
        return (int) result;
    }
}
