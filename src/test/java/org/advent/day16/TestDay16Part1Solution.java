package org.advent.day16;

import org.advent.day15.Day15Part1Solution;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TestDay16Part1Solution {

    Day16Part1Solution solution;
    @Test
    void a_test_solution() throws IOException, URISyntaxException {
        solution = new Day16Part1Solution("day16/day16-part1-input-sample.txt");
        assertThat(solution.solve(), equalTo(46L));
    }

    @Test
    void b_test_solution_puzzle_input() throws IOException, URISyntaxException {
        solution = new Day16Part1Solution("day16/day16-part1-input.txt");
        assertThat(solution.solve(), equalTo(46L));
    }
}
