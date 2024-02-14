package ru.nsu.burde;

import java.io.BufferedWriter;
import java.io.Writer;
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

    public static boolean isInteger(String in){
        return in.matches("-?\\d");
    }
}
