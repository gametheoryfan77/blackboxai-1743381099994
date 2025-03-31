package com.unrealclone.engine;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL46.*;
import static org.lwjgl.system.MemoryUtil.*;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.PointerBuffer;

public class Renderer {
    private long window;
    private int width = 1280;
    private int height = 720;
    
    public void initialize() {
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }
        
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 6);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        
        window = glfwCreateWindow(width, height, "Unreal Clone", NULL, NULL);
        if (window == NULL) {
            System.err.println("Failed to create GLFW window");
            int errorCode = glfwGetError(null);
            System.err.println("GLFW error code: " + errorCode);
            throw new RuntimeException("Failed to create GLFW window");
        }
        System.err.println("GLFW window created successfully. Window handle: " + window);
        System.err.flush();
        
        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        glfwShowWindow(window);
        // Force window to front and focused
        glfwFocusWindow(window);
        // Set window position to ensure visibility
        glfwSetWindowPos(window, 100, 100);
        
        // Initialize OpenGL
        GL.createCapabilities();
        System.out.println("OpenGL Version: " + glGetString(GL_VERSION));
        System.out.println("GLSL Version: " + glGetString(GL_SHADING_LANGUAGE_VERSION));
        
        glEnable(GL_DEPTH_TEST);
        glClearColor(0.1f, 0.1f, 0.1f, 1.0f);
        glViewport(0, 0, width, height);
        
        // Force flush output
        System.out.println("Renderer initialized successfully");
        System.out.println("Window visible: " + glfwGetWindowAttrib(window, GLFW_VISIBLE));
        System.out.println("Window focused: " + glfwGetWindowAttrib(window, GLFW_FOCUSED));
        System.out.println("OpenGL vendor: " + glGetString(GL_VENDOR));
        System.out.println("OpenGL renderer: " + glGetString(GL_RENDERER));
        System.out.println("GLFW window created successfully");
        System.out.println("OpenGL context created");
        System.out.flush();
    }
    
    public void render() {
        glClearColor(0.1f, 0.1f, 0.1f, 1.0f);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        
        glfwSwapBuffers(window);
        glfwPollEvents();
    }
    
    public boolean shouldClose() {
        return glfwWindowShouldClose(window);
    }
    
    public long getWindow() {
        return window;
    }

    public void cleanup() {
        glfwDestroyWindow(window);
        glfwTerminate();
    }
}