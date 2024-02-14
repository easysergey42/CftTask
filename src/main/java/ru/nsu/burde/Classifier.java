package ru.nsu.burde;

import java.io.BufferedWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Classifier {

    public enum ClassifiedType{
        INTEGER,
        FLOAT,
        STRING
    }



//    public ClassifiedType determineType(String s){
//
//    }
    public static ClassifiedType classifyString(String s){
        if (isInteger(s)) return ClassifiedType.INTEGER;
        if (isFloat(s)) return ClassifiedType.FLOAT;
        return ClassifiedType.STRING;
    }

    public static boolean isInteger(String in){
        try {
            var x = new BigInteger(in);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isFloat(String in){
        try {
            var x = new BigDecimal(in);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
