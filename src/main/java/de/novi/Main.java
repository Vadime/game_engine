package de.novi;

import org.lwjgl.glfw.GLFW;

import de.novi.engine.Window;
import de.novi.shaders.MeshRenderer;

public class Main extends Window {

    private int direction = 0;
    private float color = 0;

    @Override
    public void init() {
        addShader(new MeshRenderer());
    }

    @Override
    public void input() {
        if (isKeyPressed(GLFW.GLFW_KEY_UP)) {
            direction = 1;
        } else if (isKeyPressed(GLFW.GLFW_KEY_DOWN)) {
            direction = -1;
        } else {
            direction = 0;
        }
    }

    @Override
    public void update() {
        color += direction * 0.01;
        if (color > 1)
            color = 1;
        if (color < 0)
            color = 0;
    }

    @Override
    public void draw() {
        clearColor(color, color, color);
    }

    @Override
    public void clean() {
    }

    public static void main(String... args) throws Exception {
        try (Main main = new Main();) {
            main.run();
        }
    }

}
