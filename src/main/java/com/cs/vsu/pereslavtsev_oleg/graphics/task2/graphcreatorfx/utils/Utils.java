package com.cs.vsu.pereslavtsev_oleg.graphics.task2.graphcreatorfx.utils;

import java.util.List;

public abstract class Utils {
    public static int[] listIntToArray(List<Integer> list) {
        int[] result = new int[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    public static List<Integer> copyValue(List<Integer> target, int[] items) {
        for (int i: items) {
            target.add(i);
        }
        return target;
    }
}
