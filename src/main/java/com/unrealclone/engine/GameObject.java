package com.unrealclone.engine;

import java.util.ArrayList;
import java.util.List;

public class GameObject {
    private List<Component> components = new ArrayList<>();
    private Transform transform = new Transform();
    private String name = "GameObject";

    public void update(float deltaTime) {
        for (Component component : components) {
            component.update(deltaTime);
        }
    }

    public void addComponent(Component component) {
        component.setGameObject(this);
        components.add(component);
    }

    public <T extends Component> T getComponent(Class<T> componentClass) {
        for (Component component : components) {
            if (componentClass.isInstance(component)) {
                return componentClass.cast(component);
            }
        }
        return null;
    }

    public void cleanup() {
        for (Component component : components) {
            component.cleanup();
        }
    }

    // Getters and setters
    public Transform getTransform() { return transform; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}