package org.advent.day10;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GridPoint {
    private char label;
    private Point coordinate;
    private List<GridPoint> neighbors;
    private boolean isVisited;

    public GridPoint(Point coordinate) {
        this.coordinate = coordinate;
        this.neighbors = new ArrayList<>();
        this.isVisited = false;
    }

    public char getLabel() {
        return label;
    }

    public void setLabel(char label) {
        this.label = label;
    }

    public Point getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Point coordinate) {
        this.coordinate = coordinate;
    }

    public List<GridPoint> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(List<GridPoint> neighbors) {
        this.neighbors = neighbors;
    }

    public void addNeighbor(GridPoint neighbor) {
        this.neighbors.add(neighbor);
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    @Override
    public String toString() {
        return "GridPoint{" +
                "label=" + label +
                ", isVisited=" + isVisited +
                ", coordinate=" + coordinate +
                ", neighbors=" + Arrays.toString(neighbors.toArray()) +
                '}';
    }
}
