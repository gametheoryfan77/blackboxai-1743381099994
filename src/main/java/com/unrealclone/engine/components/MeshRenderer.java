package com.unrealclone.engine.components;

import com.unrealclone.engine.Component;
import com.unrealclone.engine.Renderer;
import static org.lwjgl.opengl.GL46.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MeshRenderer extends Component {
    private int vao;
    private int vertexCount;
    private int shaderProgram;
    private Renderer renderer;

    public MeshRenderer(Renderer renderer, float[] vertices, int[] indices) {
        this.renderer = renderer;
        createMesh(vertices, indices);
    }

    private void createMesh(float[] vertices, int[] indices) {
        vao = glGenVertexArrays();
        int vbo = glGenBuffers();
        int ebo = glGenBuffers();

        glBindVertexArray(vao);

        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ebo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indices, GL_STATIC_DRAW);

        // Position attribute
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 6 * Float.BYTES, 0);
        glEnableVertexAttribArray(0);

        // Color attribute
        glVertexAttribPointer(1, 3, GL_FLOAT, false, 6 * Float.BYTES, 3 * Float.BYTES);
        glEnableVertexAttribArray(1);

        vertexCount = indices.length;
        glBindVertexArray(0);
    }

    private void createShaderProgram() {
        int vertexShader = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertexShader, readShaderFile("vertex.glsl"));
        glCompileShader(vertexShader);
        System.out.println("Vertex shader compiled: " + (glGetShaderi(vertexShader, GL_COMPILE_STATUS) == GL_TRUE));
        System.out.println("Vertex shader log: " + glGetShaderInfoLog(vertexShader));

        int fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragmentShader, readShaderFile("fragment.glsl"));
        glCompileShader(fragmentShader);
        System.out.println("Fragment shader compiled: " + (glGetShaderi(fragmentShader, GL_COMPILE_STATUS) == GL_TRUE));
        System.out.println("Fragment shader log: " + glGetShaderInfoLog(fragmentShader));

        shaderProgram = glCreateProgram();
        glAttachShader(shaderProgram, vertexShader);
        glAttachShader(shaderProgram, fragmentShader);
        glLinkProgram(shaderProgram);
        System.out.println("Program linked: " + (glGetProgrami(shaderProgram, GL_LINK_STATUS) == GL_TRUE));
        System.out.println("Program log: " + glGetProgramInfoLog(shaderProgram));

        glDeleteShader(vertexShader);
        glDeleteShader(fragmentShader);
    }

    private String readShaderFile(String filename) {
        try {
            return new String(Files.readAllBytes(Paths.get("src/main/resources/shaders/" + filename)));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read shader file: " + filename, e);
        }
    }

    private void checkShaderCompilation(int shader) {
        if (glGetShaderi(shader, GL_COMPILE_STATUS) == GL_FALSE) {
            throw new RuntimeException("Shader compilation failed: " + glGetShaderInfoLog(shader));
        }
    }

    private void checkProgramLinking(int program) {
        if (glGetProgrami(program, GL_LINK_STATUS) == GL_FALSE) {
            throw new RuntimeException("Program linking failed: " + glGetProgramInfoLog(program));
        }
    }

    @Override
    public void update(float deltaTime) {
        glUseProgram(shaderProgram);
        glBindVertexArray(vao);
        glDrawElements(GL_TRIANGLES, vertexCount, GL_UNSIGNED_INT, 0);
        glBindVertexArray(0);
    }

    @Override
    public void cleanup() {
        glDeleteVertexArrays(vao);
        glDeleteProgram(shaderProgram);
    }
}