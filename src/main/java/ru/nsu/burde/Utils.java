package ru.nsu.burde;

import org.apache.commons.cli.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class Utils {


    private static List<BufferedReader> readers;
    private static Map<Classifier.ClassifiedType, BufferedWriter> writers = new HashMap<>();

    private static void setWriters(){
        writers = new HashMap<>();
        for (Classifier.ClassifiedType key : Classifier.ClassifiedType.values()) {
            writers.put(key, null);
        }
    }

    private static void flushWriters() throws IOException {
        for (BufferedWriter value : writers.values()) {
            if (value != null){
                value.flush();
            }
        }
    }

    private static void closeWriters() throws IOException {
        for (BufferedWriter value : writers.values()) {
            if (value != null){
                value.close();
            }
        }
    }

    private static void setReaders(String[] inputFileNames){
        readers = new ArrayList<>();
        for (var file :
                inputFileNames) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8));
                readers.add(reader);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private static void writeToFile(Classifier.ClassifiedType type, String value) throws IOException {
        var writer = writers.getOrDefault(type, null);
        if (writer == null){
            File dir = new File(Globals.outputPath);
            if (!dir.exists()) dir.mkdirs();
            File f = new File(dir, Globals.filePrefix + Globals.typeToFileName.get(type));

            writer = new BufferedWriter(new FileWriter(f, StandardCharsets.UTF_8));
            writers.put(type, writer);
        }
        writer.write(value);
        writer.newLine();
    }

    public static void processInput() {
        setReaders(Globals.inputFileNames);


        try {
            while (!readers.isEmpty()) {
                var iter = readers.listIterator();
                while (iter.hasNext()){
                    var reader = iter.next();
                    String line = reader.readLine();
                    if (line == null){
                        reader.close();
                        iter.remove();
                    }
                    else{
                        var t = Classifier.classifyString(line);

                        writeToFile(t, line);
                    }
                }
            }
            flushWriters();
            closeWriters();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void parseCommandLineArgs(String[] args){
        Options options = new Options();
        options.addOption("o", true, "Path to results");
        options.addOption("p", true, "Prefix of output files");

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);

            Globals.outputPath = cmd.getOptionValue("o", "");
            Globals.filePrefix = cmd.getOptionValue("p", "");

            Globals.inputFileNames = cmd.getArgs();
//            System.out.println(Arrays.toString(cmd.getOptions()));
//            File theDir = new File(Globals.outputPath);
//            if (!Globals.outputPath.equals("") && !theDir.exists()){
//                if (!theDir.mkdirs()){
//                    throw new IOException("Failed to create directory " + Globals.outputPath);
//                }
//            }
//            if (!Globals.outputPath.equals("")){
//                Globals.outputPath += File.separator;
//            }

        } catch (ParseException e) {
            System.err.println(e.getMessage());
        }
    }
}
