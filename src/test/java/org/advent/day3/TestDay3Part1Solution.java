package org.advent.day3;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TestDay3Part1Solution {

    Day3Part1Solution solution;
    @Test
    void test_solution() throws IOException, URISyntaxException {
//        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
//        InputStream is = classloader.getResourceAsStream("day3-input-sample.txt");
//        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
//        BufferedReader reader = new BufferedReader(streamReader);
        solution = new Day3Part1Solution("day3-input-sample.txt");
        assertThat(solution.sumOfPartNumbers(), equalTo(4361));
    }

    @Test
    void test_solution_puzzle_input() throws IOException, URISyntaxException {
        solution = new Day3Part1Solution("day3-input.txt");
        assertThat(solution.sumOfPartNumbers(), equalTo(4361));
    }
}
