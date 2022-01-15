package jade;

/**
 * Created by Tiffany Matthew on 1/14/22.
 */
public class MouseListener {
    private static MouseListener instance;
    private double scrollX, scrollY;
    private double xPos, yPos, lastX, lastY;
    private boolean mouseButtonPressed[] = new boolean[3];
    private boolean isDragging;
    //Singleton class hence private constructor
    private MouseListener(){
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

    public static void mousePosCallback(long window, double xpos, double ypos) {
        //set current X,Y mouse position to last mouse position
        get().lastX = get().xPos;
        get().lastY = get().yPos;

        //set current X,Y mouse positions from method parameters
        get().xPos = xpos;
        get().yPos = ypos;

    }

}
