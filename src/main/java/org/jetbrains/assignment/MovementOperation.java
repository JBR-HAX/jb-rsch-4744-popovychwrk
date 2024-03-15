package org.jetbrains.assignment;

public class MovementOperation {
    private String direction;
    private int steps;

    public MovementOperation(String direction, int steps) {
        this.direction = direction;
        this.steps = steps;
    }

    public String getDirection() {
        return direction;
    }

    public int getSteps() {
        return steps;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public String toString() {
        return "Direction: " + direction + ", Steps: " + steps;
    }
}
