package de.novi.shaders;

import static org.lwjgl.opengl.GL20.*;

import java.io.IOException;

import de.novi.engine.Logger;
import de.novi.engine.Utils;

public abstract class Shader { 

    private final int programId;

    private int vertexShaderId;

    private int fragmentShaderId;

    protected Shader() {
        programId = glCreateProgram();
        if (programId == 0) {
            Logger.log("Could not create Shader");
        }
        try {
            createVertexShader(Utils.loadResource("/vertex.vert"));
            createFragmentShader(Utils.loadResource("/fragment.frag"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        link();
        init();

    }

    private void createVertexShader(String shaderCode) {
        vertexShaderId = createShader(shaderCode, GL_VERTEX_SHADER);
    }

    private void createFragmentShader(String shaderCode) {
        fragmentShaderId = createShader(shaderCode, GL_FRAGMENT_SHADER);
    }

    private int createShader(String shaderCode, int shaderType) {
        int shaderId = glCreateShader(shaderType);
        if (shaderId == 0) {
            Logger.log("Error creating shader. Type: " + shaderType);
        }

        glShaderSource(shaderId, shaderCode);
        glCompileShader(shaderId);

        if (glGetShaderi(shaderId, GL_COMPILE_STATUS) == 0) {
            Logger.log("Error compiling Shader code: " + glGetShaderInfoLog(shaderId, 1024));
        }

        glAttachShader(programId, shaderId);

        return shaderId;
    }

    private  void link() {
        glLinkProgram(programId);
        if (glGetProgrami(programId, GL_LINK_STATUS) == 0) {
            Logger.log("Error linking Shader code: " + glGetProgramInfoLog(programId, 1024));
        }

        if (vertexShaderId != 0) {
            glDetachShader(programId, vertexShaderId);
        }
        if (fragmentShaderId != 0) {
            glDetachShader(programId, fragmentShaderId);
        }

        glValidateProgram(programId);
        if (glGetProgrami(programId, GL_VALIDATE_STATUS) == 0) {
            System.out.println("Warning validating Shader code: " + glGetProgramInfoLog(programId, 1024));
        }
    }

    public abstract void init();

    public abstract void draw();
    public abstract void clean();

    public void render() {
        bind();
        draw();
        unbind();
    }


    public void bind() {
        glUseProgram(programId);
    }

    public void unbind() {
        glUseProgram(0);
    }

    public void close() {
        clean();
        unbind();
        if (programId != 0) {
            glDeleteProgram(programId);
        }
    }
}