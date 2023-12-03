package com.cs.vsu.pereslavtsev_oleg.graphics.task2.graphcreatorfx;

public class Figure {
    private String formula;
    private Primitive primitive;

    public Figure(String formula, Primitive primitive) {
        this.formula = formula;
        this.primitive = primitive;
    }

    public Figure(String formula) {
        this.formula = formula;
    }

    public Figure(Primitive primitive) {
        this.primitive = primitive;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public Primitive getPrimitive() {
        return primitive;
    }

    public void setPrimitive(Primitive primitive) {
        this.primitive = primitive;
    }
}
