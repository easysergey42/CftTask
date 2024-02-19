package ru.nsu.burde;

import ru.nsu.burde.statistics.FullStatistician;
import ru.nsu.burde.statistics.NoneStatistician;
import ru.nsu.burde.statistics.ShortStatistician;
import ru.nsu.burde.statistics.Statistician;

public class Main {

    public static void main(String[] args) {

        var conf = new CommandLineArgumentsParser().parse(args);

        Statistician statistician = switch(conf.getStatisticsType()){
            case NONE -> new NoneStatistician();
            case SHORT -> new ShortStatistician();
            case FULL -> new FullStatistician();
        };

        var fp = new FileProcessor(conf, statistician);
        fp.run();
        System.out.print(fp.getStatistics());

    }
}