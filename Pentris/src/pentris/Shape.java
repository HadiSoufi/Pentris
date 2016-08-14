package pentris;

import java.awt.Color;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Hadi Soufi
 * @version 1 Program {number}: {name} CSC230-02 Spring 2016
 */
public class Shape {

    /**
     * The x coordinate of the shape
     */
    int x = 0;

    /**
     * The y coordinate of the shape
     */
    int y = 0;

    /**
     * The number of points in the shape
     */
    private final int SIZE;

    /**
     * The set that stores the points of the shape
     */
    private final Set<Point> SHAPE;

    /**
     * Initialize a shape of arbitrary size
     *
     * @param size the number of points in the shape
     */
    public Shape(int size) {
        SIZE = size;
        SHAPE = new HashSet<>(size);
    }

    /**
     * Initialize a shape with a given size and set of coordinates
     *
     * @param x the x position
     * @param y the y position
     * @param size the number of points in the shape
     */
    public Shape(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.SIZE = size;
        SHAPE = new HashSet<>(size);
    }

    /**
     * Initialize a shape with a set and a pair of coordinates
     *
     * @param x the x position
     * @param y the y position
     * @param shape the original set
     */
    public Shape(int x, int y, Set<Point> shape) {
        this.x = x;
        this.y = y;

        SHAPE = new HashSet<>(shape.size());
        shape.stream().forEach((p) -> {
            SHAPE.add(new Point(p));
        });

        SIZE = SHAPE.size();
    }

    /**
     * Initializes a shape with different shape
     *
     * @param shape The base shape
     */
    public Shape(Shape shape) {
        this.x = shape.x;
        this.y = shape.y;

        SHAPE = new HashSet<>(shape.size());
        shape.points().stream().forEach((p) -> {
            SHAPE.add(new Point(p));
        });

        SIZE = SHAPE.size();
    }

    /**
     * Get the size of the shape
     *
     * @return the number of points in the shape
     */
    public int size() {
        return SIZE;
    }

    /**
     * Add a new point to the shape
     *
     * @param p a point
     */
    public void add(Point p) {
        SHAPE.add(p);
        p.x = this.x + p.x;
        p.y = this.y - p.y;
    }

    /**
     * Reduces the shapes y position by one
     */
    public void update() {
        y--;
        SHAPE.stream().forEach((p) -> {
            p.y--;
        });
    }

    /**
     * Moves the shape one unit along the x axis
     *
     * @param left If it's moving left or not
     */
    public void addX(boolean left) {
        int dir = (left) ? -1 : 1;
        this.x += dir;
        SHAPE.stream().forEach((p) -> {
            p.x += dir;
        });
    }

    /**
     * Rotates the shape 90 degrees multiplied by the parameter
     *
     * @param times the number of times to rotate
     */
    public void rotate(int times) {
        for (int i = 0; i < times; i++) {
            rotate();
        }
    }

    /**
     * Rotates the shape 90 degrees
     */
    public void rotate() {
        int oldX;

        for (Point p : SHAPE) {
            oldX = this.x - p.x;
            p.x = -(this.y - p.y);
            p.y = -oldX;
        }

        SHAPE.stream().map((p) -> {
            p.x = this.x + p.x;
            return p;
        }).forEach((p) -> {
            p.y = this.y - p.y;
        });
    }

    /**
     * Mirrors the shape over it's y axis
     *
     * @return the shape after mirroring
     */
    public Shape mirror() {
        SHAPE.stream().forEach((Point p) -> {
            p.x = -(this.x - p.x);
        });

        SHAPE.stream().forEach((Point p) -> {
            p.x = this.x + p.x;
        });
        return this;
    }

    /**
     * Get the shape in the form of a set
     *
     * @return a set that stores all the points in the shape
     */
    public Set<Point> points() {
        return new HashSet<>(SHAPE);
    }

    /**
     * Sets the color of the shape to a given color
     *
     * @param c the color of the shape
     * @return this shape
     */
    public Shape setColor(Color c) {
        SHAPE.stream().forEach((Point p) -> {
            p.color = c;
        });
        return this;
    }

    /**
     * Checks if the shape includes a point
     *
     * @param point The point to check
     * @return If the shape contains the point
     */
    public boolean contains(Point point) {
        return SHAPE.stream().anyMatch((p) -> (p == point));
    }

    /**
     * Makes the shape a stationary shape
     */
    public void makeStationary() {
        SHAPE.stream().forEach((p) -> {
            p.makeStationary();
        });
    }

    @Override
    public String toString() {
        String str = "Shape: ";

        str = SHAPE.stream().map((p) -> p.toString() + "; \n       ").reduce(str, String::concat);

        return str;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Shape other = (Shape) obj;
        if (this.hashCode() != other.hashCode()) {
            return false;
        }
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        if (this.SIZE != other.SIZE) {
            return false;
        }
        return Objects.equals(this.SHAPE, other.SHAPE);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.x;
        hash = 47 * hash + this.y;
        hash = 47 * hash + this.SIZE;
        hash = 47 * hash + Objects.hashCode(this.SHAPE);
        return hash;
    }
}
