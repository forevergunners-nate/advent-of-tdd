package org.advent.day19;

import java.util.Objects;

public class Expression {
    public static final String OPERATOR_GREATER_THAN = ">";
    public static final String OPERATOR_LESS_THAN = "<";

    private String partCategory;
    private String operator;
    private long threshold;
    private String outcome;
    public String getPartCategory() {
        return partCategory;
    }

    public void setPartCategory(String partCategory) {
        this.partCategory = partCategory;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public long getThreshold() {
        return threshold;
    }

    public void setThreshold(long threshold) {
        this.threshold = threshold;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public Expression(String partCategory, String operator, long threshold, String outcome) {
        this.partCategory = partCategory;
        this.operator = operator;
        this.threshold = threshold;
        this.outcome = outcome;
    }

    public String evaluate(Rating rating) {
        if (Objects.equals(this.operator, OPERATOR_GREATER_THAN)) {
            if (rating.value() > this.threshold) {
                return this.outcome;
            }
        }
        else if (rating.value() < this.threshold) {
            return this.outcome;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Expression{" +
                "partCategory='" + partCategory + '\'' +
                ", operator='" + operator + '\'' +
                ", threshold=" + threshold +
                ", outcome='" + outcome + '\'' +
                '}';
    }
}
