package org.advent.day12;

import org.advent.day11.Day11Part1Solution;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TestDay12Part1Solution {

    Day12Part1Solution solution;
    @Test
    void a_test_solution() throws IOException, URISyntaxException {
        solution = new Day12Part1Solution("day12/day12-part1-input-sample.txt");
//        solution = new Day12Part1Solution("day12/day12-part1-input-1line.txt");
        assertThat(solution.solve(), equalTo(21));
    }

    @Test
    void b_test_solution_puzzle_input() throws IOException, URISyntaxException {
        solution = new Day12Part1Solution("day12/day12-part1-input.txt");
        assertThat(solution.solve(), equalTo(21));
    }
}
