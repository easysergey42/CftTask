package ru.nsu.burde;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    private static final Pattern intPattern = Pattern.compile("[-+]?\\d+");
    private static final Matcher intMatcher = intPattern.matcher("");
    private static final Pattern floatPattern = Pattern.compile("[-+]?\\d*[.]\\d*([eE][-+]?\\d+)?");

    private static final Matcher floatMatcher = floatPattern.matcher("");

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
        intMatcher.reset(in);
        return intMatcher.matches();
    }

    public static boolean isFloat(String in){
        floatMatcher.reset(in);
        return (!in.equals(".") && floatMatcher.matches());
    }
}
