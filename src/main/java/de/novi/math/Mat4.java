package de.novi.math;

public class Mat4 {
    public final float[][] mat;
    public Mat4() {
        this.mat = new float[3][3];
    }

    public Mat4 identity() {
        this.mat[0][0] = 1;
        this.mat[0][1] = 0;
        this.mat[0][2] = 0;
        this.mat[0][3] = 0;

        this.mat[1][0] = 0;
        this.mat[1][1] = 1;
        this.mat[1][2] = 0;
        this.mat[1][3] = 0;

        this.mat[2][0] = 0;
        this.mat[2][1] = 0;
        this.mat[2][2] = 1;
        this.mat[2][3] = 0;

        this.mat[3][0] = 0;
        this.mat[3][1] = 0;
        this.mat[3][2] = 0;
        this.mat[3][3] = 1;

        return this;
    }
    
}
