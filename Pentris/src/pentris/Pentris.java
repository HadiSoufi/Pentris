/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pentris;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

/**
 *
 * @author adi
 */
public class Pentris {

    Board board = new Board();
    NextShapeBoard nextShapeBoard;
    JFrame textFrame;
    JLabel label;

    float DEFAULT_SPEED = 0.4f;
    float DROP_SPEED = 0.1f;
    float updateSpeed = DEFAULT_SPEED;

    int keyboardUpdateSpeed = 6;
    int BOX_SIZE;
    int score = 0;

    // We need to strongly reference callback instances.
    private GLFWErrorCallback errorCallback;
    private GLFWKeyCallback keyCallback;

    // The window handle
    private long window;

    public void run() {
        try {
            init();
            loop();

            // Release window and window callbacks
            glfwDestroyWindow(window);
            keyCallback.release();
        } finally {
            // Terminate GLFW and release the GLFWErrorCallback
            glfwTerminate();
            errorCallback.release();
        }
    }

    private void init() {

        // Setup an error callback. The default implementation will print the error message in System.err.
        glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if (glfwInit() != GLFW_TRUE) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        // Configure this window
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable

        int WIDTH = 600;
        int HEIGHT = 600;

        BOX_SIZE = (int) HEIGHT / 24;

        //For fullscreen: glfwGetPrimaryMonitor()
        //Create the window
        window = glfwCreateWindow(WIDTH, HEIGHT, "Pentris", NULL, NULL);
        if (window == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        //Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window, keyCallback = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {

                //When escape key is pressed, close game
                if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
                    glfwSetWindowShouldClose(window, GLFW_TRUE);
                }
                //When the left key is pressed, move the current shape left
                if (key == GLFW_KEY_LEFT && !(action == GLFW_RELEASE) && !board.isPaused()) {
                    board.moveShape(true);
                }
                //When the right key is pressed, move the current shape right
                if (key == GLFW_KEY_RIGHT && !(action == GLFW_RELEASE) && !board.isPaused()) {
                    board.moveShape(false);
                }
                //When the up key is pressed, rotate the current shape
                if (key == GLFW_KEY_UP && !(action == GLFW_RELEASE) && !board.isPaused()) {
                    board.rotateShape();
                }
                //When the down key is pressed, increase the drop speed
                if (key == GLFW_KEY_DOWN && !(action == GLFW_RELEASE) && !board.isPaused()) {
                    updateSpeed = DROP_SPEED;
                }
                //When the down key is released, reduce the drop speed
                if (key == GLFW_KEY_DOWN && action == GLFW_RELEASE && !board.isPaused()) {
                    updateSpeed = DEFAULT_SPEED;
                }
                //When the space bar is pressed, drop the current shape
                if (key == GLFW_KEY_SPACE && action == GLFW_PRESS && !board.isPaused()) {
                    board.dropShape();
                }
                //When the n key is pressed, restart the game
                if (key == GLFW_KEY_N && action == GLFW_PRESS) {
                    board = new Board();
                }
                //When the p key is pressed, pause or resume the game
                if (key == GLFW_KEY_P && action == GLFW_PRESS) {
                    if (!board.isPaused()) {
                        board.pause();
                    } else if (board.isPaused()) {
                        board.play();
                    }
                }
            }
        });

        // Get the resolution of the primary monitor
        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

        // Center our window
        glfwSetWindowPos(window, (vidmode.width() - WIDTH) / 2, (vidmode.height() - HEIGHT) / 2);

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);

        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(window);

        // This line is critical for LWJGL's interoperation with GLFW's OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread, creates the GLCapabilities instance and makes the OpenGL bindings available for use.
        GL.createCapabilities();

        // Set the clear color
        glClearColor(1.0f, 1.0f, 1.0f, 0.0f);

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, WIDTH, 0, HEIGHT, 1, -1);

        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();

        nextShapeBoard = new NextShapeBoard((int) (0.625 * WIDTH), (int) (0.625 * HEIGHT + 1), board.getNextShape());
        textFrame = new JFrame("Information");
        label = new JLabel("Controls:\nn to start new game");
        textFrame.add(label);

        //load a default java font
        //font = new TrueTypeFont(new Font("Times New Roman", Font.BOLD, 24), false);
    }

    private void loop() {
        float now, last, delta;
        last = (float) glfwGetTime();
        delta = 0;

        //Run the rendering loop until the user has attempted to close the window or has pressed the ESCAPE key.
        while (glfwWindowShouldClose(window) == GLFW_FALSE) {

            now = (float) glfwGetTime();
            delta += now - last;
            last = now;

            if (delta >= updateSpeed && !board.isPaused()) {
                board.update();
                nextShapeBoard.update(board.getNextShape());
                delta = 0;
            }

            //clear the framebuffer
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            //Draw the screen
            render();

            //swap the color buffers
            glfwSwapBuffers(window);

            //Poll for window events. The key callback above will only be invoked during this call.
            glfwPollEvents();
        }
    }

    public void render() {

        renderBox(board);
        renderBox(nextShapeBoard);

        if (score != board.score()) {
            score = board.score();
            System.out.println(score);
        }
    }

    public void renderBox(Grid grid) {
        Color c;
        for (int x = 0; x < grid.getWidth(); x++) {
            for (int y = 0; y < grid.getHeight(); y++) {
                c = grid.getPoint(y, x).color;
                glColor3f(c.getRed() / 255, c.getGreen() / 255, c.getBlue() / 255);
                glRectf(BOX_SIZE * x + grid.getX(), BOX_SIZE * y + grid.getY(), (BOX_SIZE * x) + BOX_SIZE + grid.getX(), (BOX_SIZE * y) + BOX_SIZE + grid.getY());
            }
        }
    }

    public static void main(String[] args) {
        Pentris p = new Pentris();
        p.run();
    }
}
