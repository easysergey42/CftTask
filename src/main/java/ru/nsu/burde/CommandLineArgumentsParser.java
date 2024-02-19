package ru.nsu.burde;

import org.apache.commons.cli.*;

public class CommandLineArgumentsParser {

    public AppConfig parse(String[] args){
        Options options = new Options();
        options.addOption("o", true, "Path to results");
        options.addOption("p", true, "Prefix of output files");
        options.addOption("a", false, "Append results to output files");

        OptionGroup statisticsOpt = new OptionGroup();
        statisticsOpt.addOption(new Option("s", "Show short statistics"));
        statisticsOpt.addOption(new Option("f", "Show full statistics"));

        options.addOptionGroup(statisticsOpt);

        CommandLineParser parser = new DefaultParser();

        AppConfig conf = new AppConfig();
        try {
            CommandLine cmd = parser.parse(options, args);

            conf.setOutputPath(cmd.getOptionValue("o", "."));
            conf.setFilePrefix(cmd.getOptionValue("p", ""));
            if (cmd.hasOption("f")) conf.setStatisticsType(AppConfig.StatisticsType.FULL);
            else if (cmd.hasOption("s")) conf.setStatisticsType(AppConfig.StatisticsType.SHORT);
            conf.setAFlag(cmd.hasOption("a"));
            conf.setInputFileNames(cmd.getArgs());

        } catch (ParseException e) {
            System.err.println(e.getMessage());
        }

        return conf;
    }
}
