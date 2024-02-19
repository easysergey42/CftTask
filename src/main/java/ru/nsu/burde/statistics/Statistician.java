package ru.nsu.burde.statistics;

import ru.nsu.burde.Utils;

public interface Statistician {

    void take(Utils.ClassifiedType type, String value);

}
