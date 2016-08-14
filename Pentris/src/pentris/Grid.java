package pentris;

/**
 * @author Hadi Soufi
 * @version 1 Program {number}: {name} CSC230-02 Spring 2016
 */
public class Grid {

    /**
     * The width of the grid
     */
    private final int WIDTH;

    /**
     * The height of the grid
     */
    private final int HEIGHT;

    /**
     * The array that represents the grid
     */
    private final Point[][] GRID;

    /**
     * The shape object on the grid
     */
    private Shape currentShape;

    /**
     * The x position of the grid
     */
    private final int X;

    /**
     * The y position of the grid
     */
    private final int Y;

    /**
     * Initializes the grid
     *
     * @param width The width
     * @param height The height
     * @param x The x position
     * @param y The y position
     * @param shape The initial shape
     */
    public Grid(int width, int height, int x, int y, Shape shape) {
        WIDTH = width;
        HEIGHT = height;
        X = x;
        Y = y;
        currentShape = new Shape(shape);

        GRID = new Point[HEIGHT][WIDTH];
        clearBoard();
    }

    /**
     * Get the width of the grid
     *
     * @return The width
     */
    public int getWidth() {
        return WIDTH;
    }

    /**
     * Get the height of the grid
     *
     * @return The height
     */
    public int getHeight() {
        return HEIGHT;
    }

    /**
     * Get the x position of the grid
     *
     * @return The x position
     */
    public int getX() {
        return X;
    }

    /**
     * Get the y position of the grid
     *
     * @return The y position
     */
    public int getY() {
        return Y;
    }

    /**
     * Get the grid's current shape
     *
     * @return The current shape
     */
    public Shape getCurrentShape() {
        return currentShape;
    }

    /**
     * Sets the current shape to a new shape
     *
     * @param s The new shape
     */
    public void setCurrentShape(Shape s) {
        currentShape = new Shape(s);
    }

    /**
     * Gets the point at a given position
     *
     * @param y The y position
     * @param x The x position
     * @return The point
     */
    public Point getPoint(int y, int x) {
        return GRID[y][x];
    }

    /**
     * Sets a point at a given position to a new point
     *
     * @param y The y position
     * @param x The x position
     * @param p The new point
     */
    public void setPoint(int y, int x, Point p) {
        GRID[y][x] = new Point(p);
    }

    /**
     * Sets all values on the board to empty points
     */
    public final void clearBoard() {
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                GRID[y][x] = new Point();
            }
        }
    }

    /**
     * Draws the shape
     *
     * @param blank Draw the shape with blank spaces or not
     */
    public void drawShape(boolean blank) {
        currentShape.points().stream().forEach((p) -> {
            if (p.y < getHeight()) {
                GRID[p.y][p.x] = blank ? new Point() : p;
            }
        });
    }

    /**
     * Generic update method, should be overridden by child classes
     */
    public void update() {
        drawShape(true);
        currentShape.update();
        drawShape(false);
    }
}
