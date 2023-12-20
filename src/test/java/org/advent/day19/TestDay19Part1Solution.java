package org.advent.day19;

import org.advent.day18.Day18Part1Solution;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TestDay19Part1Solution {

    Day19Part1Solution solution;
    @Test
    void a_test_solution() throws IOException, URISyntaxException {
        solution = new Day19Part1Solution("day19/day19-part1-input-sample.txt");
        assertThat(solution.solve(), equalTo(19114L));
    }

    @Test
    void b_test_solution_puzzle_input() throws IOException, URISyntaxException {
        solution = new Day19Part1Solution("day19/day19-part1-input.txt");
        assertThat(solution.solve(), equalTo(401674L));
    }
}
