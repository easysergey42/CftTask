package ru.nsu.burde;

import ru.nsu.burde.statistics.Statistician;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileProcessor {


    private final Map<Utils.ClassifiedType, String> typeToFileName;
    private final AppConfig config;
    private final List<BufferedReader> readers;
    private final Map<Utils.ClassifiedType, BufferedWriter> writers;

    private final Statistician statistician;

    public FileProcessor(AppConfig config, Statistician statistician){
        this.config = config;
        this.statistician = statistician;
        readers = new ArrayList<>();
        for (var file :
                config.getInputFileNames()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8));
                readers.add(reader);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }

        writers = new HashMap<>();

        typeToFileName = new HashMap<>();
        typeToFileName.put(Utils.ClassifiedType.INTEGER, "integers.txt");
        typeToFileName.put(Utils.ClassifiedType.FLOAT, "floats.txt");
        typeToFileName.put(Utils.ClassifiedType.STRING, "strings.txt");
    }

    private void flushWriters() throws IOException {
        for (BufferedWriter value : writers.values()) {
            if (value != null){
                value.flush();
            }
        }
    }

    private void closeWriters() throws IOException {
        for (BufferedWriter value : writers.values()) {
            if (value != null){
                value.close();
            }
        }
    }

    private void writeToFile(Utils.ClassifiedType type, String value) throws IOException {
        var writer = writers.getOrDefault(type, null);
        if (writer == null){
            File dir = new File(config.getOutputPath());
            if (!dir.exists()) dir.mkdirs();
            File f = new File(dir, config.getFilePrefix() + typeToFileName.get(type));

            writer = new BufferedWriter(new FileWriter(f, StandardCharsets.UTF_8, config.isAppend()));
            writers.put(type, writer);
        }
        writer.write(value);
        writer.newLine();
    }

    public void run(){

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
                        var t = Utils.classifyString(line);

                        statistician.take(t, line);

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

    public String getStatistics(){
        return statistician.toString();
    }
}
