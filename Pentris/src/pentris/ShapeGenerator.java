package pentris;

import java.awt.Color;

/**
 * @author Hadi Soufi
 * @version 1 Program {number}: {name} CSC230-02 Spring 2016
 */
public class ShapeGenerator {

    private static int x = 0;
    private static int y = 0;

    /**
     * Generate a random shape from the possible shapes
     *
     * @param xPos
     * @param yPos
     * @return
     */
    public static Shape generateShape(int xPos, int yPos) {
        x = xPos;
        y = yPos;
        switch ((int) (Math.random() * 29)) {
            case 0:
                return dot();
            case 1:
                return biLine();
            case 2:
                return triLine();
            case 3:
                return triCorner();
            case 4:
                return quadLine();
            case 5:
                return quadL();
            case 6:
                return quadL().mirror();
            case 7:
                return quadT();
            case 8:
                return quadCube();
            case 9:
                return quadZig();
            case 10:
                return quadZig().mirror();
            case 11:
                return pentLine();
            case 12:
                return pentL();
            case 13:
                return pentL().mirror();
            case 14:
                return pentCross();
            case 15:
                return pentT();
            case 16:
                return pentCube();
            case 17:
                return pentCube().mirror();
            case 18:
                return pentR();
            case 19:
                return pentR().mirror();
            case 20:
                return pentF();
            case 21:
                return pentF().mirror();
            case 22:
                return pentZig();
            case 23:
                return pentZig().mirror();
            case 24:
                return pentCorner();
            case 25:
                return pentStair();
            case 26:
                return pentU();
            case 27:
                return pentZ();
            case 28:
                return pentZ().mirror();
            default:
                return dot();
        }
    }

    /**
     * x
     *
     * @return a Shape with the above configuration
     */
    public static Shape dot() {
        Shape s = new Shape(x, y, 1);
        s.add(new Point(0, 0));
        s.setColor(Color.BLUE);
        return s;
    }

    /**
     * x
     * x
     *
     * @return a Shape with the above configuration
     */
    public static Shape biLine() {
        Shape s = new Shape(x, y, 2);
        s.add(new Point(0, -1));
        s.add(new Point(0, 0));
        s.setColor(Color.CYAN);
        return s;
    }

    /**
     * x
     * x
     * x
     *
     * @return a Shape with the above configuration
     */
    public static Shape triLine() {
        Shape s = new Shape(x, y, 3);
        s.add(new Point(0, -1));
        s.add(new Point(0, 0));
        s.add(new Point(0, 1));
        s.setColor(Color.YELLOW);
        return s;
    }

    /**
     * x
     * x x
     *
     * @return a Shape with the above configuration
     */
    public static Shape triCorner() {
        Shape s = new Shape(x, y, 3);
        s.add(new Point(0, -1));
        s.add(new Point(0, 0));
        s.add(new Point(1, 0));
        s.setColor(Color.GREEN);
        return s;
    }

    /**
     * x
     * x
     * x
     * x
     *
     * @return a Shape with the above configuration
     */
    public static Shape quadLine() {
        Shape s = new Shape(x, y, 4);
        s.add(new Point(0, -1));
        s.add(new Point(0, 0));
        s.add(new Point(0, 1));
        s.add(new Point(0, 2));
        s.setColor(Color.MAGENTA);
        return s;
    }

    /**
     * x
     * x
     * x x
     *
     * @return a Shape with the above configuration
     */
    public static Shape quadL() {
        Shape s = new Shape(x, y, 4);
        s.add(new Point(0, -1));
        s.add(new Point(0, 0));
        s.add(new Point(0, 1));
        s.add(new Point(1, 1));
        s.setColor(Color.ORANGE);
        return s;
    }

    /**
     * x
     * x x x
     *
     * @return a Shape with the above configuration
     */
    public static Shape quadT() {
        Shape s = new Shape(x, y, 4);
        s.add(new Point(0, -1));
        s.add(new Point(-1, 0));
        s.add(new Point(0, 0));
        s.add(new Point(1, 0));
        s.setColor(Color.PINK);
        return s;
    }

    /**
     * x x
     * x x
     *
     * @return a Shape with the above configuration
     */
    public static Shape quadCube() {
        Shape s = new Shape(x, y, 4);
        s.add(new Point(1, -1));
        s.add(new Point(0, -1));
        s.add(new Point(0, 0));
        s.add(new Point(1, 0));
        s.setColor(Color.RED);
        return s;
    }

    /**
     * x x
     * x x
     *
     * @return a Shape with the above configuration
     */
    public static Shape quadZig() {
        Shape s = new Shape(x, y, 4);
        s.add(new Point(-1, -1));
        s.add(new Point(0, -1));
        s.add(new Point(0, 0));
        s.add(new Point(1, 0));
        s.setColor(Color.BLUE);
        return s;
    }

