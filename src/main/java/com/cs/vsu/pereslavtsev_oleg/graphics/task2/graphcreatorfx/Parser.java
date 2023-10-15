package com.cs.vsu.pereslavtsev_oleg.graphics.task2.graphcreatorfx;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    public List<Term> parse(String string) {
        List<Term> terms = new ArrayList<>();
        char[] symbols = string.toCharArray();
        for (int i = 0; i < symbols.length; i++) {
            if (symbols[i] == '+') {
//                String strTerm =
            }
        }
        return null;
    }

    class Term {
        private String value;

        public Term(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
