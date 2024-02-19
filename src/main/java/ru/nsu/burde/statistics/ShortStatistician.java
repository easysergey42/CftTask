package ru.nsu.burde.statistics;


import ru.nsu.burde.Utils;

public class ShortStatistician implements Statistician {
    protected int intCount = 0;
    protected int floatCount = 0;
    protected int stringCount = 0;


    @Override
    public void take(Utils.ClassifiedType type, String value) {
        switch (type){
            case INTEGER -> intCount++;
            case FLOAT -> floatCount++;
            case STRING -> stringCount++;
        }
    }

    @Override
    public String toString() {
        return "\nShort statistics:" +
                "\nNumber of integers: " + intCount +
                "\nNumber of floats: " + floatCount +
                "\nNumber of strings: " + stringCount +
                '\n';
    }
}
