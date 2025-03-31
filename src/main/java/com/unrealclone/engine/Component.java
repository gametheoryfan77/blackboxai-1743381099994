package com.unrealclone.engine;

public abstract class Component {
    protected GameObject gameObject;
    
    public void setGameObject(GameObject gameObject) {
        this.gameObject = gameObject;
    }
    
    public GameObject getGameObject() {
        return gameObject;
    }
    
    public void start() {}
    public void update(float deltaTime) {}
    public void cleanup() {}
    
    public Transform getTransform() {
        return gameObject.getTransform();
    }
}