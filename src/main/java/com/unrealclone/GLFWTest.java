import static org.lwjgl.glfw.GLFW.*;

public class GLFWTest {
    public static void main(String[] args) {
        // Force immediate output
        System.err.println("=== GLFW TEST STARTING ===");
        System.err.flush();
        try { Thread.sleep(100); } catch (InterruptedException e) {}
        if (!glfwInit()) {
            throw new RuntimeException("Failed to initialize GLFW");
        }
        
        System.err.println("Creating window");
        long window = glfwCreateWindow(800, 600, "GLFW Test", 0, 0);
        if (window == 0) {
            throw new RuntimeException("Failed to create window");
        }
        
        System.err.println("Window created successfully");
        glfwMakeContextCurrent(window);
        glfwShowWindow(window);
        
        while (!glfwWindowShouldClose(window)) {
            glfwSwapBuffers(window);
            glfwPollEvents();
        }
        
        glfwDestroyWindow(window);
        glfwTerminate();
        System.err.println("Test completed");
    }
}