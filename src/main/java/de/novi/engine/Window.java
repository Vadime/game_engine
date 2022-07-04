package de.novi.engine;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.system.MemoryStack;

import de.novi.shaders.Shader;

import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFWErrorCallback.createPrint;
import static org.lwjgl.opengl.GL.createCapabilities;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

public abstract class Window extends Timer implements AutoCloseable, Runnable {

    private long handle;
    private List<Shader> shaders = new ArrayList<>();

    @Override
    public void run() {
        try (GLFWErrorCallback errorCallBack = createPrint(System.err);) {
            errorCallBack.set();
        }
        Logger.log("Starting LWJGL " + Version.getVersion());
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);
        handle = glfwCreateWindow(300, 300, "Game engine", NULL, NULL);
        if (handle == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }
        glfwSetKeyCallback(handle, (handle, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
                glfwSetWindowShouldClose(handle, true);
            }
        });

        try (MemoryStack stack = stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);
            glfwGetWindowSize(handle, pWidth, pHeight);
            GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
            glfwSetWindowPos(
                    handle,
                    (vidMode.width() - pWidth.get(0)) / 2,
                    (vidMode.height() - pHeight.get(0)) / 2);
        }
        glfwMakeContextCurrent(handle);
        glfwSwapInterval(1);
        glfwShowWindow(handle);
        createCapabilities();
        Logger.log("OpenGL: " + glGetString(GL_VERSION));

        init();

        initTime();
        float elapsedTime;
        float accumulator = 0f;
        float interval = 1f / TARGET_UPS;
        while (!glfwWindowShouldClose(handle)) {
            elapsedTime = getElapsedTime();
            accumulator += elapsedTime;

            input();

            while (accumulator >= interval) {
                update();
                accumulator -= elapsedTime;
            }

            clear();
            draw();
            for (Shader shader : shaders) {
                shader.render();
            }
            glfwSwapBuffers(handle);
            glfwPollEvents();
            sync();
        }
        for (Shader shader : shaders) {
            shader.clean();
        }
        clean();
    }

    public boolean isKeyPressed(int keyCode) {
        return glfwGetKey(handle, keyCode) == GLFW_PRESS;
    }

    public abstract void init();

    public abstract void input();

    public abstract void update();

    public abstract void draw();

    public abstract void clean();

    @Override
    public void close() throws Exception {
        glfwFreeCallbacks(handle);
        glfwDestroyWindow(handle);
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    public void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    public void clearColor(float r, float g, float b) {
        glClearColor(r, g, b, 0.0f);
    }

    /**
     * @return the handle
     */
    public long gethandle() {
        return handle;
    }

    public void addShader(Shader shader) {
        shaders.add(shader);
    }

}
