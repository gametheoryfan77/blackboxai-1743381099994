package com.unrealclone.engine;

import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class Transform {
    private Vector3f position = new Vector3f(0, 0, 0);
    private Quaternionf rotation = new Quaternionf();
    private Vector3f scale = new Vector3f(1, 1, 1);

    public Matrix4f getWorldMatrix() {
        return new Matrix4f()
            .translation(position)
            .rotate(rotation)
            .scale(scale);
    }

    public void translate(Vector3f translation) {
        position.add(translation);
    }

    public void rotate(float angle, Vector3f axis) {
        rotation.rotateAxis(angle, axis);
    }

    // Getters and setters
    public Vector3f getPosition() { return position; }
    public void setPosition(Vector3f position) { this.position = position; }
    public Quaternionf getRotation() { return rotation; }
    public void setRotation(Quaternionf rotation) { this.rotation = rotation; }
    public Vector3f getScale() { return scale; }
    public void setScale(Vector3f scale) { this.scale = scale; }
}