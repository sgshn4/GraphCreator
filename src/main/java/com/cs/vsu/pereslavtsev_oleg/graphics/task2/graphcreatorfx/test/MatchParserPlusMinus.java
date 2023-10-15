package com.cs.vsu.pereslavtsev_oleg.graphics.task2.graphcreatorfx.test;

public class MatchParserPlusMinus {
    public MatchParserPlusMinus(){}

    public double Parse(String s) throws Exception
    {
        Result result = PlusMinus(s);
        if (!result.rest.isEmpty()) {
            System.err.println("Error: can't full parse");
            System.err.println("rest: " + result.rest);
        }
        return result.acc;
    }

    private Result PlusMinus(String s) throws Exception
    {
        Result current = Num(s);
        double acc = current.acc;

        while (current.rest.length() > 0) {
            if (!(current.rest.charAt(0) == '+' || current.rest.charAt(0) == '-')) break;

            char sign = current.rest.charAt(0);
            String next = current.rest.substring(1);

            acc = current.acc;

            current = Num(next);
            if (sign == '+') {
                acc += current.acc;
            } else {
                acc -= current.acc;
            }
            current.acc = acc;
        }
        return new Result(current.acc, current.rest);
    }

    private Result Num(String s) throws Exception
    {
        int i = 0;
        int dot_cnt = 0;
        boolean negative = false;
        // число также может начинаться с минуса
        if( s.charAt(0) == '-' ){
            negative = true;
            s = s.substring( 1 );
        }
        // разрешаем только цифры и точку
        while (i < s.length() && (Character.isDigit(s.charAt(i)) || s.charAt(i) == '.')) {
            // но также проверям, что в числе может быть только одна точка!
            if (s.charAt(i) == '.' && ++dot_cnt > 1) {
                throw new Exception("not valid number '" + s.substring(0, i + 1) + "'");
            }
            i++;
        }
        if( i == 0 ){ // что-либо похожее на число мы не нашли
            throw new Exception( "can't get valid number in '" + s + "'" );
        }

        double dPart = Double.parseDouble(s.substring(0, i));
        if( negative ) dPart = -dPart;
        String restPart = s.substring(i);

        return new Result(dPart, restPart);
    }
}
