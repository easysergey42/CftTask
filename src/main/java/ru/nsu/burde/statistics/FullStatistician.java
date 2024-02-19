package ru.nsu.burde.statistics;

import ru.nsu.burde.Utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

public class FullStatistician extends ShortStatistician {

    private BigInteger intSum = BigInteger.ZERO;
    private BigInteger intMin = null;
    private BigInteger intMax = null;
    private BigDecimal floatSum = BigDecimal.ZERO;
    private BigDecimal floatMin = null;
    private BigDecimal floatMax = null;
    private int minLength = Integer.MAX_VALUE;
    private int maxLength = 0;


    @Override
    public void take(Utils.ClassifiedType type, String value) {
        super.take(type,value);
        switch (type){
            case INTEGER -> {
                var val = new BigInteger(value);
                intSum = intSum.add(val);
                if (intMin == null || intMin.compareTo(val) > 0) intMin = val;
                if (intMax == null || intMax.compareTo(val) < 0) intMax = val;
            }
            case FLOAT -> {
                var val = new BigDecimal(value);
                floatSum = floatSum.add(val);
                if (floatMin == null || floatMin.compareTo(val) > 0) floatMin = val;
                if (floatMax == null || floatMax.compareTo(val) < 0) floatMax = val;
            }
            case STRING -> {
                var len = value.length();
                if (len < minLength) minLength = len;
                if (len > maxLength) maxLength = len;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\nПолная статистика:");
        sb.append("\nКоличество целых чисел: ").append(intCount);
        if (intCount > 0){
            sb.append("\n\tМинимальное целое число: ").append(intMin);
            sb.append("\n\tМаксимальное целое число: ").append(intMax);
            sb.append("\n\tСумма целых чисел: ").append(intSum);
            sb.append("\n\tСреднее значение целых чисел: ").append(new BigDecimal(intSum).multiply(BigDecimal.valueOf(1D/intCount)));
        }
        sb.append("\nКоличество вещественных чисел: ").append(floatCount);
        if (floatCount > 0){
            sb.append("\n\tМинимальное вещественное число: ").append(floatMin);
            sb.append("\n\tМаксимальное вещественное число: ").append(floatMax);
            sb.append("\n\tСумма вещественных чисел: ").append(floatSum);
            sb.append("\n\tСреднее значение вещественных чисел: ").append(floatSum.multiply(BigDecimal.valueOf(1D/floatCount)));
        }
        sb.append("\nКоличество строк: ").append(stringCount);
        if (stringCount > 0){
            sb.append("\n\tРазмер самой короткой строки: ").append(minLength);
            sb.append("\n\tРазмер самой длинной строки: ").append(maxLength);
        }
        sb.append('\n');
        return sb.toString();
    }
}
