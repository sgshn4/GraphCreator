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

    public static Primitive createRectangle(int x1, int y1, int x2, int y2) {
        List<Integer> pointsX = new ArrayList<>();
        List<Integer> pointsY = new ArrayList<>();
        Primitive line = createLine(x1, y1, x2, y1);
        pointsX = Utils.copyValue(pointsX, line.getX());
        pointsY = Utils.copyValue(pointsY, line.getY());
        line = createLine(x2, y1, x2, y2);
        pointsX = Utils.copyValue(pointsX, line.getX());
        pointsY = Utils.copyValue(pointsY, line.getY());
        line = createLine(x1, y1, x1, y2);
        pointsX = Utils.copyValue(pointsX, line.getX());
        pointsY = Utils.copyValue(pointsY, line.getY());
        line = createLine(x1, y2, x2, y2);
        pointsX = Utils.copyValue(pointsX, line.getX());
        pointsY = Utils.copyValue(pointsY, line.getY());
        return new Primitive(Utils.listIntToArray(pointsX), Utils.listIntToArray(pointsY));
    }

    public static Primitive createRound(int x, int y, int r) {
        int i = -r;
        int j = 0;
        int error = 2 - 2 * r;
        List<Integer> pointsX = new ArrayList<>();
        List<Integer> pointsY = new ArrayList<>();
        while (i < 0) {
            pointsX.add(x - i);
            pointsY.add(y + j);
            pointsX.add(x - j);
            pointsY.add(y - i);
            pointsX.add(x + i);
            pointsY.add(y - j);
            pointsX.add(x + j);
            pointsY.add(y + i);

            r = error;
            if (r <= j) error += ++j * 2 + 1;
            if (r > i || error > j) error += ++i * 2 + 1;
        }
        return new Primitive(Utils.listIntToArray(pointsX), Utils.listIntToArray(pointsY));
    }
}
