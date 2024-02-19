package ru.nsu.burde.statistics;

import ru.nsu.burde.Utils;

public interface Statistician {

    public void take(Utils.ClassifiedType type, String value);

//    public void printStatistics();
}
