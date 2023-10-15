package com.cs.vsu.pereslavtsev_oleg.graphics.task2.graphcreatorfx;

public class Primitive {
    private int[] x;
    private int[] y;

    public Primitive(int[] x, int[] y) {
        this.x = x;
        this.y = y;
    }

    public int[] getX() {
        return x;
    }

    public int[] getY() {
        return y;
    }

    public void shift(int x, int y) {
        for (int i = 0; i < this.x.length; i++) {
            this.x[i] = this.x[i] - x;
            this.y[i] = this.y[i] - y;
        }
    }
}
