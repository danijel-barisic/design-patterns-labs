package zad4;

import java.util.ArrayList;
import java.util.List;

public class SequentialGenerator implements GeneratorStrategy {

    private final int lowerBound;
    private final int upperBound;
    private final int step;

    public SequentialGenerator(int lowerBound, int upperBound, int step) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.step = step;
    }

    @Override
    public List<Integer> generate(){
        int n = (upperBound - lowerBound) / step + 1;
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0, x = lowerBound; i < n; i++, x += step) {
            numbers.add(x);
        }
        return numbers;
    }

}
