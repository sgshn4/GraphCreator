package com.cs.vsu.pereslavtsev_oleg.graphics.task2.graphcreatorfx.utils;

import com.cs.vsu.pereslavtsev_oleg.graphics.task2.graphcreatorfx.Primitive;
import com.cs.vsu.pereslavtsev_oleg.graphics.task2.graphcreatorfx.parser.MatchParser;

import java.util.ArrayList;
import java.util.HashMap;
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

    public static int getLimit(int start, int border, String formula) {
        MatchParser parser = new MatchParser();
        try {
            parser.Parse(formula);
            HashMap<String, Double> variables = parser.getVariables();
            for (String i : variables.keySet()) {
                for (int j = start; j < border; j++) {
                    parser.setVariable(i, (double)(j));
                    if (parser.Parse(formula) > border) return j;
                }
            }
        } catch (Exception e) {
            System.err.println("err: " + e);
        }
        return 0;
    }
}
