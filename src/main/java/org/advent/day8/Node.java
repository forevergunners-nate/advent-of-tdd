package org.advent.day8;

public class Node {
    private String Label;
    private Node left;
    private Node right;

    public Node(String label) {
        Label = label;
        this.left = null;
        this.right = null;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
