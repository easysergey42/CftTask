package ru.nsu.burde;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Utils {



    public enum ClassifiedType{
        INTEGER,
        FLOAT,
        STRING
    }

    public static ClassifiedType classifyString(String s){
        if (isInteger(s)) return ClassifiedType.INTEGER;
        if (isFloat(s)) return ClassifiedType.FLOAT;
        return ClassifiedType.STRING;
    }

    public static boolean isInteger(String in){
        try {
            new BigInteger(in);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isFloat(String in){
        try {
            new BigDecimal(in);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
