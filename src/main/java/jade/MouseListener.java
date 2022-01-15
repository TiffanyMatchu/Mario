package jade;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Callbacks for this class will go in Window, just after window is created in init().
 */
public class MouseListener {
    private static MouseListener instance;
    private double scrollX;
    private double scrollY;
    private double xPos, yPos, lastX, lastY;
    private final boolean[] mouseButtonPressed = new boolean[3]; //no fancy mouse inputs
    private boolean isDragging;

    //Singleton class hence private constructor
    private MouseListener() {

        // not initializing this could cause error when launching game

        this.scrollX = 0;
        this.scrollY = 0;
        this.xPos = 0;
        this.yPos = 0;
        this.lastX = 0;
        this.lastY = 0;
    }

    public static MouseListener get() {
        if (MouseListener.instance == null) {
            MouseListener.instance = new MouseListener();
        }
        return MouseListener.instance;
    }

    /*
     * Go to GLFW startup guide and reference necessary callback functions
     * needed for mouse inputs. Its in C in the guide*****
     */

    public static void mousePosCallback(long window, double xPos, double yPos) {
        //set current X,Y mouse position to last mouse position
        get().lastX = get().xPos;
        get().lastY = get().yPos;

        //set current X,Y mouse positions from method parameters
        get().xPos = xPos;
        get().yPos = yPos;
        //if any mouseButtonsPressed == true. Dragging will be true
        get().isDragging = get().mouseButtonPressed[0] || get().mouseButtonPressed[1] || get().mouseButtonPressed[3];
    }

    //mods are modifiers ie... hitting ctrl with mouse click
    public static void mouseButtonCallback(long window, int button, int action, int mods) {
        //check if button action is in the state of press or release. Set button boolean
        if (action == GLFW_PRESS) {
            //checks if button is < 3. Ignores any other mouse buttons.
            if (button < get().mouseButtonPressed.length) {
                get().mouseButtonPressed[button] = true;
            }
        } else if (action == GLFW_RELEASE) {
            if (button < get().mouseButtonPressed.length) {
                get().mouseButtonPressed[button] = false;
                get().isDragging = false;
            }
        }
    }

    public static void mouseScrollCallback(long window, double xOffset, double yOffset) {
        get().scrollX = xOffset;
        get().scrollY = yOffset;
    }

    public static void endFrame() {
        get().scrollX = 0;
        get().scrollY = 0;
        get().lastX = get().xPos; //apparently this sets xPos to zero. Not sure how tho?
        get().lastY = get().yPos;
    }

    public static float getX() {
        return (float) get().xPos;
    }

    public static float getY() {
        return (float) get().yPos;
    }

    //gives the amount of lapsed X,Y positions in the current frame
    public static float getDx() {
        return (float) (get().lastX - get().xPos);
    }

    public static float getDy() {
        return (float) (get().lastY - get().yPos);
    }

    public static float getScrollX() {
        return (float) get().scrollX;
    }

    public static float getScrollY() {
        return (float) get().scrollY;
    }

    public static boolean getDragging() {
        return get().isDragging;
    }

    public static boolean mouseButtonDown(int button) {
        if (button < get().mouseButtonPressed.length) {
            return get().mouseButtonPressed[button];
        } else {
            return false;
        }
    }
}
