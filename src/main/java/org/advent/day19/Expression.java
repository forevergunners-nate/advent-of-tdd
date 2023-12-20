package org.advent.day19;

public class Expression {
    public static final String OPERATOR_GREATER_THAN = ">";
    public static final String OPERATOR_LESS_THAN = "<";

    private String partCategory;
    private String operator;
    private long threshold;
    private String positiveOutcome;
    private String negativeOutcome;

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

    public String getPositiveOutcome() {
        return positiveOutcome;
    }

    public void setPositiveOutcome(String positiveOutcome) {
        this.positiveOutcome = positiveOutcome;
    }

    public String getNegativeOutcome() {
        return negativeOutcome;
    }

    public void setNegativeOutcome(String negativeOutcome) {
        this.negativeOutcome = negativeOutcome;
    }

    public Expression(String partCategory, String operator, long threshold, String positiveOutcome, String negativeOutcome) {
        this.partCategory = partCategory;
        this.operator = operator;
        this.threshold = threshold;
        this.positiveOutcome = positiveOutcome;
        this.negativeOutcome = negativeOutcome;
    }

    public String evaluate(Rating rating) {
        if (this.operator == OPERATOR_GREATER_THAN) {
            if (rating.value() > this.threshold) {
                return this.positiveOutcome;
            } else {
                return this.negativeOutcome;
            }
        }
        if (rating.value() < this.threshold) {
            return this.positiveOutcome;
        }
        return this.negativeOutcome;
    }

    @Override
    public String toString() {
        return "Expression{" +
                "partCategory='" + partCategory + '\'' +
                ", operator='" + operator + '\'' +
                ", threshold=" + threshold +
                ", positiveOutcome='" + positiveOutcome + '\'' +
                ", negativeOutcome='" + negativeOutcome + '\'' +
                '}';
    }
}
