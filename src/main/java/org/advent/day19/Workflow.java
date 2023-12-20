package org.advent.day19;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Workflow {
    private List<Expression> expressions;

    public Workflow() {
        this.expressions = new ArrayList<>();
    }

    public List<Expression> getExpressions() {
        return this.expressions;
    }

    public void addExpression(Expression expression) {
        this.expressions.add(expression);
    }

    @Override
    public String toString() {
        return "Workflow{" +
                "expressions=" + expressions.toArray() +
                '}';
    }
}
