package com.unrealclone.engine;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    private List<GameObject> gameObjects = new ArrayList<>();
    private Renderer renderer;
    
    public Scene(Renderer renderer) {
        this.renderer = renderer;
    }
    
    public void addGameObject(GameObject gameObject) {
        gameObjects.add(gameObject);
    }
    
    public void removeGameObject(GameObject gameObject) {
        gameObjects.remove(gameObject);
    }
    
    public void update(float deltaTime) {
        for (GameObject gameObject : gameObjects) {
            gameObject.update(deltaTime);
        }
    }
    
    public void render() {
        renderer.render();
    }
    
    public void cleanup() {
        for (GameObject gameObject : gameObjects) {
            gameObject.cleanup();
        }
    }
}