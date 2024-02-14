package ru.nsu.burde;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {

    public static void main(String[] args) {
        Utils.parseCommandLineArgs(args);

//Передаем названия файлов для соотвествующих типов
        var typeToFileName = new HashMap<Classifier.ClassifiedType, String>();
        typeToFileName.put(Classifier.ClassifiedType.INTEGER, "integers.txt");
        typeToFileName.put(Classifier.ClassifiedType.FLOAT, "floats.txt");
        typeToFileName.put(Classifier.ClassifiedType.STRING, "strings.txt");
        Globals.typeToFileName = typeToFileName;

//        Start here
        Utils.processInput();



//        End here
    }
}