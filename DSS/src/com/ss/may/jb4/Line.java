package com.ss.may.jb4;

/**
 * @author Pramod, taken from video
 *
 */
public class Line {

    public Line(double x0, double y0, double x1, double y1) {
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
    }

    // returns slope of the line
    public double getSlope() {
        // avoid dividing by 0
        if (x1 == x0) {
            throw new ArithmeticException();
        }

        return (y1 - y0) / (x1 - x0);
    }

    // returns length of the line
    public double getDistance() {
        return Math.sqrt((x1 - x0) * (x1 - x0) + (y1 - y0) * (y1 - y0));
    }

    // returns whether 2 lines are parallel
    public boolean parallelTo(Line l) {
        // if diff is very small, consider the lines parallel
        if (Math.abs(getSlope() - l.getSlope()) < .0001) {
            return true;
        } else {
            return false;
        }
    }

    private double x0, y0, x1, y1;

}