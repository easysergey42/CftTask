package ru.nsu.burde;


public class ShortStatistics implements Statistics{
    private static ShortStatistics INSTANCE;
    private int intCount = 0;
    private int floatCount = 0;
    private int stringCount = 0;


    private ShortStatistics(){
    }
    @Override
    public void take(Classifier.ClassifiedType type, String value) {
        switch (type){
            case INTEGER -> intCount++;
            case FLOAT -> floatCount++;
            case STRING -> stringCount++;
        }
    }

    public static ShortStatistics getInstance(){
        if (INSTANCE == null){
            INSTANCE = new ShortStatistics();
        }
        return INSTANCE;
    }

    @Override
    public String toString() {
        return "ShortStatistics{" +
                "intCount=" + intCount +
                ", floatCount=" + floatCount +
                ", stringCount=" + stringCount +
                '}';
    }
}
