package com.cs.vsu.pereslavtsev_oleg.graphics.task2.graphcreatorfx;
import com.cs.vsu.pereslavtsev_oleg.graphics.task2.graphcreatorfx.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public abstract class Figures {


    public static Primitive createLine(int x1, int y1, int x2, int y2) {
        List<Integer> pointsX = new ArrayList<>();
        List<Integer> pointsY = new ArrayList<>();
        double dx = x2 - x1;
        double dy = y2 - y1;
        double step = 0;
        if (java.lang.Math.abs(dx) >= java.lang.Math.abs(dy)) {
            step = java.lang.Math.abs(dx);
        } else {
            step = java.lang.Math.abs(dy);
        }
        dx = dx / step;
        dy = dy / step;
        double x = x1;
        double y = y1;
        double i = 1;
        while (i <= step) {
            pointsX.add((int) (java.lang.Math.round(x)));
            pointsY.add((int) (java.lang.Math.round(y)));
            x = x + dx;
            y = y + dy;
            i++;
        }
        return new Primitive(Utils.listIntToArray(pointsX), Utils.listIntToArray(pointsY));
    }
}
