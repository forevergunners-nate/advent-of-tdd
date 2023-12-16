package org.advent.day15;

import org.advent.day14.Day14Part1Solution;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TestDay15Part1Solution {

    Day15Part1Solution solution;
    @Test
    void a_test_solution() throws IOException, URISyntaxException {
        solution = new Day15Part1Solution("day15/day15-part1-input-sample.txt");
        assertThat(solution.solve(), equalTo(1320L));
    }

    @Test
    void b_test_solution_puzzle_input() throws IOException, URISyntaxException {
        solution = new Day15Part1Solution("day15/day15-part1-input.txt");
        assertThat(solution.solve(), equalTo(1320L));
    }
}
