package zad4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomGenerator implements GeneratorStrategy{

    private double mi;
    private double sigma;
    private int n;

    public RandomGenerator(double mi, double sigma, int n) {
        this.mi = mi;
        this.sigma = sigma;
        this.n = n;
    }

    @Override
    public List<Integer> generate() {
        List<Integer> numbers = new ArrayList<>();

        Random r = new Random();
        for (int i = 0; i < n; i++) {
            double x = r.nextGaussian() * sigma + mi;
            numbers.add((int) Math.round(x));
        }
        return numbers;
    }
}
