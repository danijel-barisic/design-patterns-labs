package zad4;

public class Main {
    public static void main(String[] args) {
        //zad4.GeneratorStrategy generator = new zad4.SequentialGenerator(1, 100, 1);
        //zad4.GeneratorStrategy generator = new zad4.RandomGenerator(0, 12, 100);
        GeneratorStrategy generator = new FibonacciGenerator(20);

        PercentileStrategy percentileStrategy = new NearestRankPercentile();
        //zad4.PercentileStrategy percentileStrategy = new zad4.LinearInterpolationPercentile();

        DistributionTester tester = new DistributionTester(generator, percentileStrategy);
        tester.test();
    }
}
