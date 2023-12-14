package org.advent.day14;

import org.advent.day13.Day13Part1Solution;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TestDay14Part1Solution {

    Day14Part1Solution solution;
    @Test
    void a_test_solution() throws IOException, URISyntaxException {
        solution = new Day14Part1Solution("day14/day14-part1-input-sample.txt");
        assertThat(solution.solve(), equalTo(136L));
    }

    @Test
    void b_test_solution_puzzle_input() throws IOException, URISyntaxException {
        solution = new Day14Part1Solution("day14/day14-part1-input.txt");
        assertThat(solution.solve(), equalTo(405L));
    }
}
