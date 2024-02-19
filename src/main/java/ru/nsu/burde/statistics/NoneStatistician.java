package ru.nsu.burde.statistics;

import ru.nsu.burde.Utils;

public class NoneStatistician implements Statistician {
    @Override
    public void take(Utils.ClassifiedType type, String value) {
    }

    @Override
    public String toString() {
        return "";
    }
}
