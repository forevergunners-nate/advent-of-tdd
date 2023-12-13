package org.advent.day13;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TestDay13Part1Solution {

    Day13Part1Solution solution;
    @Test
    void a_test_solution() throws IOException, URISyntaxException {
        solution = new Day13Part1Solution("day13/day13-part1-input-sample.txt");
        assertThat(solution.solve(), equalTo(405L));
    }

    @Test
    void b_test_solution_puzzle_input() throws IOException, URISyntaxException {
        solution = new Day13Part1Solution("day13/day13-part1-input.txt");
        assertThat(solution.solve(), equalTo(405L));
    }
}
