package org.advent.day10;

import java.awt.Point;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

public class Day10Part1Solution {
    private Map<Point, GridPoint> gridPointMap;
    //private boolean[][] travelHistory;
    private Point startPointCoordinate;
    private int rows;

    private long maxDistance = Long.MIN_VALUE;
    public Day10Part1Solution(String filePath) throws IOException, URISyntaxException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        Path path = Paths.get(classloader.getResource(filePath).toURI());
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        this.rows = lines.size();
        //this.travelHistory = new boolean[rows][rows];
        this.gridPointMap = new HashMap<>();
        int i = 0;
        for (String line : lines) {
            // puzzle input
            // SJ.L7
            char[] lineArray = line.trim().toCharArray();
            for(int j = 0; j < lineArray.length; j++) {
                char c = lineArray[j];
                if (c == '.') {
                    //this.travelHistory[i][j] = true;
                    continue;
                }
                Point coordinate = new Point(i, j);
                GridPoint gridPoint = this.gridPointMap.computeIfAbsent(coordinate, GridPoint::new);
                gridPoint.setLabel(c);
                if (c == 'S') {
                    this.startPointCoordinate = coordinate;
                }
            }
            i++;
        }
//        GridPoint start = this.gridPointMap.get(this.startPointCoordinate);
//        calculateFarthestDistance(start, new ArrayList<>());

    }

    public long solve() { // BFS
        //For loop solution - find the close loop
        int closeLoopDistance = 0;
        GridPoint start = this.gridPointMap.get(this.startPointCoordinate);
        start.setVisited(true);
        Queue<GridPoint> queue = new LinkedList<>();
        queue.offer(start);
        while (!queue.isEmpty()) {
            closeLoopDistance++;
            GridPoint current = queue.poll();
            findAllReachableNeighbors(current);
            for (GridPoint next : current.getNeighbors()) {
                next.setVisited(true);
                queue.offer(next);
            }
        }

        return closeLoopDistance / 2; // -1 to take out when no neighbor found

//        return this.maxDistance;
    }

    /**
     * Recursion Version
     * @param point
     * @param path
     */
    private void calculateFarthestDistance(GridPoint point, List<GridPoint> path) {
        point.setVisited(true);
        path.add(point);
        findAllReachableNeighbors(point);
        System.out.println("after findAllReachableNeighbors: " + point);
        if (point.getNeighbors().isEmpty()) {
            this.maxDistance = Long.max(this.maxDistance, path.size());
        }


        for (GridPoint neighbor : point.getNeighbors()) {

            //this.travelHistory[currentX][currentY] = true;
            calculateFarthestDistance(neighbor, path);
            int n = path.size();
            path.remove(n - 1);
        }
    }

    private void findAllReachableNeighbors(GridPoint point) {
        int x = point.getCoordinate().x;
        int y = point.getCoordinate().y;
        int label = point.getLabel();
        if (label == 'S') {
            //Top
            if (x - 1 >= 0) {
                Point p = new Point(x - 1, y);
                if (this.gridPointMap.containsKey(p)) {
                    GridPoint gp = this.gridPointMap.get(p);
                    if (!gp.isVisited()) {
                        point.addNeighbor(gp);
                    }
                }
            }
            //Bottom
            if (x + 1 < this.rows) {
                Point p = new Point(x + 1, y);
                if (this.gridPointMap.containsKey(p)) {
                    GridPoint gp = this.gridPointMap.get(p);
                    if (!gp.isVisited()) {
                        point.addNeighbor(gp);
                    }
                }

            }
            //left
            if (y - 1 >= 0) {
                //if (!this.travelHistory[x][y - 1]) {
                Point p = new Point(x, y - 1);
                if (this.gridPointMap.containsKey(p)) {
                    GridPoint gp = this.gridPointMap.get(p);
                    if (!gp.isVisited()) {
                        point.addNeighbor(gp);
                    }
                }
            }
            //right
            if (y + 1 < this.rows) {
                //if (!this.travelHistory[x][y + 1]) {
                Point p = new Point(x, y + 1);
                if (this.gridPointMap.containsKey(p)) {
                    GridPoint gp = this.gridPointMap.get(p);
                    if (!gp.isVisited()) {
                        point.addNeighbor(gp);
                    }
                }

            }
        } else if (label == '|') {
            //Top
            if (x - 1 >= 0) {
                //if (!this.travelHistory[x - 1][y]) {
                Point p = new Point(x - 1, y);
                if (this.gridPointMap.containsKey(p)) {
                    GridPoint gp = this.gridPointMap.get(p);
                    if (!gp.isVisited()) {
                        point.addNeighbor(gp);
                    }
                }
            }
            //Bottom
            if (x + 1 < this.rows) {
                //if (!this.travelHistory[x + 1][y]) {
                Point p = new Point(x + 1, y);
                if (this.gridPointMap.containsKey(p)) {
                    GridPoint gp = this.gridPointMap.get(p);
                    if (!gp.isVisited()) {
                        point.addNeighbor(gp);
                    }
                }

            }
        }
        else if (label == '-') {
            //left
            if (y - 1 >= 0) {
                //if (!this.travelHistory[x][y - 1]) {
                Point p = new Point(x, y - 1);
                if (this.gridPointMap.containsKey(p)) {
                    GridPoint gp = this.gridPointMap.get(p);
                    if (!gp.isVisited()) {
                        point.addNeighbor(gp);
                    }
                }
            }
            //right
            if (y + 1 < this.rows) {
                //if (!this.travelHistory[x][y + 1]) {
                Point p = new Point(x, y + 1);
                if (this.gridPointMap.containsKey(p)) {
                    GridPoint gp = this.gridPointMap.get(p);
                    if (!gp.isVisited()) {
                        point.addNeighbor(gp);
                    }
                }

            }
        }
        else if (label == 'L') {
            //Top
            if(x - 1 >= 0) {
                //if (!this.travelHistory[x - 1][y]) {
                Point p = new Point(x - 1, y);
                if (this.gridPointMap.containsKey(p)) {
                    GridPoint gp = this.gridPointMap.get(p);
                    if (!gp.isVisited()) {
                        point.addNeighbor(gp);
                    }
                }

            }
            //right
            if(y + 1 < this.rows) {
                //if (!this.travelHistory[x][y + 1]) {
                Point p = new Point(x, y + 1);
                if (this.gridPointMap.containsKey(p)) {
                    GridPoint gp = this.gridPointMap.get(p);
                    if (!gp.isVisited()) {
                        point.addNeighbor(gp);
                    }
                }
            }
        }
        else if (label == 'J') {
            //Top
            if(x - 1 >= 0) {
                //if (!this.travelHistory[x - 1][y]) {
                Point p = new Point(x - 1, y);
                if (this.gridPointMap.containsKey(p)) {
                    GridPoint gp = this.gridPointMap.get(p);
                    if (!gp.isVisited()) {
                        point.addNeighbor(gp);
                    }
                }
            }
            //left
            if(y - 1 >= 0) {
                //if (!this.travelHistory[x][y - 1]) {
                Point p = new Point(x, y - 1);
                if (this.gridPointMap.containsKey(p)) {
                    GridPoint gp = this.gridPointMap.get(p);
                    if (!gp.isVisited()) {
                        point.addNeighbor(gp);
                    }
                }
            }
        }
        else if (label == '7') {
            //Bottom
            if (x + 1 < this.rows) {
                //if (!this.travelHistory[x + 1][y]) {
                Point p = new Point(x + 1, y);
                if (this.gridPointMap.containsKey(p)) {
                    GridPoint gp = this.gridPointMap.get(p);
                    if (!gp.isVisited()) {
                        point.addNeighbor(gp);
                    }
                }

            }
            //left
            if (y - 1 >= 0) {
                //if (!this.travelHistory[x][y - 1]) {
                Point p = new Point(x, y - 1);
                if (this.gridPointMap.containsKey(p)) {
                    GridPoint gp = this.gridPointMap.get(p);
                    if (!gp.isVisited()) {
                        point.addNeighbor(gp);
                    }
                }

            }
        }
        else if (label == 'F') {
            //Bottom
            if(x + 1 < this.rows) {
                //if (!this.travelHistory[x + 1][y]) {
                Point p = new Point(x + 1, y);
                if (this.gridPointMap.containsKey(p)) {
                    GridPoint gp = this.gridPointMap.get(p);
                    if (!gp.isVisited()) {
                        point.addNeighbor(gp);
                    }
                }

            }
            //right
            if(y + 1 < this.rows) {
                //if (!this.travelHistory[x][y + 1]) {
                Point p = new Point(x, y + 1);
                if (this.gridPointMap.containsKey(p)) {
                    GridPoint gp = this.gridPointMap.get(p);
                    if (!gp.isVisited()) {
                        point.addNeighbor(gp);
                    }
                }

            }
        }


    }

}
