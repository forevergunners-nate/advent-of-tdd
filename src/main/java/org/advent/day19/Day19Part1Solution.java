package org.advent.day19;

import org.advent.day18.GridPoint;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day19Part1Solution {
    private static final String ACCEPTED = "A";
    private static final String REJECTED = "R";
    private static final String ENTRY_WORKFLOW = "in";

    private Map<String, Workflow> workflowMap;
    private long total = 0L;

    public Day19Part1Solution(String filePath) throws IOException, URISyntaxException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        Path path = Paths.get(classloader.getResource(filePath).toURI());
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        this.workflowMap = new HashMap<>();
        boolean dataTypeSwitches = false;
        int i = 0;
        for (String line : lines) {
            if (line.isEmpty()) {
                dataTypeSwitches = true;
                continue;
            }
            if (!dataTypeSwitches) {
                // puzzle input
                // px{a<2006:qkq,m>2090:A,rfg}
                // read workflow data
                Workflow workflow = readWorkflowData(line);
            } else {
                List<Rating> ratings = readRatingData(line);
                if (processRatings(ratings)) {
                    // calculate
                    this.total += ratings.stream().mapToLong(Rating::value).sum();
                }
            }
            i++;
        }
    }

    private boolean processRatings(List<Rating> ratings) {
        Map<String, Rating> ratingMap = ratings.stream()
                .collect(Collectors.toMap(Rating::category, Function.identity()));

        String result = ENTRY_WORKFLOW;
        Workflow workflow;
        while (!(ACCEPTED.equals(result) || REJECTED.equals(result))) {
            workflow = this.workflowMap.get(result);
            String tmp = null;
            for (Expression expression : workflow.getExpressions()) {
                if (tmp != null) {
                    break;
                }
                String currentCategory = expression.getPartCategory();
                Rating rating = ratingMap.getOrDefault(currentCategory, null);
                if (null != rating) {
                    tmp = expression.evaluate(rating);
                }
            }
            result = tmp;
        }
        return result.equals(ACCEPTED);
    }



    private List<Rating> readRatingData(String line) {
        //{x=787,m=2655,a=1222,s=2876}
        String ratingsString = line.replace("{", "").replace("}", "");
        List<Rating> ratings = new ArrayList<>();
        StringTokenizer stringTokenizer = new StringTokenizer(ratingsString, ",");
        while (stringTokenizer.hasMoreTokens()) {
            String current = stringTokenizer.nextToken();
            String[] params = current.split("=");
            ratings.add(new Rating(params[0], Long.parseLong(params[1])));
        }
        return ratings;
    }

    private Workflow readWorkflowData(String line) {
        // px{a<2006:qkq,m>2090:A,rfg}
        String[] params = line.replace("}", "").split("\\{");
        String workflowName = params[0];
        Workflow workflow = this.workflowMap.computeIfAbsent(workflowName, k -> new Workflow());
        //a<2006:qkq,m>2090:A,rfg
        StringTokenizer stringTokenizer = new StringTokenizer(params[1], ",");
        Expression last = null;
        while (stringTokenizer.hasMoreTokens()) {
            String current = stringTokenizer.nextToken();
            if (current.contains(Expression.OPERATOR_GREATER_THAN) || current.contains(Expression.OPERATOR_LESS_THAN)) {
                String delimiter = current.contains(Expression.OPERATOR_GREATER_THAN) ? Expression.OPERATOR_GREATER_THAN : Expression.OPERATOR_LESS_THAN;
                String[] expressionParams = current.split(delimiter);
                String category = expressionParams[0];
                String[] conditionParams = expressionParams[1].split(":");
                long threshold = Long.parseLong(conditionParams[0]);
                String positiveOutcome = conditionParams[1];
                Expression newExpression = new Expression(category, delimiter, threshold, positiveOutcome, null);
                workflow.addExpression(newExpression);
                last = newExpression;
            } else {
                if (null != last) {
                    last.setNegativeOutcome(current);
                }
            }
        }
        return workflow;
    }
    public long solve() {
        return this.total;
    }
}
