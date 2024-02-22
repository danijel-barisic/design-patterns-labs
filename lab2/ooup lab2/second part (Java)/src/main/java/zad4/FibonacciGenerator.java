package zad4;

import java.util.ArrayList;
import java.util.List;

public class FibonacciGenerator implements GeneratorStrategy{

    private final int n;

    public FibonacciGenerator(int n) {
        this.n = n;
    }

    @Override
    public List<Integer> generate() {
        List<Integer> numbers = new ArrayList<>();
        int a = 0, b = 1;
        for (int i = 0; i < n; i++) {
            int x = a + b;
            a = b;
            b = x;
            numbers.add(x);
        }
        return numbers;
    }
}
