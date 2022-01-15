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
        this. xPos = 0;
        this.yPos = 0;
        this.lastX = 0;
        this.lastY = 0;
    }

    public static MouseListener get() {
        if (instance == null){
            instance = new MouseListener();
        }
        return instance;
    }
}
