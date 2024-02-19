package ru.nsu.burde.statistics;

import ru.nsu.burde.Utils;

import java.math.BigDecimal;
import java.math.BigInteger;

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
        StringBuilder sb = new StringBuilder("\nFull statistics:");
        sb.append("\nNumber of integers: ").append(intCount);
        if (intCount > 0){
            sb.append("\n\tMinimum integer: ").append(intMin);
            sb.append("\n\tMaximum integer: ").append(intMax);
            sb.append("\n\tSum of integers: ").append(intSum);
            sb.append("\n\tAverage of integers: ").append(new BigDecimal(intSum).multiply(BigDecimal.valueOf(1D/intCount)));
        }
        sb.append("\nNumber of floats: ").append(floatCount);
        if (floatCount > 0){
            sb.append("\n\tMinimum float: ").append(floatMin);
            sb.append("\n\tMaximum float: ").append(floatMax);
            sb.append("\n\tSum of flats: ").append(floatSum);
            sb.append("\n\tAverage of floats: ").append(floatSum.multiply(BigDecimal.valueOf(1D/floatCount)));
        }
        sb.append("\nNumber of strings: ").append(stringCount);
        if (stringCount > 0){
            sb.append("\n\tSize of the shortest line: ").append(minLength);
            sb.append("\n\tSize of the longest line: ").append(maxLength);
        }
        sb.append('\n');
        return sb.toString();
    }
}
