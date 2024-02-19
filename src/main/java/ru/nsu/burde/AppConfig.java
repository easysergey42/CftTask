package ru.nsu.burde;

public class AppConfig {


    public enum StatisticsType {
        NONE,
        SHORT,
        FULL
    }

    private String outputPath = ".";
    private String filePrefix = "";
    private boolean aFlag = false;

    private StatisticsType statisticsType = StatisticsType.NONE;

    private String[] inputFileNames = {};

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public String getFilePrefix() {
        return filePrefix;
    }

    public void setFilePrefix(String filePrefix) {
        this.filePrefix = filePrefix;
    }

    public boolean isAppend() {
        return aFlag;
    }

    public void setAFlag(boolean aFlag) {
        this.aFlag = aFlag;
    }

    public StatisticsType getStatisticsType() {
        return statisticsType;
    }

    public void setStatisticsType(StatisticsType statisticsType) {
        this.statisticsType = statisticsType;
    }

    public String[] getInputFileNames() {
        return inputFileNames;
    }

    public void setInputFileNames(String[] inputFileNames) {
        this.inputFileNames = inputFileNames;
    }

}
