package com.unrealclone;

import com.unrealclone.engine.*;
import com.unrealclone.engine.components.MeshRenderer;
import org.joml.Vector3f;
import static org.lwjgl.opengl.GL46.*;
import static org.lwjgl.glfw.GLFW.*;

public class TestScene {
    public static void main(String[] args) {
        System.out.println("Starting TestScene initialization");
        System.out.flush();
        
        // Initialize engine systems
        Renderer renderer = new Renderer();
        renderer.initialize();
        System.out.println("Renderer initialized");
        System.out.flush();
        
        // Create test scene
        Scene scene = new Scene(renderer);
        System.out.println("Scene created");
        System.out.flush();
        
        // Create a simple triangle
        System.out.println("Creating triangle object");
        GameObject triangle = new GameObject();
        triangle.setName("Triangle");
        System.out.println("Triangle game object created");
        System.out.flush();
        
        // Vertex data (position + color)
        float[] vertices = {
            // Position      // Color
            -0.5f, -0.5f, 0.0f, 1.0f, 0.0f, 0.0f,
             0.5f, -0.5f, 0.0f, 0.0f, 1.0f, 0.0f,
             0.0f,  0.5f, 0.0f, 0.0f, 0.0f, 1.0f
        };
        
        int[] indices = {0, 1, 2};
        
        System.out.println("Adding MeshRenderer component");
        MeshRenderer meshRenderer = new MeshRenderer(renderer, vertices, indices);
        triangle.addComponent(meshRenderer);
        scene.addGameObject(triangle);
        System.out.println("Triangle added to scene");
        System.out.flush();
        
        // Main game loop
        System.out.println("Entering main render loop");
        int frameCount = 0;
        while (!renderer.shouldClose()) {
            System.out.println("Frame " + (++frameCount));
            System.out.flush();
            
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            System.out.println("After glClear - glError: " + glGetError());
            
            scene.update(0.016f);
            System.out.println("After scene update - glError: " + glGetError());
            
            glfwSwapBuffers(renderer.getWindow());
            System.out.println("After swap buffers - glError: " + glGetError());
            
            glfwPollEvents();
            System.out.flush();
        }
        
        // Cleanup
        scene.cleanup();
        renderer.cleanup();
    }
}