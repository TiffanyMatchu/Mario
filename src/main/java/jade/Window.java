package jade;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;


/**
 * Created by Tiffany Matthew on 1/14/22.
 * Singleton class... only one instance of window will exist
 */
public class Window {
    private final int width;
    private final int height;
    private final String title;
    private static Window window = null;
    private long glfwWindow;

    private Window(){
        this.width = 1920;
        this.height = 1080; //user no touch these
        this.title = "Mario";
    }
   /*
   * One instance of window is created upon initial call of get method, every
   * other call to method will result in original method being returned
   */
    public static Window getWindow(){
        if(Window.window == null){
            Window.window = new Window();
        }
        return Window.window;
    }

    public void run(){
        //Checks to see if LWJGL is working
        System.out.println("Hello LWJGL" + Version.getVersion() + "!");

        init();
        loop();
    }

    public void init(){
        //Set up error call back So GLFW can print any errors that occur
        GLFWErrorCallback.createPrint(System.err).set(); //creates print statement to show error

        //Initialize GLFW
        if (!glfwInit()){ //returns true if initialization if false throws error
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        //Configure GLFW
        glfwDefaultWindowHints(); //hints example, resizeable? visible? etc
        glfwWindowHint(GLFW_VISIBLE,GLFW_FALSE); //not visible until window is finished later
        glfwWindowHint(GLFW_RESIZABLE,GLFW_TRUE); //should be default
        glfwWindowHint(GLFW_MAXIMIZED,GLFW_TRUE); //Starts window in maximized position
        //hints were set first because GLFW will use those hints to create window
        /*
        * Create Window
        * Create a window returns a long for a memory space for where the window is
        * */
        glfwWindow = glfwCreateWindow(this.width,this.height,this.title,NULL, NULL);

        //check if window was made
        if (glfwWindow == NULL){
            throw new IllegalStateException("Failed to create new GLFW window");
        }

        //Make the OpenGL context current
        glfwMakeContextCurrent(glfwWindow);
        //Enable v-sync **game refresh rate matches
        glfwSwapInterval(1);
        //make window visible
        glfwShowWindow(glfwWindow); //creates window from the long, which points to a memory slot

    }
    public void loop(){
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();
        // Set the clear color
        glClearColor(1.0f, 0.0f, 0.0f, 0.0f); //sets
        //while window is open
        while (!glfwWindowShouldClose(glfwWindow)){
            //Poll events ** key listeners
            glfwPollEvents();
            glClear(GL_COLOR_BUFFER_BIT); //flushes color to entire screen
            glfwSwapBuffers(glfwWindow);
        }
    }
}
