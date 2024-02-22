package zad4;

import java.util.Collections;
import java.util.List;

public class DistributionTester {

    private final GeneratorStrategy generatorStrategy;
    private final PercentileStrategy percentileStrategy;

    public DistributionTester(GeneratorStrategy generatorStrategy, PercentileStrategy percentileStrategy) {
        this.generatorStrategy = generatorStrategy;
        this.percentileStrategy = percentileStrategy;
    }

    public void test() {
        List<Integer> values = generatorStrategy.generate();
        System.out.println("Generated values:" + values);
        Collections.sort(values);
        for (double percentile = 10; percentile <= 90; percentile+=10) {
            int value = percentileStrategy.calculatePercentile(values, percentile);
            System.out.printf("%.0f%% percentile: %d\n", percentile, value);
        }
    }

}
