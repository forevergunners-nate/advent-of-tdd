package org.advent.day11;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Queue;
import java.util.*;

public class Day11Part2Solution {
    private Map<Long, Point> galaxies;
    private char[][] input;
    private int rows;
    private int cols;
    private List<Long> shortestDistances;
    private long totalOfGalaxies;
    private long expandFactor;
    public Day11Part2Solution(String filePath, long expandFactor) throws IOException, URISyntaxException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        Path path = Paths.get(classloader.getResource(filePath).toURI());
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        this.rows = lines.size();
        this.galaxies = new HashMap<>();
        this.shortestDistances = new ArrayList<>();
        this.expandFactor = expandFactor;
        this.input = new char[this.rows][];
        int i = 0;
        for (String line : lines) {
            // puzzle input
            char[] lineArray = line.trim().toCharArray();
            this.input[i] = lineArray;
            this.cols = lineArray.length;
            for(int j = 0; j < lineArray.length; j++) {
                char c = lineArray[j];
                if (c == '.') {
                    continue;
                }
                if (c == '#') {
                    this.totalOfGalaxies++;
                    Point gridPoint = this.galaxies.getOrDefault(this.totalOfGalaxies, new Point(i ,j));
                    this.galaxies.put(this.totalOfGalaxies, gridPoint);
                }
            }
            i++;
        }
        expandUniverse();
        for (long k = 1; k <= this.galaxies.size() - 1; k++) {
            Point start = this.galaxies.get(k);
//            long[][] distances = bfs(start);
            for (long l = k + 1; l <= this.galaxies.size(); l++) {
                Point destination = this.galaxies.get(l);
                // Manhattan distance
                long diffX = Math.abs(start.x - destination.x);
                long diffY = Math.abs(start.y - destination.y);
                this.shortestDistances.add(diffX + diffY);
            }
        }
    }

    private void expandUniverse() {
        for (int i = this.cols - 1; i >= 0; i--) {
            boolean edgePointFound = false;
            for (Point point : this.galaxies.values()) {
                if(point.y == i){
                    edgePointFound = true;
                    break;
                }
            }
            if (edgePointFound) {
                continue; // skip for all edge points
            }
            for (Point point : this.galaxies.values()) {
                if(point.y >= i){
                    point.y += (int) (this.expandFactor - 1); // twice
                }
            }
        }

        for(int i = this.rows -1; i >= 0; i--){
            boolean edgePointFound = false;
            for (Point point : this.galaxies.values()) {
                if(point.x == i){
                    edgePointFound = true;
                    break;
                }
            }
            if (edgePointFound) {
                continue; // skip for all edge points
            }
            for (Point point : this.galaxies.values()) {
                if(point.x >= i){
                    point.x += (int) (this.expandFactor - 1); // twice
                }
            }
        }
    }
    private long[][] bfs(Point start){
        int []x = {0,0,1,-1};
        int []y = {1,-1,0,0};
        Queue<Point> q = new LinkedList<>();
        q.add(start);
        long[][] dist = new long[this.rows][this.cols];
        for(long[] a : dist){
            Arrays.fill(a,-1);
        }
        dist[start.x][start.y] = 0;
        while(!q.isEmpty()){
            Point p = q.poll();
            for(int i = 0; i < 4; i++){
                int a = p.x + x[i];
                int b = p.y + y[i];
                if(a >= 0 && b >= 0 && a < this.rows && b < this.cols && dist[a][b] == -1){
                    dist[a][b] = 1 + dist[p.x][p.y];
                    q.add(new Point(a,b));
                }
            }
        }
        return dist;
    }

    public long solve() {
        System.out.println("shortestDistances size: " + this.shortestDistances.size());
        return this.shortestDistances.stream().mapToLong(Long::longValue).sum();
    }

}
