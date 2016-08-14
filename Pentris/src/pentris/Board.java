package pentris;

/**
 * @author Hadi Soufi
 */
public class Board extends Grid {

    /**
     * The next shape to drop
     */
    private Shape nextShape;

    /**
     * Whether or not the game is paused
     */
    private boolean paused = false;

    /**
     * The score
     */
    private int score = 0;

    /**
     * Initializes a new grid of size 12x24 at (0, 0) and a random shape. Then
     * initializes nextShape as a random shape, and drop the shape
     */
    public Board() {
        super(12, 24, 0, 0, ShapeGenerator.generateShape(6, 23));
        nextShape = ShapeGenerator.generateShape(6, 23);
        spawnShape();
    }

    /**
     * Set the current shape to the next shape, and set the next shape to a
     * random shape
     */
    private void spawnShape() {
        setCurrentShape(nextShape);
        nextShape = ShapeGenerator.generateShape(6, 23);
    }

    /**
     * Get the next shape
     *
     * @return The next shape
     */
    public Shape getNextShape() {
        return new Shape(nextShape);
    }

    /**
     * Pause the game
     */
    public void pause() {
        paused = true;
    }

    /**
     * Resume the game
     */
    public void play() {
        paused = false;
    }

    /**
     * Check if the game is paused
     *
     * @return True if paused, false if not
     */
    public boolean isPaused() {
        return paused;
    }

    /**
     * Get the score
     *
     * @return The score
     */
    public int score() {
        return score;
    }

    /**
     * Find any rows that are full and clear them
     */
    public void clearFullRows() {
        boolean foundRow = true;

        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                if (getPoint(y, x).isBlank()) {
                    foundRow = false;
                    break;
                }
            }
            if (foundRow) {
                score++;
                for (int j = y; j < getHeight() - 1; j++) {
                    for (int i = 0; i < getWidth(); i++) {
                        if (!getCurrentShape().contains(getPoint(j, i))) {
                            setPoint(j, i, getPoint(j + 1, i));
                        }
                    }
                }
                y--;
            }
            foundRow = true;
        }
    }

    @Override
    public void update() {
        atSide();
        if (!atBottom()) {
            drawShape(true);
            getCurrentShape().update();
        } else {
            getCurrentShape().makeStationary();
            getCurrentShape().points().stream().filter((p) -> (p.y >= getHeight())).forEach((_item) -> {
                clearBoard();
            });
            spawnShape();
            clearFullRows();
        }
        drawShape(false);
    }

    private void atSide() {
        getCurrentShape().points().stream().map((p) -> {
            while (p.x < 0) {
                getCurrentShape().addX(false);
            }
            return p;
        }).forEach((p) -> {
            while (p.x >= getWidth()) {
                getCurrentShape().addX(true);
            }
        });
    }

    private boolean atBottom() {
        Point pointBelow;
        for (Point p : getCurrentShape().points()) {
            pointBelow = (p.y < getHeight() && p.y - 1 >= 0) ? (getPoint(p.y - 1, p.x)) : new Point();
            if (!pointBelow.isBlank() && !getCurrentShape().contains(pointBelow)) {
                return true;
            }
        }
        return getCurrentShape().points().stream().anyMatch((p) -> (p.y == 0));
    }

    private boolean pointBeside(boolean left) {
        Point pointBeside;
        for (Point p : getCurrentShape().points()) {
            pointBeside = MathUtils.isBetween(0, getHeight(), p.y) && MathUtils.isBetween((left ? 1 : 0), getWidth() - (left ? 0 : 1), p.x) ? (getPoint(p.y, p.x + (left ? -1 : 1))) : new Point();
            if (!pointBeside.isBlank() && !getCurrentShape().contains(pointBeside)) {
                return true;
            }
        }
        return false;
    }

    public void moveShape(boolean left) {
        drawShape(true);
        if (!pointBeside(left)) {
            getCurrentShape().addX(left);
        }
        atSide();
        drawShape(false);
    }

    public void rotateShape() {
        drawShape(true);
        getCurrentShape().rotate();
        atSide();
        if (atBottom()) {
            getCurrentShape().rotate(3);
        }
        drawShape(false);
    }

    public void dropShape() {
        drawShape(true);
        while (!atBottom()) {
            getCurrentShape().update();
        }

        drawShape(false);
        getCurrentShape().makeStationary();
        getCurrentShape().points().stream().filter((p) -> (p.y >= getHeight())).forEach((_item) -> {
            clearBoard();
        });
        spawnShape();

        clearFullRows();
    }
}
