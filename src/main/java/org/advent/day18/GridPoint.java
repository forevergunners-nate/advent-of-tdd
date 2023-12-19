package org.advent.day18;

import java.awt.*;

public class GridPoint extends Point implements Comparable<GridPoint> {

    public GridPoint(int x, int y) {
        super(x, y);
    }

    @Override
    public int compareTo(GridPoint o) {
        if (x == o.x) {
            return y - o.y;
        }
        return x - o.x;
    }

    @Override
    public String toString() {
        return "GridPoint{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
