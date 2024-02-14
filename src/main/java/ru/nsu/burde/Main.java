package ru.nsu.burde;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {

    public static void writeToFile(BufferedWriter writer){

    }
    public static boolean isFractional(String in){

        try {
            Double.parseDouble(in);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

//        return in.matches("-?\\d+\\.\\d*");
    }

    public static boolean isInteger(String in){
        return in.matches("-?\\d+");
    }

    public static void main(String[] args) {
        Utils.parseCommandLineArgs(args);
        System.out.println(Arrays.toString(Globals.inputFileNames));

        // Передаем названия файлов для соотвествующих типов
        var typeToFileName = new HashMap<Classifier.ClassifiedType, String>();
        typeToFileName.put(Classifier.ClassifiedType.INTEGER, "integers.txt");
        typeToFileName.put(Classifier.ClassifiedType.FLOAT, "floats.txt");
        typeToFileName.put(Classifier.ClassifiedType.STRING, "strings.txt");
        Globals.typeToFileName = typeToFileName;

//        Start here

        List<BufferedReader> readers = new CopyOnWriteArrayList<>();

        for (var file :
                Globals.inputFileNames) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8));
                readers.add(reader);
            } catch (IOException e) {
//                throw new RuntimeException(e);
                System.err.println(e.getMessage());
            }
        }

        BufferedWriter StringWriter = null;
        BufferedWriter IntegerWriter = null;
        BufferedWriter FractionalWriter = null;

        try {
            while(!readers.isEmpty()){
                int fCnt = 0;
                    for (var r:
                            readers) {

                        String line = r.readLine();
                        if (line == null){
                            readers.remove(r);
                            r.close();
                        }
                        else{
                            if(isInteger(line)){
                                if (IntegerWriter == null){
                                    IntegerWriter = new BufferedWriter(new FileWriter("integers.txt", StandardCharsets.UTF_8));
                                }
                                IntegerWriter.write(line);
                                IntegerWriter.newLine();
                            }
                            else if (isFractional(line)){
                                if (FractionalWriter == null){
                                    FractionalWriter = new BufferedWriter(new FileWriter("floats.txt", StandardCharsets.UTF_8));
                                }
                                FractionalWriter.write(line);
                                FractionalWriter.newLine();
                            }
                            else{
                                if (StringWriter == null){
                                    StringWriter = new BufferedWriter(new FileWriter("strings.txt", StandardCharsets.UTF_8));
                                }
                                StringWriter.write(line);
                                StringWriter.newLine();
                            }
                        }

                    }
                }
            if (FractionalWriter != null) FractionalWriter.flush();
            if (IntegerWriter != null) IntegerWriter.flush();
            if (StringWriter != null) StringWriter.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (FractionalWriter != null){
                    FractionalWriter.close();
                }
                if (IntegerWriter != null){
                    IntegerWriter.close();
                }
                if (StringWriter != null){
                    StringWriter.close();
                }
//                for (var r : readers){
//                    r.close();
//                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


//        try(BufferedWriter StringWriter = new BufferedWriter(new FileWriter("govno.txt"));
//            BufferedWriter IntegerWriter = new BufferedWriter(new FileWriter("govno.txt"));
//            BufferedWriter FractionalWriter = new BufferedWriter(new FileWriter("govno.txt")))
//        {
//            while(readers.size()>0){
//                for (var r:
//                        readers) {
//
//                    String line = r.readLine();
//                    if (line == null){
//                        readers.remove(r);
//                        r.close();
//                    }
//                    else{
//                    }
//
//                }
//            }
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        System.out.println(readers);


//        End here
    }
}