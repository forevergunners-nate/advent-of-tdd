package org.advent.day21;

import java.awt.*;

public class GardenerPosition {
    private int x;
    private int y;
    private long steps;

    public GardenerPosition(int x, int y, long steps) {
        this.x = x;
        this.y = y;
        this.steps = steps;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public long getSteps() {
        return steps;
    }

    public void setSteps(long steps) {
        this.steps = steps;
    }

    public Point getPointObject() {
        return new Point(x, y);
    }
}
