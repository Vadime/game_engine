package de.novi.shaders;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

import java.util.ArrayList;
import java.util.List;

import de.novi.components.Mesh;

public class MeshRenderer extends Shader {
    List<Mesh> meshes;

    /**
     * 
     */
    public MeshRenderer() {
        super();
    }

    @Override
    public void init() {
        meshes = new ArrayList<>();
        float[] positions = new float[] {
                -0.5f, 0.5f, 0.0f,
                -0.5f, -0.5f, 0.0f,
                0.5f, -0.5f, 0.0f,
                0.5f, 0.5f, 0.0f,
        };
        float[] colours = new float[] {
                0.5f, 0.0f, 0.0f,
                0.0f, 0.5f, 0.0f,
                0.0f, 0.0f, 0.5f,
                0.0f, 0.5f, 0.5f,
        };
        int[] indices = new int[] {
                0, 1, 3, 3, 1, 2,
        };
        meshes.add(new Mesh(positions, colours, indices));
    }

    @Override
    public void draw() {
        for (Mesh mesh : meshes) {
            glBindVertexArray(mesh.getVaoId());
            glDrawElements(GL_TRIANGLES, mesh.getVertexCount(), GL_UNSIGNED_INT, 0);
        }
        glBindVertexArray(0);

    }

    @Override
    public void clean() {
        for (Mesh mesh : meshes) mesh.clean();
    }

}