    /**
     * x
     * x
     * x
     * x
     * x
     *
     * @return a Shape with the above configuration
     */
    public static Shape pentLine() {
        Shape s = new Shape(x, y, 5);
        s.add(new Point(0, -2));
        s.add(new Point(0, -1));
        s.add(new Point(0, 0));
        s.add(new Point(0, 1));
        s.add(new Point(0, 2));
        s.setColor(Color.BLUE);
        return s;
    }

    /**
     * x
     * x
     * x
     * x x
     *
     * @return a Shape with the above configuration
     */
    public static Shape pentL() {
        Shape s = new Shape(x, y, 5);
        s.add(new Point(0, -2));
        s.add(new Point(0, -1));
        s.add(new Point(0, -0));
        s.add(new Point(0, 1));
        s.add(new Point(1, 1));
        s.setColor(Color.CYAN);
        return s;
    }

    /**
     * x
     * x x x
     * x
     *
     * @return a Shape with the above configuration
     */
    public static Shape pentCross() {
        Shape s = new Shape(x, y, 5);
        s.add(new Point(0, -1));
        s.add(new Point(-1, 0));
        s.add(new Point(0, 0));
        s.add(new Point(1, 0));
        s.add(new Point(0, 1));
        s.setColor(Color.GREEN);
        return s;
    }

    /**
     * x x x
     * x
     * x
     *
     * @return a Shape with the above configuration
     */
    public static Shape pentT() {
        Shape s = new Shape(x, y, 5);
        s.add(new Point(-1, -1));
        s.add(new Point(0, -1));
        s.add(new Point(1, -1));
        s.add(new Point(0, 0));
        s.add(new Point(0, 1));
        s.setColor(Color.MAGENTA);
        return s;
    }

    /**
     * x x x
     * x x
     *
     * @return a Shape with the above configuration
     */
    public static Shape pentCube() {
        Shape s = new Shape(x, y, 5);
        s.add(new Point(-1, -1));
        s.add(new Point(0, -1));
        s.add(new Point(1, -1));
        s.add(new Point(0, 0));
        s.add(new Point(1, 0));
        s.setColor(Color.ORANGE);
        return s;
    }

    /**
     * x
     * x x
     * x x
     *
     * @return a Shape with the above configuration
     */
    public static Shape pentR() {
        Shape s = new Shape(x, y, 5);
        s.add(new Point(0, -1));
        s.add(new Point(0, 0));
        s.add(new Point(-1, 0));
        s.add(new Point(0, 1));
        s.add(new Point(1, 1));
        s.setColor(Color.PINK);
        return s;
    }

    /**
     * x
     * x x
     * x
     * x
     *
     * @return a Shape with the above configuration
     */
    public static Shape pentF() {
        Shape s = new Shape(x, y, 5);
        s.add(new Point(0, -1));
        s.add(new Point(0, 0));
        s.add(new Point(1, 0));
        s.add(new Point(0, 1));
        s.add(new Point(0, 2));
        s.setColor(Color.RED);
        return s;
    }

    /**
     * x
     * x x
     * x
     * x
     *
     * @return a Shape with the above configuration
     */
    public static Shape pentZig() {
        Shape s = new Shape(x, y, 5);
        s.add(new Point(1, -1));
        s.add(new Point(1, 0));
        s.add(new Point(0, 0));
        s.add(new Point(0, 1));
        s.add(new Point(0, 2));
        s.setColor(Color.GREEN);
        return s;
    }

    /**
     * x
     * x
     * x x x
     *
     * @return a Shape with the above configuration
     */
    public static Shape pentCorner() {
        Shape s = new Shape(x, y, 5);
        s.add(new Point(0, -2));
        s.add(new Point(0, -1));
        s.add(new Point(0, 0));
        s.add(new Point(1, 0));
        s.add(new Point(2, 0));
        s.setColor(Color.YELLOW);
        return s;
    }

    /**
     * x x
     * x x
     * x
     *
     * @return a Shape with the above configuration
     */
    public static Shape pentStair() {
        Shape s = new Shape(x, y, 5);
        s.add(new Point(-1, -1));
        s.add(new Point(-1, 0));
        s.add(new Point(0, 0));
        s.add(new Point(0, 1));
        s.add(new Point(1, 1));
        s.setColor(Color.BLUE);
        return s;
    }

    /**
     * x x
     * x x x
     *
     * @return a Shape with the above configuration
     */
    public static Shape pentU() {
        Shape s = new Shape(x, y, 5);
        s.add(new Point(-1, -1));
        s.add(new Point(-1, 0));
        s.add(new Point(0, 0));
        s.add(new Point(1, 0));
        s.add(new Point(1, -1));
        s.setColor(Color.CYAN);
        return s;
    }

    /**
     * x x
     * x
     * x x
     *
     * @return a Shape with the above configuration
     */
    public static Shape pentZ() {
        Shape s = new Shape(x, y, 5);
        s.add(new Point(-1, -1));
        s.add(new Point(0, -1));
        s.add(new Point(0, 0));
        s.add(new Point(0, 1));
        s.add(new Point(1, 1));
        s.setColor(Color.GREEN);
        return s;
    }
}
