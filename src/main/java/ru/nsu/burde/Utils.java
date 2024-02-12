package ru.nsu.burde;

import org.apache.commons.cli.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static void processInput(){

        List<BufferedReader> readers = new ArrayList<>();

        for (var file :
                Globals.inputFileNames) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                readers.add(reader);
            } catch (FileNotFoundException e) {
//                throw new RuntimeException(e);
                System.err.println(e.getMessage());
            }
        }

        try(BufferedWriter StringWriter = new BufferedWriter(new FileWriter("govno.txt"));
            BufferedWriter IntegerWriter = new BufferedWriter(new FileWriter("govno.txt"));
            BufferedWriter FractionalWriter = new BufferedWriter(new FileWriter("govno.txt")))
        {
            while(readers.size()>0){
                for (var r:
                        readers) {

                    String line = r.readLine();
                    if (line == null){
                        readers.remove(r);
                        r.close();
                    }
                    else{
                    }

                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }



//        System.out.println(readers);
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
