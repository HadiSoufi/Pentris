package pentris;

/**
 * @author Hadi Soufi
 * @version 1 Program {number}: {name} CSC230-02 Spring 2016
 */
public class NextShapeBoard extends Grid {

    public NextShapeBoard(int x, int y, Shape s) {
        super(6, 6, x, y, new Shape(s.x, s.y, s.points()));
        clearBoard();
    }

    public void spawnShape() {
        boolean outOfBox = false;
        while (getCurrentShape().y > (int) (getHeight() / 2 + 0.5)) {
            for (Point p : getCurrentShape().points()) {
                if (p.y > (int) (getHeight() / 2 + 0.5)) {
                    outOfBox = true;
                }
            }
            if (!outOfBox) {
                getCurrentShape().y = (int) (getHeight() / 2 + 0.5);
            } else {
                getCurrentShape().update();
            }
            outOfBox = false;
        }
        while (getCurrentShape().x > (int) (getWidth() / 2 + 0.5)) {
            for (Point p : getCurrentShape().points()) {
                if (p.x > (int) (getWidth() / 2 + 0.5)) {
                    outOfBox = true;
                }
            }
            if (!outOfBox) {
                getCurrentShape().x = (int) (getWidth() / 2 + 0.5);
            } else {
                getCurrentShape().addX(true);
            }
            outOfBox = false;
        }
    }

    public void update(Shape s) {
        setCurrentShape(new Shape(s));
        clearBoard();
        spawnShape();
        drawShape(false);
    }
}
