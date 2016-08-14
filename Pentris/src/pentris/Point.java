package pentris;

import java.awt.Color;
import java.util.Objects;

/**
 * @author Hadi Soufi
 * @version 1 Program {number}: {name} CSC230-02 Spring 2016
 */
public class Point {

    /**
     * The x coordinate of the point
     */
    int x = 0;

    /**
     * The y coordinate of the point
     */
    int y = 0;

    /**
     * The color of the point
     */
    Color color = Color.BLACK;

    /**
     * If it's an empty point or not
     */
    private boolean isBlank = true;

    /**
     * If it's stationary
     */
    private boolean isStationary = false;

    /**
     * Initialize an empty point
     */
    public Point() {
    }

    /**
     * Initialize a point with user defined position and color
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @param color the color
     */
    public Point(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
        isBlank = false;
    }

    /**
     * Initialize a point with user defined position
     *
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        isBlank = false;
    }

    /**
     * Initialize a point using another point as a basis
     *
     * @param p the old point
     */
    public Point(Point p) {
        this.x = p.x;
        this.y = p.y;
        this.color = p.color;
        isBlank = p.isBlank;
    }

    /**
     * If the point has only been initialized with the default values
     *
     * @return whether it's an empty point or not
     */
    public boolean isBlank() {
        return isBlank;
    }

    public void makeStationary() {
        isStationary = true;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Point)) {
            return false;
        }
        if (!(obj.hashCode() == this.hashCode())) {
            return false;
        }
        Point other = (Point) obj;
        return this.x == other.x && this.y == other.y && this.color.equals(other.color) && this.isBlank == other.isBlank && this.isStationary == other.isStationary;
    }

    @Override
    public String toString() {
        return "Point: x = " + x + ", y = " + y + ", color = " + color.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.x;
        hash = 71 * hash + this.y;
        hash = 71 * hash + Objects.hashCode(this.color);
        hash = 71 * hash + (this.isBlank ? 1 : 0);
        hash = 71 * hash + (this.isStationary ? 1 : 0);
        return hash;
    }
}
